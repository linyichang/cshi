package com.lin.utils;

import java.util.List;

public class SysPage<T> {
	private Integer page = 0;			
	private Integer pageSize = 6;		
	private Integer prevPage = 0;		
	private Integer nextPage = 0;		
	private Integer pageTotal = 0;		
	private Integer count = 0;			
	private List<T> list = null;			
	private String url = null;
	
	public SysPage() {
		super();
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "SysPage [page=" + page + ", pageSize=" + pageSize
				+ ", prevPage=" + prevPage + ", nextPage=" + nextPage
				+ ", pageTotal=" + pageTotal + ", count=" + count + ", url="
				+ url + "]";
	}
	
}
