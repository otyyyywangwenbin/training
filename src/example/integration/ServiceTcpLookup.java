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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@SuppressWarnings("unchecked")
public class ServiceTcpLookup implements IServiceLookup {

	private SocketClient socketClient;

	public ServiceTcpLookup(String ip, int port) throws IOException {
		socketClient = new SocketClient(ip, port);
	}

	public <T> T lookup(Class<T> serviceIntf) {
		return (T) Proxy.newProxyInstance(ServiceTcpLookup.class.getClassLoader(), new Class[] { serviceIntf }, new SocketClientInvocationHandler(serviceIntf));
	}

	@SuppressWarnings("rawtypes")
	private class SocketClientInvocationHandler implements InvocationHandler {
		private String serviceName;

		public SocketClientInvocationHandler(Class serviceIntf) {
			this.serviceName = serviceIntf.getName();
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			ServiceInvocation invocation = new ServiceInvocation();
			invocation.setServiceName(serviceName);
			invocation.setFuncName(method.getName());
			invocation.setParams(args);
			Object ret = socketClient.sendAndReceive(invocation);
			if (ret instanceof Throwable) {
				throw (Throwable) ret;
			}
			return ret;
		}
	}

	private class SocketClient {
		private Socket socket = null;
		private SocketPacket socketPacket = null;

		public SocketClient(String ip, int port) throws IOException {
			socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port));
			socketPacket = new SocketPacket(socket, false);
		}

		public Object sendAndReceive(ServiceInvocation invocation) throws IOException {
			socketPacket.write(invocation);
			Object ret = socketPacket.read();
			return ret;
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */