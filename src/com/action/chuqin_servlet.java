package com.action;

import java.io.IOException;
import java.sql.ResultSet;
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
import com.orm.Chuqin;
import com.orm.TAdmin;
import com.orm.Yuangong;
import com.service.liuService;

public class chuqin_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("chuqinAdd"))
		{
			chuqinAdd(req, res);
		}
		if(type.endsWith("chuqinMana"))
		{
			chuqinMana(req, res);
		}
		if(type.endsWith("chuqinDel"))
		{
			chuqinDel(req, res);
		}
		
	}
	
	
	public void chuqinAdd(HttpServletRequest req,HttpServletResponse res)
	{
		
			String yuefen=req.getParameter("yuefen");
			int tianshu=Integer.parseInt(req.getParameter("tianshu"));
			int yuangong_id=Integer.parseInt(req.getParameter("yuangong_id"));
			
			
			String sql="insert into t_chuqin(yuefen,tianshu,yuangong_id) values(?,?,?)";
			Object[] params={yuefen,tianshu,yuangong_id};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			req.setAttribute("msg", "出勤录入完毕。请点击出勤管理查看");
			String targetURL = "/common/msg.jsp";
			dispatch(targetURL, req, res);
		
	}
	
	

	public void chuqinMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		
			List chuqinList=new ArrayList();
			String sql="select * from t_chuqin order by yuefen desc";
			
			Object[] params={};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				while(rs.next())
				{
					Chuqin chuqin=new Chuqin();
					
					chuqin.setId(rs.getInt("id"));
					chuqin.setYuefen(rs.getString("yuefen"));
					chuqin.setTianshu(rs.getInt("tianshu"));
					chuqin.setYuangong_id(rs.getInt("yuangong_id"));
					chuqin.setYuangong(liuService.getYuangong(rs.getInt("yuangong_id")));
					
					chuqinList.add(chuqin);
			    }
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
			
			req.setAttribute("chuqinList", chuqinList);
			req.getRequestDispatcher("admin/chuqin/chuqinMana.jsp").forward(req, res);
	}
	
	
	public void chuqinDel(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		
		String sql="delete from t_chuqin where id=?";
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
