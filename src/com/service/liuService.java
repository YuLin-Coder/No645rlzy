package com.service;

import java.sql.ResultSet;
import com.dao.DB;
import com.orm.TOrganization;
import com.orm.Yuangong;

public class liuService
{

	public static TOrganization getOrg(int id)
	{
		TOrganization organization=new TOrganization();
		
		if(id==0)
		{
			organization.setId(0);
		}
		else
		{
			String sql="select * from t_organization where id=?";
			Object[] params={id};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				rs.next();
				organization.setId(rs.getInt("id"));
				organization.setName(rs.getString("name"));
				organization.setDescription(rs.getString("description"));
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
		}
		
		return organization;
	}
	
	
	
	public static Yuangong getYuangong(int id)
	{
		    Yuangong yuangong=new Yuangong();
		    String sql="select * from t_yuangong where id=?";
			Object[] params={id};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				rs.next();

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
				
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
		
		return yuangong;
	}
	
	
	public static String getYuangong_id_list(int org_id)
	{
		    StringBuffer yuangong_id_list=new StringBuffer("");
		
		    String sql="select * from t_yuangong where org_id=?";
			Object[] params={org_id};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				while(rs.next())
				{
					yuangong_id_list.append(rs.getInt("id"));
					yuangong_id_list.append(",");
				}
				
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
			
		    return yuangong_id_list.substring(0,yuangong_id_list.length()-1);
	}
	
	
	public static String panduan_bianhao(String bianhao)
	{
		    String i="";
		    
		    String sql="select * from t_yuangong where bianhao=?";
			Object[] params={bianhao.trim()};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				i=(rs==null||!rs.next()?"fou":"shi");
				
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
		
		return i;
	}
	
	
}
