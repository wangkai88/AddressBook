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
 * ͨѶ¼action
 */
public class AddressbookAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Addressbookdao usersDao = new Addressbookdao();
	/**
	 * ͨѶ¼��������ģ������
	 */
	public void getkeyVals() {
		try {
			String keyWord = StringHelper.obj2str(request.getParameter("keyWord"),"");//�û���
			List<User> userlist = usersDao.getkeyVals(keyWord);
			Map<String, List<?>> map = new HashMap<String, List<?>>();
			map.put("users", userlist);//�û�����
			String jsonMap = jsonBuilder.buildMap(map, "");
			toWrite(jsonMap);
		} catch (Exception e) {
			toWrite(jsonBuilder.returnFailureJson("'ͨѶ¼��������ģ������,������Ϣ��"
					+ e.getMessage() + "'"));
			System.out.println(e.getMessage());
		}
	}
	/**
	 * ͨѶ¼�б�
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void getAddressbookList() {
		// ��ҳ�������ݿ���
		String pageIndex = "";
		String params = "";

		try {
		if (!request.getParameter("pageIndex").toString().trim().equals(""))
			pageIndex = request.getParameter("pageIndex").toString();

		System.out.println("��ǰ����ҳ��pageIndex= " + pageIndex);

		// �����û��Զ���Ĳ���
		// ע���������õ�����Ҫ��Ӧ��ȡֵ
		params = "key1=value1&key2=value2";
		if (!"".equals(request.getParameter("key1")))
			System.out.println("ȡ�Զ������1params= "
					+ request.getParameter("key1").toString());
		if (!"".equals(request.getParameter("key2")))
			System.out.println("ȡ�Զ������2params= "
					+ request.getParameter("key2").toString());

		//�ڽ����Ҿ���jstl���е���
		request.setAttribute("list", getList(Integer.parseInt(pageIndex)));
		// �����в���������bean����jspҳ��ȡ����
		Navigation nav = new Navigation();
		// �����������ǵ�ǰҳ������ɫ �� ��ҳ������ɫ ��磺10(���ǵ�ǰҳ��)/100(������ҳ��)
		// ���������ҳ�滹Ҫ���ж�Ӧ������
		// nav.setIndexColor("red");
		// nav.setCountColor("blue");
		
		//������ҳ��
		String count = Integer.toString((int)java.lang.Math.ceil((double)navigationBean().size()/10));
		System.out.println("��ҳ�� : " + count);
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

	// һ����ҳ������
    // ����ļ��Ͼ͵����ݿ�İ�~!
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
	 * ͨѶ¼��ϸ
	 */
	public void getControlTypeCompare() {
		String xzqh = StringHelper.obj2str(request.getParameter("xzqh"),"");
		String cptype = StringHelper.obj2str(request.getParameter("hbType"),"");//��������
		int cpnum = StringHelper.obj2int(request.getParameter("hbNum"),0);//��������
		try {
		} catch (Exception e) {
			 toWrite(jsonBuilder.returnFailureJson("'ͨѶ¼��������ģ������,������Ϣ��"
			 + e.getMessage() + "'"));
		}
	}
	private int treeNodeId;//�������ڵ��Id
	/**
	 * �������ĸ��ڵ��Json��ʽ����
	 * @return
	 */
	public String getNodesDataById(){
		System.out.println("treeNodeId = "+treeNodeId);
		String iconPath = ",icon:\"zTree/css/zTreeStyle/img/diy/3.png\"";
		String nodesJson = "";
		if (treeNodeId == 1) {
			nodesJson = "[{id:11, pId:"+treeNodeId+", name: \"�ȷ��н���֧��\",isParent:false"+iconPath+"}," +
            "{id:12, pId:"+treeNodeId+", name: \"һ���\",isParent:false"+iconPath+"}," +
            "{id:13, pId:"+treeNodeId+", name: \"�����\",isParent:false"+iconPath+"}" +
            "{id:13, pId:"+treeNodeId+", name: \"�����\",isParent:false"+iconPath+"}" +
            "]";
		}else{
			nodesJson = "[]";//������������ڵ�û���ӽڵ�
		}
//		toWrite(nodesJson);
		getPrintWriter().write(nodesJson);
		return SUCCESS;
	}
	/**
	 * ���HttpServletResponse����
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
