package com.ehl.base.navigation;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class MyNavigationTag extends BodyTagSupport
{
	private static final long serialVersionUID = -4247241487535299862L;

	private String indexColor = "";

	private String countColor = "";

	private String navigationUrl = "";

	private int pageIndex;

	private int pageCount;
	// �Զ������
	private String params = "";

	public int getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}

	public int getPageIndex()
	{
		return pageIndex;
	}

	public void setPageIndex(int pageIndex)
	{
		this.pageIndex = pageIndex;
	}

	@Override
	public int doStartTag() throws JspException
	{
		// ��ȡ�����
		navigationUrl = getParameterUrl();
		// ������ʾҳ������ɫ
		getColor();
		StringBuffer strb = new StringBuffer();

		// ��ҳ
		int front = pageIndex - 4; // ǰ��һ��
		int back = pageIndex + 4; // ����һ��

		if (pageIndex == 1)
		{
			strb.append(" ��ҳ ");
			strb.append(" ��һҳ ");
		}
		else
		{
			strb.append("<a href=" + navigationUrl + "pageIndex=1> ��ҳ </a>");
			strb.append("<a href=" + navigationUrl + "pageIndex="
					+ (pageIndex - 1) + "> ��һҳ </a> ");
		}

		if (pageIndex < 5) // ���������ǰ5ҳ
		{
			if (pageCount <= 10) // ��ҳ������10ҳ
			{
				for (int i = 1; i <= pageCount; i++)
				{
					if (i == pageIndex)
						strb.append(" " + pageIndex + " ");
					else
						strb.append("<a href=" + navigationUrl + "pageIndex="
								+ i + ">[" + i + "]</a>");
				}
			}
			else
			{
				for (int i = 1; i < 10; i++)
				{
					if (i == pageIndex)
						strb.append(" " + pageIndex + " ");
					else
						strb.append("<a href=" + navigationUrl + "pageIndex="
								+ i + ">[" + i + "]</a>");
				}
			}
		}
		else
		{
			if (front >= 1)
			{
				for (int i = 4; i > 0; i--)
					strb.append("<a href=" + navigationUrl + "pageIndex="
							+ (pageIndex - i) + ">[" + (pageIndex - i)
							+ "]</a>");
			}
			else
			{
				for (int i = 1; i < pageIndex; i++)
					strb.append("<a href=" + navigationUrl + "pageIndex=" + i
							+ ">[" + i + "]</a>");
			}

			strb.append(" " + pageIndex + " "); // �ֽ��� eg: 5
			// ,ǰ��һ�ؾ���1234,����һ�ؾ���6789

			if (back <= pageCount)
			{
				for (int i = 1; i < 5; i++)
					strb.append("<a href=" + navigationUrl + "pageIndex="
							+ (pageIndex + i) + ">[" + (pageIndex + i)
							+ "]</a>");
			}
			else
			{
				for (int i = 1; i < pageCount - pageIndex + 1; i++)
					strb.append("<a href=" + navigationUrl + "pageIndex="
							+ (pageIndex + i) + ">[" + (pageIndex + i)
							+ "]</a>");
			}
		}

		if (pageIndex == pageCount)
		{
			strb.append(" ��һҳ ");
			strb.append(" ���һҳ ");
		}
		else
		{
			strb.append(" <a href=" + navigationUrl + "pageIndex="
					+ (pageIndex + 1) + "> ��һҳ </a>");
			strb.append("<a href=" + navigationUrl + "pageIndex=" + pageCount
					+ "> ���һҳ </a>");
		}
		strb.append("  <font color=" + indexColor + ">" + pageIndex
				+ "</font>/<font color=" + countColor + ">" + pageCount
				+ "</font>");
		JspWriter out = pageContext.getOut();
		try
		{
			out.println(strb.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	private void getColor()
	{
		if (indexColor.equals(""))
			indexColor = "red";
		if (countColor.equals(""))
			countColor = "blue";
	}

	private String getParameterUrl()
	{
		System.out.println("params | "+params);
		// û���Զ������
		if (params.equals(""))
			return navigationUrl;
		StringBuffer url = new StringBuffer();
		url.append(navigationUrl).append("?").append(params).append("&");
		return url.toString();
	}

	public String getNavigationUrl()
	{
		return navigationUrl;
	}

	public void setNavigationUrl(String navigationUrl)
	{
		this.navigationUrl = navigationUrl;
	}

	public String getCountColor()
	{
		return countColor;
	}

	public void setCountColor(String countColor)
	{
		this.countColor = countColor;
	}

	public String getIndexColor()
	{
		return indexColor;
	}

	public void setIndexColor(String indexColor)
	{
		this.indexColor = indexColor;
	}

	public String getParams()
	{
		return params;
	}

	public void setParams(String params)
	{
		this.params = params;
	}

}
