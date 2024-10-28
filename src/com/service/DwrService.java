package com.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.dao.DB;
import com.orm.Yuangong;


public class DwrService
{
	public String getForwardHTMLPage(String nodeId) throws ServletException, IOException
	{
		for(int i=0;i<20000;i++)
		{
			System.out.println(i);
		}
		
		
		WebContext wctx = WebContextFactory.get();
		wctx.getHttpServletRequest().setCharacterEncoding("UTF-8");
	    wctx.getHttpServletResponse().setCharacterEncoding("UTF-8");
	    wctx.getHttpServletResponse().setContentType("text/html;charset=UTF-8");
	    
	   
	    
	    List yuangongList=new ArrayList();
		String sql="select * from t_yuangong where del='no' and org_id=?";
		Object[] params={Integer.parseInt(nodeId)};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Yuangong yuangong=new Yuangong();
				
				yuangong.setId(rs.getInt("id"));
				yuangong.setOrg_id(rs.getInt("org_id"));
				yuangong.setBianhao(rs.getString("bianhao"));
				yuangong.setName(rs.getString("name"));
				
				yuangong.setSex(rs.getString("sex"));
				yuangong.setAge(rs.getString("age"));
				yuangong.setZhiwei(rs.getString("zhiwei"));
				yuangong.setShifoubumenlingdao(rs.getString("shifoubumenlingdao"));
				
				yuangong.setLoginname(rs.getString("loginname"));
				yuangong.setLoginpw(rs.getString("loginpw"));
				yuangong.setDel(rs.getString("del"));
				
				yuangong.setOrganization(liuService.getOrg(rs.getInt("org_id")));
				
				yuangongList.add(yuangong);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		wctx.getHttpServletRequest().setAttribute("yuangongList", yuangongList);
	    String result = wctx.forwardToString("/admin/yuangong/yuangongByOrg.jsp");
	    return result;
	}
	
}
