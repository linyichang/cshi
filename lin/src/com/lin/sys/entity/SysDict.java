package com.lin.sys.entity;


import java.util.Date;

import com.lin.entity.BaseEntity;

public class SysDict extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String type;	
	private String label;	
	private String value;	
	private String description;	
	private String rank;	
	
	public SysDict() {
		super();
	}
	
    public SysDict(String type, String label, String value, String description,
			String rank) {
		super();
		this.type = type;
		this.label = label;
		this.value = value;
		this.description = description;
		this.rank = rank;
	}

	public String getType() {
    	return type;
    }
    public void setType(String type) {
    	this.type = type;
    }
    public String getLabel() {
    	return label;
    }
    public void setLabel(String label) {
    	this.label = label;
    }
    public String getValue() {
    	return value;
    }
    public void setValue(String value) {
    	this.value = value;
    }
    public String getDescription() {
    	return description;
    }
    public void setDescription(String description) {
    	this.description = description;
    }
    public String getRank() {
    	return rank;
    }
    public void setRank(String rank) {
    	this.rank = rank;
    }
	
}