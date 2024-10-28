package com.orm;

public class Yuangong
{
	private int id;
	private int org_id;
	private String bianhao;
	private String name;
	
	private String sex;
	private String age;
	private String zhiwei;
	private String shifoubumenlingdao;
	private String loginname;
	private String loginpw;
	private String del;
	
	private TOrganization organization;
	
	
	public String getBianhao()
	{
		return bianhao;
	}
	public void setBianhao(String bianhao)
	{
		this.bianhao = bianhao;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getZhiwei()
	{
		return zhiwei;
	}
	public void setZhiwei(String zhiwei)
	{
		this.zhiwei = zhiwei;
	}
	public String getName()
	{
		return name;
	}
	
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	public String getShifoubumenlingdao()
	{
		return shifoubumenlingdao;
	}
	public void setShifoubumenlingdao(String shifoubumenlingdao)
	{
		this.shifoubumenlingdao = shifoubumenlingdao;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSex()
	{
		return sex;
	}
	
	public String getLoginname()
	{
		return loginname;
	}
	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}
	public String getLoginpw()
	{
		return loginpw;
	}
	public void setLoginpw(String loginpw)
	{
		this.loginpw = loginpw;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getDel()
	{
		return del;
	}
	public void setDel(String del)
	{
		this.del = del;
	}
	public int getOrg_id()
	{
		return org_id;
	}
	public void setOrg_id(int org_id)
	{
		this.org_id = org_id;
	}
	public TOrganization getOrganization()
	{
		return organization;
	}
	public void setOrganization(TOrganization organization)
	{
		this.organization = organization;
	}
	
	
	
}
