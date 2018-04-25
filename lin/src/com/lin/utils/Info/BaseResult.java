package com.lin.utils.Info;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BaseResult", description = "���ؼ���")
public class BaseResult {

	// �����룬0��ʾ�ɹ�����0��ʾʧ��
	@ApiModelProperty(notes = "�����룬0��ʾ�ɹ�����0��ʾʧ��")
	private int code;

	// ������Ϣ���ɹ�Ϊ��success����ʧ��Ϊ����ʧ����Ϣ
	@ApiModelProperty(notes = "������Ϣ���ɹ�Ϊ��success����ʧ��Ϊ����ʧ����Ϣ")
	private String msg;

	@ApiModelProperty(notes = "����")
	private Integer count;

	// ��������
	@ApiModelProperty(notes = "���صľ�������")
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
