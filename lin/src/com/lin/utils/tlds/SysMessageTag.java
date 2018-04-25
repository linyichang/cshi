package com.lin.utils.tlds;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SysMessageTag extends TagSupport{

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.append("<h1>ÄãºÃÂð</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}
}
