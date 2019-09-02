package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import City.City;

public class cityDaoJDBC implements CityDao {
	
	Connection connection;
	PreparedStatement findByID;
	PreparedStatement findByCode;
	PreparedStatement findByName;
	PreparedStatement findAll;
	PreparedStatement createCity;
	PreparedStatement updateCity;
	PreparedStatement deleteCity;
	
	
	public cityDaoJDBC() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost;3306/world","root","root");
			
			
			findByID = connection.prepareStatement("SELECT * FROM city WHERE id=?");
			findByCode =connection.prepareStatement("SELECT * FROM city WHERE countrycode=?");
			findByName =connection.prepareStatement("SELECT * FROM city WHERE name =?");
			findAll =connection.prepareStatement("SELECT * FROM city");
			createCity =connection.prepareStatement("INSERT INTO city (name,countrycode,district,population) VALUES (?,?,?,?)");
			updateCity =connection.prepareStatement("UPDATE city SET name=?,countrycode=?,district=?,population=? WHERE id=?");
			deleteCity =connection.prepareStatement("DELETE FROM city WHERE id=?");
			
		}
		catch(SQLException e) {
			System.out.println("Connection error.");
		}
		
	}

	public City findById(int id) {
		ResultSet rs = null;
		int id = 0;
		String name = null;
		String district = null;
		int population = 0;
		City city =  null;
		
		try {
			findByID.setString(1,id+"%");
			rs= findByID.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				district = rs.getString("name");
				population = rs.getInt("population");

			}
			if(id !=0 || name != null || district != null || population < 0) {
				try {
					new City(id,name,district,population);
					
				} catch (Exception e) {
					System.out.println("Internal error please try again");
				}
			}
		} catch (SQLException e) {
			System.out.println("Internal error. Try again");
		}
		
		if(city !=null) { 
			return city;
		}
		throw new Exception("Internal error. Try again");
	}

	public List<City> findByCode(String code) {
		
		ResultSet rs= null;
		int id= 0;
		String name = null;
		String district = null;
		int population = 0;
		List<City> cities = new ArrayList<City>();
		
		try{
			//TODO: setstring for findByCode
			rs = findByCode.executeQuery();
			while(rs.next()) {
				id= rs.getInt("id");
				name= rs.getString("name");
				district= rs.getString("district");
				population = rs.getInt("population");
				
				if(id !=0 || name !=null ||district != null || population <0) {
					try {
						cities.add(new City(id,name,district,population));
					}catch(Exception e) {
						System.out.println("Internal error please try again");
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Internal error. Try again");
		}
		
		return cities;
		
		
		
	}

	public List<City> findByName(String name) {
		ResultSet rs= null;
		int id= 0;
		String name = null;
		String district = null;
		int population = 0;
		List<City> cities = new ArrayList<City>();
		
		try{
			//TODO: setstring for findByCode
			rs = findByName.executeQuery();
			while(rs.next()) {
				id= rs.getInt("id");
				name= rs.getString("name");
				district= rs.getString("district");
				population = rs.getInt("population");
				
				if(id !=0 || name !=null ||district != null || population <0) {
					try {
						cities.add(new City(id,name,district,population));
					}catch(Exception e) {
						System.out.println("Internal error please try again");
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Internal error. Try again");
		}
		
		return cities;
	}

	public List<City> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public City add(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	public City update(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete(City city) {
		// TODO Auto-generated method stub
		return 0;
	}

}