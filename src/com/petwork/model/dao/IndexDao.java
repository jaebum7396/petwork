package com.petwork.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.FreePost;
import com.petwork.model.vo.Parcel;
import com.petwork.model.vo.SharingPost;
import com.petwork.model.vo.Slider;

public class IndexDao 
{		
	Properties prop=new Properties();
	List<Slider> sliderList=null;
	List<FreePost> freeList=null;
	List<Parcel> parcelList=null;
	List<SharingPost> sharingList=null;
	ResultSet rs=null;
	PreparedStatement pstmt=null;
	
	public IndexDao()
	{
		String fileName=IndexDao.class.getResource("./memberquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public List<Slider> indexSliderDao(Connection conn)
	{
		sliderList=new ArrayList();
		
		String sql=prop.getProperty("sliderObjGet");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int i=0;
			
			while(rs.next())
			{
				Slider sliderObj=new Slider();
				
				sliderObj.setFindPostNo(rs.getString("find_post_no"));
				sliderObj.setFindPostTitle(rs.getString("find_post_title"));
				sliderObj.setFindPostContent(rs.getString("find_post_content"));
				sliderObj.setFindPostMissingAddress(rs.getString("find_post_missing_address"));
				sliderObj.setFindPostMissingDate(rs.getDate("find_post_missing_date"));
				sliderObj.setFindPostReward(rs.getString("find_post_reward").charAt(0));
				sliderObj.setFindPostSum(rs.getString("find_post_sum"));
				sliderObj.setFindPostImageAddress(rs.getString("find_post_img_address"));
				sliderObj.setFindPostDate(rs.getDate("find_post_date"));
				sliderObj.setFindPostYn(rs.getString("find_post_yn").charAt(0));				
								
				sliderList.add(sliderObj);		
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		
		return sliderList;
	}
	
	public List<FreePost> indexFreePostDao(Connection conn)
	{
		freeList=new ArrayList();
		
		String sql=prop.getProperty("freeContentObjGet");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int i=0;
			
			while(rs.next())
			{
				FreePost freePostObj=new FreePost();
				freePostObj.setFreePostTitle(rs.getString("free_post_title"));
				freePostObj.setFreePostNo(rs.getInt("free_post_no"));
				freePostObj.setFreePostDate(rs.getDate("free_post_date"));
				freePostObj.setFreePostWriter(rs.getString("free_post_writer"));
				
				freeList.add(freePostObj);				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		
		return freeList;
	}
	
	public List<Parcel> indexParcelPostDao(Connection conn)
	{
		parcelList=new ArrayList();
		
		String sql=prop.getProperty("parcelContentObjGet");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int i=0;
			
			while(rs.next())
			{
				Parcel parcelPostObj=new Parcel();
				parcelPostObj.setTitle(rs.getString("parcel_post_title"));
				parcelPostObj.setPostNo(rs.getInt("parcel_post_no"));
				parcelPostObj.setPostDate(rs.getDate("parcel_post_date"));
				parcelPostObj.setId(rs.getString("member_id"));
				
				parcelList.add(parcelPostObj);				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		
		return parcelList;
	}
	
	public List<SharingPost> indexSharingPostDao(Connection conn)
	{
		sharingList=new ArrayList();
		
		String sql=prop.getProperty("shareContentObjGet");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int i=0;
			
			while(rs.next())
			{
				SharingPost sharePostObj=new SharingPost();
				sharePostObj.setSharingPostTitle(rs.getString("sharing_post_title"));
				sharePostObj.setSharingPostDate(rs.getDate("sharing_post_date"));
				sharePostObj.setSharingPostWriter(rs.getString("sharing_post_writer"));
				sharePostObj.setSharingPostNo(rs.getInt("sharing_post_no"));
				sharingList.add(sharePostObj);				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		
		return sharingList;
	}
}
