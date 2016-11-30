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

package example.proxy;


/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class Bank implements IBank {
	private float money = 1000f;

	public float deposit(AccountInfo account, float money) {
		this.money += money;
		return this.money;
//		throw new RuntimeException("exception");
	}

	public float withdraw(AccountInfo account, float money) {
		this.money -= money;
		return this.money;
	}

}

/*
 * 修改历史
 * $Log$ 
 */