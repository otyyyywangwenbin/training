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
 * Created on 2013-7-23
 *******************************************************************************/


package example.proxy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import example.proxy.IBank.AccountInfo;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class ASMExample2 {
	public static void main(String[] args) {
		IBank bank = new Bank();
		AccountInfo account = new AccountInfo();
		System.out.println(bank.deposit(account, 100));
	}
	
	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new ClassFileTransformer() {
			public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
				try {
					return ASMExample2.transform(loader,className,classBeingRedefined,protectionDomain,classfileBuffer);
				} catch (IOException e) {
					e.printStackTrace();
					return classfileBuffer;
				}
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private static byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IOException {
		if (!className.equals("example/proxy/Bank")) {
			System.out.println("\tignored class: " + className);
			return classfileBuffer;
		}
		System.out.println("\ttransform class: " + className);
		ClassReader cr = new ClassReader(new ByteArrayInputStream(classfileBuffer));
		ClassNode classNode = new ClassNode();
		cr.accept(classNode, 0);

		for (Object method : classNode.methods) {
			MethodNode methodNode = (MethodNode) method;
			if (!"deposit".equals(methodNode.name) && !"withdraw".equals(methodNode.name)) {
				continue;
			}

			InsnList beginList = new InsnList();
			beginList.add(new LdcInsnNode(methodNode.name));
			beginList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "example/proxy/ASMExample", "logEnter", "(Ljava/lang/String;)V"));

			methodNode.instructions.insert(beginList);

			// A method can have multiple places for return
			// All of them must be handled.
			Iterator<AbstractInsnNode> insnNodes = methodNode.instructions.iterator();
			while (insnNodes.hasNext()) {
				AbstractInsnNode insn = insnNodes.next();
				// System.out.println(insn.getOpcode());

				if (insn.getOpcode() == Opcodes.IRETURN
						|| insn.getOpcode() == Opcodes.RETURN
						|| insn.getOpcode() == Opcodes.ARETURN
						|| insn.getOpcode() == Opcodes.LRETURN
						|| insn.getOpcode() == Opcodes.DRETURN
						|| insn.getOpcode() == Opcodes.FRETURN) {
					InsnList endList = new InsnList();
					endList.add(new VarInsnNode(Opcodes.FSTORE, 3));
					endList.add(new LdcInsnNode(methodNode.name));
					endList.add(new VarInsnNode(Opcodes.FLOAD, 3));
					endList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "example/proxy/ASMExample", "logExit", "(Ljava/lang/String;F)V"));
					endList.add(new VarInsnNode(Opcodes.FLOAD, 3));
					endList.add(new InsnNode(Opcodes.FRETURN));
					methodNode.instructions.insertBefore(insn, endList);
				}

				if (insn.getOpcode() == Opcodes.ATHROW) {
					InsnList endList = new InsnList();
					endList.add(new VarInsnNode(Opcodes.ASTORE, 3));
					endList.add(new LdcInsnNode(methodNode.name));
					endList.add(new VarInsnNode(Opcodes.ALOAD, 3));
					endList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "example/proxy/ASMExample", "logThrowable", "(Ljava/lang/String;Ljava/lang/Throwable;)V"));
					endList.add(new VarInsnNode(Opcodes.ALOAD, 3));
					endList.add(new InsnNode(Opcodes.ATHROW));
					methodNode.instructions.insertBefore(insn, endList);
				}

			}

		}
		// We are done now. so dump the class
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(cw);

		byte[] bytes = cw.toByteArray();
		return bytes;
	}
	
}

/*
 * 修改历史
 * $Log$ 
 */