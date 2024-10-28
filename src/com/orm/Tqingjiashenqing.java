package com.orm;

public class Tqingjiashenqing
{

	private int id;
	private String kaishishijian;
	private String jieshushijian;
	private String beizhu;
	private int yuangong_id;
	private String shenhezhuangtai;//a-审核中，b-审核通过。c-审核未通过
	private String huifuxinxi;
	
	private Yuangong yuangong;
	
	
	
	
	public String getBeizhu()
	{
		return beizhu;
	}
	public void setBeizhu(String beizhu)
	{
		this.beizhu = beizhu;
	}
	public String getHuifuxinxi()
	{
		return huifuxinxi;
	}
	
	public Yuangong getYuangong()
	{
		return yuangong;
	}
	public void setYuangong(Yuangong yuangong)
	{
		this.yuangong = yuangong;
	}
	public void setHuifuxinxi(String huifuxinxi)
	{
		this.huifuxinxi = huifuxinxi;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getShenhezhuangtai()
	{
		return shenhezhuangtai;
	}
	public void setShenhezhuangtai(String shenhezhuangtai)
	{
		this.shenhezhuangtai = shenhezhuangtai;
	}
	public int getYuangong_id()
	{
		return yuangong_id;
	}
	public void setYuangong_id(int yuangong_id)
	{
		this.yuangong_id = yuangong_id;
	}
	public String getJieshushijian()
	{
		return jieshushijian;
	}
	public void setJieshushijian(String jieshushijian)
	{
		this.jieshushijian = jieshushijian;
	}
	public String getKaishishijian()
	{
		return kaishishijian;
	}
	public void setKaishishijian(String kaishishijian)
	{
		this.kaishishijian = kaishishijian;
	}
	
}
