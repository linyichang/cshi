package com.lin.sys.store.entity;

import com.lin.entity.BaseEntity;

public class Store  extends BaseEntity{

	private String name;
	
	private Integer inventory;
	
	private String coding;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	@Override
	public String toString() {
		return "Store [name=" + name + ", inventory=" + inventory + ", coding=" + coding + "]";
	}
	
	
}
