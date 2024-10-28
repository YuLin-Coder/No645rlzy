package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.Yuangong;
import com.service.liuService;

public class yuangong_servlet extends HttpServlet
{ 
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
		String type=req.getParameter("type");
		
		if(type.endsWith("yuangongAdd"))
		{
			yuangongAdd(req, res);
		}
		if(type.endsWith("yuangongMana"))
		{
			yuangongMana(req, res);
		}
		if(type.endsWith("yuangongDel"))
		{
			yuangongDel(req, res);
		}
		
		if(type.endsWith("yuangongByOrg"))
		{
			yuangongByOrg(req, res);
		}
		if(type.endsWith("yuangongGet"))
		{
			yuangongGet(req, res);
		}
	}
	
	
	
	
	public void yuangongAdd(HttpServletRequest req,HttpServletResponse res)
	{
		int org_id=Integer.parseInt(req.getParameter("org_id"));
		String bianhao=req.getParameter("bianhao");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String age=req.getParameter("age");
		String zhiwei=req.getParameter("zhiwei");
		String shifoubumenlingdao=req.getParameter("shifoubumenlingdao");
		String loginname=req.getParameter("loginname");
		String loginpw=req.getParameter("loginpw");
		String del="no";
		
		String i=liuService.panduan_bianhao(bianhao);
		System.out.print(i+"%%%");
		if(i.equals("fou"))
		{
			String sql="insert into t_yuangong(org_id,bianhao,name,sex,age,zhiwei,shifoubumenlingdao,loginname,loginpw,del) values(?,?,?,?,?,?,?,?,?,?)";
			Object[] params={org_id,bianhao,name,sex,age,zhiwei,shifoubumenlingdao,loginname,loginpw,del};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			req.setAttribute("message", "操作成功");
			req.setAttribute("path", "yuangong?type=yuangongMana");
			
	        String targetURL = "/common/success.jsp";
			dispatch(targetURL, req, res);
		}
		else
		{
			req.setAttribute("message", "工行重复，请重新输入");
			req.setAttribute("path", "admin/yuangong/yuangongAdd.jsp");
			
	        String targetURL = "/common/success.jsp";
			dispatch(targetURL, req, res);
		}
		
	}
	
	
	
	public void yuangongDel(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		
		String sql="update t_yuangong set del=? where id=?";
		Object[] params={"yes",id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "yuangong?type=yuangongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	
	public void yuangongMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yuangongList=new ArrayList();
		String sql="select * from t_yuangong where del='no' order by org_id desc";
		Object[] params={};
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
		
		req.setAttribute("yuangongList", yuangongList);
		req.getRequestDispatcher("admin/yuangong/yuangongMana.jsp").forward(req, res);
	}
	
	
	public void yuangongByOrg(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yuangongList=new ArrayList();
		String sql="select * from t_yuangong where del='no' and org_id=? order by org_id desc";
		Object[] params={Integer.parseInt(req.getParameter("org_id"))};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Yuangong yuangong=new Yuangong();
				
				yuangong.setId(rs.getInt("id"));
				yuangong.setBianhao(rs.getString("bianhao"));
				yuangong.setName(rs.getString("name"));
				yuangong.setSex(rs.getString("sex"));
				yuangong.setAge(rs.getString("age"));
				yuangong.setZhiwei(rs.getString("zhiwei"));
				yuangong.setShifoubumenlingdao(rs.getString("shifoubumenlingdao"));
				yuangong.setLoginname(rs.getString("loginname"));
				yuangong.setLoginpw(rs.getString("loginpw"));
				yuangong.setOrg_id(rs.getInt("org_id"));
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
		
		req.setAttribute("yuangongList", yuangongList);
		req.getRequestDispatcher("admin/yuangong/yuangongByOrg.jsp").forward(req, res);
	}
	
	
	public void yuangongGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		Yuangong yuangong=liuService.getYuangong(Integer.parseInt(req.getParameter("yuangong_id")));
		String s=yuangong.getName()+"###"+yuangong.getOrganization().getName()+"###"+yuangong.getBianhao();
		res.getWriter().print(s);
	}
	
	
	/*public void yuangongEdit(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String age=req.getParameter("age");
		String tel=req.getParameter("tel");
		String address=req.getParameter("address");
		String zhiwei=req.getParameter("zhiwei");
		
		
		String sql="update t_yuangong set name=?,sex=?,age=?,tel=?,address=?,zhiwei=? where id="+id;
		Object[] params={name,sex,age,tel,address,zhiwei};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "yuangong?type=yuangongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}*/
	
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
