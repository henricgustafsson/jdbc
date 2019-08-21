package JDBC;

import java.util.List;

import City.city;

public interface CityDao {

	city findById(int id);
	List<city> findByCode(String code);
	List<city> findByName(String name);
	List<city> findAll();
	city add(city city);
	city update(city city);
	int delete(city city);
}
