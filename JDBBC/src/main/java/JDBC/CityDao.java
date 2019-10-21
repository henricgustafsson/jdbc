package JDBC;
import java.util.List;
import java.util.Optional;



import city.City;



public interface CityDao {

	Optional<? extends Object> findById(int id);
	List<City> findByCode(String code);
	List<City> findByName(String name);
	List<City> findAll();
	Optional<City> create(City city);
	City update(City city);
	int delete(City city);
}
