package com.lin.sys.entity;

import java.util.Date;

import com.lin.entity.BaseEntity;

public class SysRole extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;	
	
	public SysRole() {
		super();
	}
	
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
	
}