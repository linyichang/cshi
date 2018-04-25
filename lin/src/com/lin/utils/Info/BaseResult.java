package com.lin.utils.Info;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BaseResult", description = "返回集合")
public class BaseResult {

	// 返回码，0表示成功，非0表示失败
	@ApiModelProperty(notes = "返回码，0表示成功，非0表示失败")
	private int code;

	// 返回消息，成功为“success”，失败为具体失败信息
	@ApiModelProperty(notes = "返回消息，成功为“success”，失败为具体失败信息")
	private String msg;

	@ApiModelProperty(notes = "数量")
	private Integer count;

	// 返回数据
	@ApiModelProperty(notes = "返回的具体数据")
	private Object data;

	public BaseResult() {
		super();
	}

	public BaseResult(Integer count, Object data) {
		super();
		this.count = count;
		this.data = data;
	}

	public BaseResult(Object data) {
		super();
		this.data = data;
	}

	public BaseResult(int code, String msg, Integer count, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
