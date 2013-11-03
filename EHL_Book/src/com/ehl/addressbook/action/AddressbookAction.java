package com.ehl.addressbook.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.appframe.utils.StringHelper;
import com.ehl.addressbook.dao.Addressbookdao;
import com.ehl.addressbook.domain.Thing;
import com.ehl.addressbook.domain.User;
import com.ehl.base.action.BaseAction;
import com.ehl.base.navigation.Navigation;
import com.ehl.base.util.AllPagesUtil;

/**
 * 通讯录action
 */
public class AddressbookAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Addressbookdao usersDao = new Addressbookdao();
	/**
	 * 通讯录更加名字模糊搜索
	 */
	public void getkeyVals() {
		try {
			String keyWord = StringHelper.obj2str(request.getParameter("keyWord"),"");//用户名
			List<User> userlist = usersDao.getkeyVals(keyWord);
			Map<String, List<?>> map = new HashMap<String, List<?>>();
			map.put("users", userlist);//用户集合
			String jsonMap = jsonBuilder.buildMap(map, "");
			toWrite(jsonMap);
		} catch (Exception e) {
			toWrite(jsonBuilder.returnFailureJson("'通讯录更加名字模糊搜索,错误信息："
					+ e.getMessage() + "'"));
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 通讯录列表
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void getAddressbookList() {
		// 总页数从数据库获得
		String pageIndex = "";
		String params = "";

		try {
		if (!request.getParameter("pageIndex").toString().trim().equals(""))
			pageIndex = request.getParameter("pageIndex").toString();

		System.out.println("当前所在页数pageIndex= " + pageIndex);

		// 设置用户自定义的参数
		// 注意这里设置的下面要对应的取值
		params = "key1=value1&key2=value2";
		if (!"".equals(request.getParameter("key1")))
			System.out.println("取自定义参数1params= "
					+ request.getParameter("key1").toString());
		if (!"".equals(request.getParameter("key2")))
			System.out.println("取自定义参数2params= "
					+ request.getParameter("key2").toString());

		//在界面我就用jstl进行迭代
		request.setAttribute("list", getList(Integer.parseInt(pageIndex)));
		// 把所有参数设置在bean里在jsp页面取出来
		Navigation nav = new Navigation();
		// 这两个参数是当前页数的颜色 和 总页数的颜色 ｅｇ：10(这是当前页数)/100(这是总页数)
		// 设置了这个页面还要进行对应的设置
		// nav.setIndexColor("red");
		// nav.setCountColor("blue");
		
		//设置总页数
		String count = Integer.toString((int)java.lang.Math.ceil((double)navigationBean().size()/10));
		System.out.println("总页数 : " + count);
		nav.setPageCount(count);
		nav.setPageIndex(pageIndex);
		nav.setParams(params);
		request.setAttribute("nav", nav);
			request.getRequestDispatcher("/addressbook.jsp").forward(request,
					response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Thing> getList(int page)
    {
    	return navigationBean().subList(10*(page-1), 10*page);
    }

	// 一个分页的例子
    // 这里的集合就当数据库的吧~!
	public static List<Thing> navigationBean()
	{
		List<Thing> list = new ArrayList<Thing>();	
		for(int i=0;i<100;i++)
		{
			Thing thing = new Thing(Integer.toString(i), "name" + i, "img/test.jpg");
			list.add(thing);
		}		
		return list;
	}
	/**
	 * 通讯录明细
	 */
	public void getControlTypeCompare() {
		String xzqh = StringHelper.obj2str(request.getParameter("xzqh"),"");
		String cptype = StringHelper.obj2str(request.getParameter("hbType"),"");//环比类型
		int cpnum = StringHelper.obj2int(request.getParameter("hbNum"),0);//环比数量
		try {
		} catch (Exception e) {
			 toWrite(jsonBuilder.returnFailureJson("'通讯录更加名字模糊搜索,错误信息："
			 + e.getMessage() + "'"));
		}
	}
	private int treeNodeId;//保存树节点的Id
	/**
	 * 返回树的根节点的Json格式数据
	 * @return
	 */
	public String getNodesDataById(){
		System.out.println("treeNodeId = "+treeNodeId);
		String iconPath = ",icon:\"zTree/css/zTreeStyle/img/diy/3.png\"";
		String nodesJson = "";
		if (treeNodeId == 1) {
			nodesJson = "[{id:11, pId:"+treeNodeId+", name: \"廊坊市交警支队\",isParent:false"+iconPath+"}," +
            "{id:12, pId:"+treeNodeId+", name: \"一大队\",isParent:false"+iconPath+"}," +
            "{id:13, pId:"+treeNodeId+", name: \"二大队\",isParent:false"+iconPath+"}" +
            "{id:13, pId:"+treeNodeId+", name: \"三大队\",isParent:false"+iconPath+"}" +
            "]";
		}else{
			nodesJson = "[]";//其他情况：父节点没有子节点
		}
//		toWrite(nodesJson);
		getPrintWriter().write(nodesJson);
		return SUCCESS;
	}
	/**
	 * 获得HttpServletResponse对象
	 * @return
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		return response;
	}
	public PrintWriter getPrintWriter() {
		PrintWriter pw = null;
		try {
			pw = getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pw;
	}
	public int getTreeNodeId() {
		return treeNodeId;
	}

	public void setTreeNodeId(int treeNodeId) {
		this.treeNodeId = treeNodeId;
	}
	@Override
	public void setCurrList(int curPage, int pageSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrAllPage(int pageSize) {
		// TODO Auto-generated method stub
		allPage = AllPagesUtil.getPages(10,pageSize)  ;
		this.fillShowNumber() ;
	}
	
	
	public String go(){
		this.setCurrAllPage(pageSize) ;
		return SUCCESS ;
	}
}
