package JDBC;

import JDBC.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mysql.jdbc.Statement;

import city.City;


public class cityDaoJDBC implements CityDao {
	

	
	private static String FIND_BY_ID = "SELECT * FROM city WHERE id=?";
	private static String FIND_BY_CODE ="SELECT * FROM city WHERE countrycode=?";
	private static String FIND_BY_NAME ="SELECT * FROM city WHERE name =?";
	private static String FIND_ALL ="SELECT * FROM city";
	private static String CREATE_CITY ="INSERT INTO city (name,countrycode,district,population) VALUES (?,?,?,?)";
	private static String UPDATE_CITY ="UPDATE city SET name=?,countrycode=?,district=?,population=? WHERE id=?";
	private static String DELETE_CITY ="DELETE FROM city WHERE id=?";
	
	
	

	public Optional<? extends Object> findById(int id) {
		City city = null;
		try(Connection conn = Database.getConnection()){
			
			PreparedStatement statement = prepareFindByIdStatement(conn, id,FIND_BY_ID);
			ResultSet rs =statement.executeQuery();
			
			while(rs.next()) {
				city = convertResultSetToCity(rs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return city == null ? Optional.empty() : Optional.of(city);
				
	}

	
	

	private PreparedStatement prepareFindByIdStatement(Connection conn,int id,final String findById) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(findById);
		statement.setInt(1,id);
		return statement;
	}
	
	public List<City> findByCode(String code) {
		
		List<City> cities = new ArrayList<City>();		
		
		try(Connection conn = Database.getConnection()){
			PreparedStatement statement = prepareFindByCodeStatement(conn,code,FIND_BY_CODE);
			ResultSet rs =statement.executeQuery();
			while(rs.next()) {
				cities.add(convertResultSetToCity(rs));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cities;
	}

	private PreparedStatement prepareFindByCodeStatement(Connection conn,String code, final String findByCode) throws SQLException {
		
		PreparedStatement statement = conn.prepareStatement(findByCode);
		statement.setString(1, code);
		
		return 	statement;
	}

	public List<City> findByName(String name) {
		List<City> cities = new ArrayList<City>();
		
		try(Connection conn = Database.getConnection()){
			PreparedStatement statement = prepareFindByNameStatement(conn, name, FIND_BY_NAME);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				cities.add(convertResultSetToCity(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cities;
	}
	
	private PreparedStatement prepareFindByNameStatement(Connection conn,String name, final String findByName) throws SQLException {
		
		PreparedStatement statement = conn.prepareStatement(findByName);
		statement.setString(1, name);
		
		return statement;
	}

	public List<City> findAll() {
		
		List<City> cities = new ArrayList<City>();
		
		try(Connection conn = Database.getConnection()){
			
			PreparedStatement statement = conn.prepareStatement(FIND_ALL);
			ResultSet rs =statement.executeQuery();
			while(rs.next()) {
				cities.add(convertResultSetToCity(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cities;
	}

	public Optional<City> create(City city) {
		if(city.getId() !=0) {
			return Optional.empty();
		}
		
		ResultSet resultSet = null;
		City savedCity = null;
		
		try(Connection conn= Database.getConnection()){
			
			
			
			PreparedStatement statement = conn.prepareStatement(CREATE_CITY,Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, city.getName());
			statement.setString(2, city.getCountryCode());
			statement.setString(3, city.getDistrict());
			statement.setInt(4, city.getPopulation());
			
			statement.executeUpdate();
			
			
			
            resultSet = statement.getGeneratedKeys();
            int cityID = 0;
            while(resultSet.next()){
                cityID = resultSet.getInt(1);
            }

          //(int id,String name, 		String countrycode, String district, int population
           savedCity = new City(
                    cityID,                       //Id taken from getGeneratedKeys()
                    city.getName(),
                    city.getCountryCode(),
                    city.getDistrict(),
                    city.getPopulation()
                  
            );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try{
                if(resultSet != null){
                    resultSet.close();
                }
			}catch (SQLException ex){
                ex.printStackTrace();
            }
		}
		return savedCity==null ? Optional.empty() : Optional.of(savedCity);
	}

	public City update(City city) {
		
		if(city.getId() ==0) {
			return city;
		}
		
		ResultSet keySet = null;
	
		try(Connection conn = Database.getConnection()){
			
			//"UPDATE city SET name=?,countrycode=?,district=?,population=? WHERE id=?";
			PreparedStatement statement = conn.prepareStatement(UPDATE_CITY);
			
		
			statement.setString(1, city.getName());
			statement.setString(2, city.getCountryCode());
			statement.setString(3, city.getDistrict());
			statement.setInt(4, city.getPopulation());
			statement.setInt(5, city.getId());
			
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(keySet != null) {
				try {
					keySet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return city;
	}

	public int delete(City city) {
		
		if(city.getId() !=0) {
			return city.getId();
		}
		
		ResultSet resultSet = null;
		City savedCity = null;
		int cityID = 0;
		try(Connection conn = Database.getConnection()){
			PreparedStatement statement = conn.prepareStatement(DELETE_CITY,Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, city.getId());
			
			statement.executeUpdate();

			resultSet = statement.getGeneratedKeys();
            
            while(resultSet.next()){
                cityID = resultSet.getInt(1);
            }

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return cityID;
		
	}

	
	private City convertResultSetToCity(ResultSet rs) throws SQLException {
		
						//(int id,String name, 		String countrycode, String district, int population
		return new City(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
	}
		
}