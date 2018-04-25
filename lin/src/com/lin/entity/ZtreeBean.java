package com.lin.entity;

public class ZtreeBean {

	private String id;
	private String pId;
	private String name;
	private boolean isParent;
	private boolean open;
	private boolean nocheck;
	


	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getisParent() {
		return isParent;
	}
	public void setisParent(boolean isParent) {
		this.isParent = isParent;
	}
	
}
