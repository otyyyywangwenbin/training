/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2012 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2013-1-9
 *******************************************************************************/

package example.integration;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@SuppressWarnings("rawtypes")
public class ServiceTcpExporter implements IServiceExporter {
	private SocketServer socketServer;
	private ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<String, Object>();

	public ServiceTcpExporter(String ip, int port) throws IOException {
		socketServer = new SocketServer(ip, port);
		start();
	}

	private void start() {
		socketServer.start();
	}

	public void destroy() {
		serviceMap.clear();
		socketServer.stop();
	}

	public void bind(Object service) {
		Class[] interfaces = service.getClass().getInterfaces();
		if (interfaces.length != 1) {
			throw new IllegalArgumentException();
		}
		Object existed = serviceMap.putIfAbsent(interfaces[0].getName(), service);
		if (existed != null) {
			throw new IllegalStateException();
		}
	}

	public void unbind(Object service) {
		Class[] interfaces = service.getClass().getInterfaces();
		if (interfaces.length != 1) {
			throw new IllegalArgumentException();
		}
		serviceMap.remove(interfaces[0].getName());
	}

	private static Map<Class, Class> primitiveTypeMap = new HashMap<Class, Class>();
	static {
		primitiveTypeMap.put(int.class, Integer.class);
		primitiveTypeMap.put(short.class, Short.class);
		primitiveTypeMap.put(long.class, Long.class);
		primitiveTypeMap.put(char.class, Character.class);
		primitiveTypeMap.put(byte.class, Byte.class);
		primitiveTypeMap.put(boolean.class, Boolean.class);
		primitiveTypeMap.put(float.class, Float.class);
		primitiveTypeMap.put(double.class, Double.class);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public Object invokeService(ServiceInvocation invocation) throws Throwable {
		Object service = serviceMap.get(invocation.getServiceName());
		if (service == null) {
			throw new IllegalArgumentException("Service: " + invocation.getServiceName() + " not found!");
		}
		Method methodToUse = null;
		Method[] methods = service.getClass().getMethods();
		loop1: for (Method method : methods) {
			if (method.getName().equals(invocation.getFuncName())) {
				Class[] paramTypes = method.getParameterTypes();
				if (paramTypes.length == invocation.getParams().length) {
					loop2: for (int i = 0; i < paramTypes.length; i++) {
						Class paramType = paramTypes[i];
						if (paramType.isPrimitive()) {
							paramType = primitiveTypeMap.get(paramType);
						}
						if (!paramType.isAssignableFrom(invocation.getParams()[i].getClass())) {
							continue loop1;
						}
					}
					methodToUse = method;
					break;
				}
			}
		}
		if (methodToUse == null) {
			throw new IllegalArgumentException("Func: " + invocation.getFuncName() + " not found!");
		}
		return methodToUse.invoke(service, invocation.getParams());
	}

	// ////////
	private class SocketServer {
		private ServerSocket serverSocket;
		private ExecutorService executorService;// 线程池
		private final int POOL_SIZE = 10;// 单个CPU线程池大小
		private boolean stop = false;

		public SocketServer(String ip, int port) throws IOException {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(ip, port));
			executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
			System.out.println("Start socket server: " + serverSocket.getLocalSocketAddress());
		}

		public void start() {
			new Thread("ServerSocket-Acceptor") {
				public void run() {
					_run();
				}
			}.start();
		}

		private void _run() {
			while (!stop) {
				try {
					// 接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
					final Socket socket = serverSocket.accept();
					executorService.execute(new Runnable() {
						public void run() {
							SocketPacket packet = null;
							try {
								packet = new SocketPacket(socket, true);
								while (true) {
									ServiceInvocation invocation = (ServiceInvocation) packet.read();
									Object ret = null;
									try {
										ret = ServiceTcpExporter.this.invokeService(invocation);
									} catch (Throwable e) {
										ret = e;
									}
									packet.write(ret);
								}
							} catch (Throwable e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void stop() {
			stop = true;
			executorService.shutdownNow();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */