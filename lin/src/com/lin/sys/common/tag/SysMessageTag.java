package com.lin.sys.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SysMessageTag extends TagSupport {

	private SysMessage message;

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			if (message != null) {
				String state = null;
				if ("success".equals(message.getType())) {
					state = "alert-success";
				} else {
					state = "alert-warning";
				}
				out.append("<div id='myAlert' class='alert "+state+"' style='position: absolute;width: 100%;'>");
				out.append("<a href='#' class='close' data-dismiss='alert'>&times;</a> 结果是:<strong>"+message.getMessage()+"</strong>");
				out.append("</div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	@Override
	public void release() {
		super.release();
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public SysMessage getMessage() {
		return message;
	}

	public void setMessage(SysMessage message) {
		this.message = message;
	}

}
