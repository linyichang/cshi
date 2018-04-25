package com.lin.utils.Info;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class InfoJson<T> {

	private Integer code;
	
	private T msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getMsg() {
		return msg;
	}

	public void setMsg(T msg) {
		this.msg = msg;
	}

	public InfoJson(Integer code, T msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public InfoJson(T msg) {
		super();
		this.msg = msg;
	}

	public InfoJson() {
		super();
	}
	
}
