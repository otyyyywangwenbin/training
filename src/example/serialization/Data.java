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
 * Created on 2013-1-7
 *******************************************************************************/

package example.serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
public class Data implements Serializable {
	private static final long serialVersionUID = -2274850935729576143L;
	private String s;
	private int i;
	private Date d;
	private List<String> list;
	private Map<String, String> map;
	private Data parent;
	private transient String ts;

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public Data getParent() {
		return parent;
	}

	public void setParent(Data parent) {
		this.parent = parent;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public static Data newInstance() {
		Data data = new Data();
		data.s = UUID.randomUUID().toString();
		data.i = new Random().nextInt();
		data.d = new Date();
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		data.list = list;
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "A");
		map.put("2", "B");
		map.put("3", "C");
		data.map = map;
		data.parent = data;
		data.ts = "TS:" + data.s;
		return data;
	}
	
	
//	private void writeObject(java.io.ObjectOutputStream oos)
//			throws java.io.IOException {
//		oos.writeUTF(s);
//		oos.writeUTF(ts);
//	}
//	
//	private void readObject(java.io.ObjectInputStream ois)
//			throws java.io.IOException, ClassNotFoundException {
//		s = ois.readUTF();
//		ts = ois.readUTF();
//	}
}

/*
 * 修改历史
 * $Log$ 
 */