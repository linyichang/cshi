package com.lin.sys.entity;


public class SysColumn {
	private String attrName;		
	private String columnName;		
	private String dataType;		
	private String columnType;		
	private String isNullable;		
	private String columnComment;	
	private String rank;			
	private String javaType;		
	private String getMethod;		
	private String setMethod;	
	
	public SysColumn() {
		super();
	}
	
	public SysColumn(String attrName, String columnName, String dataType,
			String columnType, String isNullable, String columnComment,
			String rank) {
		super();
		this.attrName = attrName;
		this.columnName = columnName;
		this.dataType = dataType;
		this.columnType = columnType;
		this.isNullable = isNullable;
		this.columnComment = columnComment;
		this.rank = rank;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getGetMethod() {
		return getMethod;
	}
	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}
	public String getSetMethod() {
		return setMethod;
	}
	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	@Override
	public String toString() {
		return "SysColumn [attrName=" + attrName + ", columnName=" + columnName
				+ ", dataType=" + dataType + ", columnType=" + columnType
				+ ", isNullable=" + isNullable + ", columnComment="
				+ columnComment + ", rank=" + rank + ", javaType=" + javaType
				+ ", getMethod=" + getMethod + ", setMethod=" + setMethod + "]";
	}
	
	
	
}
