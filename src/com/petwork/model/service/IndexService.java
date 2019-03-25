package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.IndexDao;
import com.petwork.model.vo.FreePost;
import com.petwork.model.vo.Parcel;
import com.petwork.model.vo.SharingPost;
import com.petwork.model.vo.Slider;

public class IndexService 
{
	Connection conn=null;
	List<Slider> sliderList=null;
	List<FreePost> freeList=null;
	List<Parcel> parcelList=null;
	List<SharingPost> sharingList=null;
	IndexDao id=null;
	
	public List<Slider> indexSliderService()
	{
		id=new IndexDao();
		conn=getConnection();
		sliderList=id.indexSliderDao(conn);
		close(conn);
		return sliderList;
	}
	
	public List<FreePost> indexFreePostService()
	{
		id=new IndexDao();
		conn=getConnection();
		freeList=id.indexFreePostDao(conn);
		close(conn);
		return freeList;		
	}
	
	public List<Parcel> indexParcelPostService()
	{
		id=new IndexDao();
		conn=getConnection();
		parcelList=id.indexParcelPostDao(conn);
		close(conn);
		return parcelList;
	}
	
	public List<SharingPost> indexShaingPostService()
	{
		id=new IndexDao();
		conn=getConnection();
		sharingList=id.indexSharingPostDao(conn);
		close(conn);
		return sharingList;
	}
}
