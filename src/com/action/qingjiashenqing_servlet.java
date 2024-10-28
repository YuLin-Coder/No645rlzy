package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.Tqingjiashenqing;
import com.orm.Yuangong;
import com.service.liuService;

public class qingjiashenqing_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("qingjiashenqingAdd"))
		{
			qingjiashenqingAdd(req, res);
		}
		if(type.endsWith("qingjiashenqingMana_me"))
		{
			qingjiashenqingMana_me(req, res);
		}
		if(type.endsWith("qingjiashenqingDel"))
		{
			qingjiashenqingDel(req, res);
		}
		if(type.endsWith("qingjiashenqingMana"))
		{
			qingjiashenqingMana(req, res);
		}
		if(type.endsWith("qingjiashenqing_shenhe"))
		{
			qingjiashenqing_shenhe(req, res);
		}
	}
	
	
	public void qingjiashenqingAdd(HttpServletRequest req,HttpServletResponse res)
	{
		
		String kaishishijian=req.getParameter("kaishishijian");
		String jieshushijian=req.getParameter("jieshushijian");
		String beizhu=req.getParameter("beizhu");
		int yuangong_id=Integer.parseInt(req.getParameter("yuangong_id"));
		String shenhezhuangtai="a";
		String huifuxinxi="";
		
		
		String sql="insert into t_qingjiashenqing values(?,?,?,?,?,?)";
		Object[] params={kaishishijian,jieshushijian,beizhu,yuangong_id,shenhezhuangtai,huifuxinxi};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "qingjiashenqing?type=qingjiashenqingMana_me&yuangong_id="+yuangong_id);
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	
	public void qingjiashenqingDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_qingjiashenqing where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}

	//我的请假
	public void qingjiashenqingMana_me(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		Yuangong yuangong=(Yuangong)session.getAttribute("yuangong");
		
		List qingjiashenqingList=new ArrayList();
		String sql="select * from t_qingjiashenqing where yuangong_id=? order by shenhezhuangtai";
		Object[] params={yuangong.getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tqingjiashenqing qingjiashenqing=new Tqingjiashenqing();
				
				qingjiashenqing.setId(rs.getInt("id"));
				qingjiashenqing.setKaishishijian(rs.getString("kaishishijian"));
				qingjiashenqing.setJieshushijian(rs.getString("jieshushijian"));
				qingjiashenqing.setYuangong_id(rs.getInt("yuangong_id"));
				qingjiashenqing.setBeizhu(rs.getString("beizhu"));
				qingjiashenqing.setShenhezhuangtai(rs.getString("shenhezhuangtai"));
				qingjiashenqing.setHuifuxinxi(rs.getString("huifuxinxi"));
				
				
				qingjiashenqingList.add(qingjiashenqing);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("qingjiashenqingList", qingjiashenqingList);
		req.getRequestDispatcher("admin/qingjiashenqing/qingjiashenqingMana_me.jsp").forward(req, res);
	}
	
	
	
	
	//部门领导请假审核管理
	public void qingjiashenqingMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
			List qingjiashenqingList=new ArrayList();
			String sql="select * from t_qingjiashenqing order by shenhezhuangtai";
			Object[] params={};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				while(rs.next())
				{
					Tqingjiashenqing qingjiashenqing=new Tqingjiashenqing();
					
					qingjiashenqing.setId(rs.getInt("id"));
					qingjiashenqing.setKaishishijian(rs.getString("kaishishijian"));
					qingjiashenqing.setJieshushijian(rs.getString("jieshushijian"));
					qingjiashenqing.setYuangong_id(rs.getInt("yuangong_id"));
					qingjiashenqing.setBeizhu(rs.getString("beizhu"));
					qingjiashenqing.setShenhezhuangtai(rs.getString("shenhezhuangtai"));
					qingjiashenqing.setHuifuxinxi(rs.getString("huifuxinxi"));
					
					qingjiashenqing.setYuangong(liuService.getYuangong(rs.getInt("yuangong_id")));
					
					qingjiashenqingList.add(qingjiashenqing);
			    }
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
			
			req.setAttribute("qingjiashenqingList", qingjiashenqingList);
			req.getRequestDispatcher("admin/qingjiashenqing/qingjiashenqingMana.jsp").forward(req, res);
	}
	
	
	
	//部门领导申请请假信息
	public void qingjiashenqing_shenhe(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		String shenhezhuangtai=req.getParameter("shenhezhuangtai");
		String huifuxinxi=req.getParameter("huifuxinxi");
		
		String sql="update t_qingjiashenqing set shenhezhuangtai=?,huifuxinxi=? where id=?";
		Object[] params={shenhezhuangtai,huifuxinxi,id};
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
