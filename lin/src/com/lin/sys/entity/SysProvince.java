package com.lin.sys.entity;

import com.lin.entity.BaseEntity;


public class SysProvince extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String name;

	public SysProvince() {
		super();
	}
	
	public SysProvince(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
