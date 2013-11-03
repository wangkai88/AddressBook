package com.ehl.base.navigation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页逻辑Bean
 * 
 * @author tangs 
 */
@SuppressWarnings("serial")
public class Pages extends TagSupport {

	private String pageNo;

	private String total;

	private String styleClass;

	private String theme;

	private String url;
	
	private Integer maxPage;
	
	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public int doEndTag(){
		try {
			int countNum = Integer.valueOf(total);
			total = String.valueOf((Integer.valueOf(total)+ maxPage - 1)/ maxPage);
			if("".equals(total)){
				total="0";
			}
			if (!(total.equals("0") || total.equals("1"))) {
				StringBuilder str = new StringBuilder();
				str.append("<div align='right'>");

				Integer cpageInt = Integer.valueOf(pageNo);
				str.append("<span ");
				if (styleClass != null) {
					str.append(" class='" + styleClass + "'>");
				} else {
					str.append(">");
				}
					// 10 > >>]
					Integer totalInt = Integer.valueOf(total);
					// 如果只有一页，则无需分页
					if (totalInt == 1) {
						str.append("<strong>1</strong> ");
					} else {
						if (cpageInt > 1) {
							// 当前不是第一组，要显示“<< <”
							// <<：返回前一组第一页
							// <：返回前一页
							str.append("<a style='text-decoration:none' href='javascript:" + url + "(1);'>[首页]</a> ");
							str.append("<a style='text-decoration:none' href='javascript:" + url + "("+ (cpageInt - 1));
							str.append(");'>[上一页]</a> ");
						} else {
							str.append("[首页] [上一页] ");
						}

						int v = (cpageInt-5);
						int v1 = (cpageInt + 5);
						if(v<=0){
							v=1;
						}
						if(v1>totalInt){
							v1=totalInt;
						}
						// 10个为一组显示
						for (int i = v; i <= v1; i++) {
							if (cpageInt == i) { // 当前页要加粗显示
								str.append("<strong style='color:red;'>" + i + "</strong> ");
							} else {
								str.append("<a style='text-decoration:none' href='javascript:" + url + "(" + i+ ");'>" + i + "</a> ");
							}
						}
						// 如果多于1组并且不是最后一组，显示“> >>”
						if (cpageInt < totalInt) {
							// >>：返回下一组最后一页
							// >：返回下一页
							str.append("<a style='text-decoration:none' href='javascript:" + url + "("+ (cpageInt + 1));
							str.append(");'>[下一页]</a> ");
							str.append("<a style='text-decoration:none' href='javascript:" + url + "("+ totalInt);
							str.append(");'>[末页]</a> ");
						} else {
							str.append("[下一页] [末页] ");
						}
						str.append(" [共 " + total + " 页]");
						str.append(" [共 " + countNum + " 条记录]");
					}
					str.append("</span>");
					str.append("</div>");
					pageContext.getOut().append(str.toString());
				}
				
		} catch (IOException ex) {
			Logger.getLogger(Pages.class.getName()).log(Level.SEVERE, null, ex);
		}
		return EVAL_PAGE;
	}
}
