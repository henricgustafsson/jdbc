package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import City.city;

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
			
			//TOOD: prepeare dem statements
			findByID = connection.prepareStatement("SELECT * FROM city WHERE id=?");
			findByCode =connection.prepareStatement("SELECT * FROM city WHERE countrycode=?");
			findByName =connection.prepareStatement("SELECT * FROM city WHERE name =?");
			findAll =connection.prepareStatement("SELECT * FROM city");
			createCity =connection.prepareStatement("");
			updateCity =connection.prepareStatement("");
			deleteCity =connection.prepareStatement("");
			
		}
		catch(SQLException e) {
			System.out.println("Connection error.");
		}
		
	}

	public city findById(int id) {
		ResultSet rs = null;
		int id = 0;
		String name = null;
		String district = null;
		int population = 0;
		city city =  null;
		
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
					new city(id,name,district,population);
					
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

	public List<city> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<city> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<city> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public city add(city city) {
		// TODO Auto-generated method stub
		return null;
	}

	public city update(city city) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete(city city) {
		// TODO Auto-generated method stub
		return 0;
	}

}