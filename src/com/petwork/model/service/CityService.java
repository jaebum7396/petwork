package com.petwork.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;
import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.CityDao;
import com.petwork.model.vo.City;
import com.petwork.model.vo.District;

public class CityService {

	public List<City> selectCityList() {
		Connection conn = getConnection();
		List<City> list = new CityDao().selectCityList(conn);
		close(conn);
		return list;
	}

	public List<District> selectDistrict(String cityCode) {
		Connection conn = getConnection();
		List<District> list = new CityDao().selectDistrict(conn, cityCode);
		close(conn);
		return list;
	}

}
