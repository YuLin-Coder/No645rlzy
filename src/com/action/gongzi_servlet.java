package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DB;
import com.orm.Gongzi;
import com.orm.Yuangong;
import com.service.liuService;

public class gongzi_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("gongziAdd"))
		{
			gongziAdd(req, res);
		}
		if(type.endsWith("gongziMana"))
		{
			gongziMana(req, res);
		}
		if(type.endsWith("gongziDel"))
		{
			gongziDel(req, res);
		}
		
		if(type.endsWith("gongziMana_me"))
		{
			gongziMana_me(req, res);
		}
		
	}
	
	
	public void gongziAdd(HttpServletRequest req,HttpServletResponse res)
	{
		
			String yuefen=req.getParameter("yuefen");
			int jine=Integer.parseInt(req.getParameter("jine"));
			int yuangong_id=Integer.parseInt(req.getParameter("yuangong_id"));
			
			
			String sql="insert into t_gongzi(yuefen,jine,yuangong_id) values(?,?,?)";
			Object[] params={yuefen,jine,yuangong_id};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			req.setAttribute("msg", "工资录入完毕。请点击工资管理查看");
			String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		
	}
	
	

	public void gongziMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
			List gongziList=new ArrayList();
			String sql="select * from t_gongzi order by yuefen desc";
			System.out.println(sql+"^^");
			Object[] params={};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				while(rs.next())
				{
					Gongzi gongzi=new Gongzi();
					
					gongzi.setId(rs.getInt("id"));
					gongzi.setYuefen(rs.getString("yuefen"));
					gongzi.setJine(rs.getInt("jine"));
					gongzi.setYuangong_id(rs.getInt("yuangong_id"));
					gongzi.setYuangong(liuService.getYuangong(rs.getInt("yuangong_id")));
					
					gongziList.add(gongzi);
			    }
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
			
			req.setAttribute("gongziList", gongziList);
			req.getRequestDispatcher("admin/gongzi/gongziMana.jsp").forward(req, res);
	}
	
	
	public void gongziMana_me(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		Yuangong yuangong=(Yuangong)session.getAttribute("yuangong");
		
		List gongziList=new ArrayList();
		String sql="select * from t_gongzi where yuangong_id=?";
		Object[] params={yuangong.getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Gongzi gongzi=new Gongzi();
				
				gongzi.setId(rs.getInt("id"));
				gongzi.setYuefen(rs.getString("yuefen"));
				gongzi.setJine(rs.getInt("jine"));
				gongzi.setYuangong_id(rs.getInt("yuangong_id"));
				gongzi.setYuangong(liuService.getYuangong(rs.getInt("yuangong_id")));
				
				gongziList.add(gongzi);				
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongziList", gongziList);
		req.getRequestDispatcher("admin/gongzi/gongziMana_me.jsp").forward(req, res);
	}
	
	public void gongziDel(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		
		String sql="delete from t_gongzi where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
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
