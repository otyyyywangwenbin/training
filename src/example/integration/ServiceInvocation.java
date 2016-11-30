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

import java.io.Serializable;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class ServiceInvocation implements Serializable {
	private static final long serialVersionUID = 1376518500310740325L;
	private String serviceName;
	private String funcName;
	private Object[] params;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funName) {
		this.funcName = funName;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String toString() {
		return "ServiceInvocation: service: " + serviceName + ", func: " + funcName;
	}
}

/*
 * 修改历史
 * $Log$ 
 */