package com.lin.sys.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.lin.utils.SysPage;

public class SysPageTag extends TagSupport {

	private SysPage page;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		try {
			if (page == null) {
				out.println("");
				return SKIP_BODY;
			}
			out.println("<ul class='pagination' style='margin:0px;margin-top:10px;'>");
			if ((page.getPage() + 1) == 1) {
				out.println("<li><a href='#' style='background-color:rgba(0,0,0,0.1);color:rgba(0,0,0,0.2);'>上一页</a></li>");
			} else {
				out.println("<li><a href='" + page.getUrl() + "?first=" + (page.getPrevPage() * page.getPageSize()) + "'>上一页</a></li>");
			}
			out.println("<li class='disabled'><a href='javascript:;'>" + (page.getPage() + 1) + "</a></li>");
			if ((page.getPage() + 1) == page.getPageTotal()) {
				out.println("<li><a href='#' style='background-color:rgba(0,0,0,0.1);color:rgba(0,0,0,0.2);'>下一页</a></li>");
			} else {
				out.println("<li><a href='" + page.getUrl() + "?first=" + (page.getNextPage() * page.getPageSize()) + "'>下一页</a></li>");
			}
			out.println("<li class='disabled'><a href='javascript:;'>共&nbsp;" + page.getPageTotal() + "&nbsp;页，&nbsp;" + page.getCount() + "&nbsp;条</a></li>");
			out.println("</ul>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
	}

	public SysPage getPage() {
		return page;
	}

	public void setPage(SysPage page) {
		this.page = page;
	}

}
