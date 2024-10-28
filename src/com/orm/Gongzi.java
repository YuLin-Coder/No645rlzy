package com.orm;

public class Gongzi
{
	private int id;
	private String yuefen;
	private int jine;
	private int yuangong_id;
	
	private Yuangong yuangong;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getJine()
	{
		return jine;
	}
	public void setJine(int jine)
	{
		this.jine = jine;
	}
	public int getYuangong_id()
	{
		return yuangong_id;
	}
	public void setYuangong_id(int yuangong_id)
	{
		this.yuangong_id = yuangong_id;
	}
	public String getYuefen()
	{
		return yuefen;
	}
	public void setYuefen(String yuefen)
	{
		this.yuefen = yuefen;
	}
	public Yuangong getYuangong()
	{
		return yuangong;
	}
	public void setYuangong(Yuangong yuangong)
	{
		this.yuangong = yuangong;
	}
	
	
}
