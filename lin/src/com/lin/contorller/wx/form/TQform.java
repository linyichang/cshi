package com.lin.contorller.wx.form;

import java.util.List;

public class TQform {

	private String date;
	private String message;
	private String status;
	private String city;
	private TQdata data;
	
	public TQdata getData() {
		return data;
	}
	public void setData(TQdata data) {
		this.data = data;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	private String count;

}
