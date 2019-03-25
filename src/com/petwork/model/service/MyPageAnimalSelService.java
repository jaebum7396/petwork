package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.MyPageAnimalSelDao;
import com.petwork.model.vo.Animal;

public class MyPageAnimalSelService 
{
	Connection conn=null;
	MyPageAnimalSelDao mpas=null;
	List<Animal> animalList=null;
	
	
	public List<Animal> myPageAnimalSelService(String inputRace)
	{
		mpas=new MyPageAnimalSelDao();
		conn=getConnection();
		animalList=mpas.myPageAnimalSelDao(inputRace, conn);
		close(conn);
		return animalList;
	}
	
	public String MyPageReturnRaceService(String raceCode, String animalNo)
	{
		mpas=new MyPageAnimalSelDao();
		conn=getConnection();
		String animalName=mpas.myPageReturnRaceDao(raceCode,animalNo, conn);
		close(conn);
		return animalName;
	}
}
