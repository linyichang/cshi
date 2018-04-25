package com.lin.sys.entity;

import java.util.List;

public class SysTable {
	private String tableName;		
	private String tableNameCH;		
	private String className;		
	private String lowerName;		
	private List<SysColumn> columnList;		
	
	private String permView;
	private String permEdit;
	private String permDelete;
	
	public SysTable() {
		super();
	}
	
	public SysTable(String tableName, String tableNameCH,String className, String lowerName, List<SysColumn> columnList) {
		super();
		this.tableName = tableName;
		this.tableNameCH = tableNameCH;
		this.className = className;
		this.lowerName = lowerName;
		this.columnList = columnList;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<SysColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<SysColumn> columnList) {
		this.columnList = columnList;
	}
	public String getLowerName() {
		return lowerName;
	}
	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public String getTableNameCH() {
		return tableNameCH;
	}

	public void setTableNameCH(String tableNameCH) {
		this.tableNameCH = tableNameCH;
	}

	public String getPermView() {
		return permView;
	}

	public void setPermView(String permView) {
		this.permView = permView;
	}

	public String getPermEdit() {
		return permEdit;
	}

	public void setPermEdit(String permEdit) {
		this.permEdit = permEdit;
	}

	public String getPermDelete() {
		return permDelete;
	}

	public void setPermDelete(String permDelete) {
		this.permDelete = permDelete;
	}

	
}
