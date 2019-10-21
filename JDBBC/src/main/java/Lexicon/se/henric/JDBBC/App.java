package Lexicon.se.henric.JDBBC;

import java.io.ObjectInputStream.GetField;
import java.util.List;
import java.util.Optional;

import JDBC.cityDaoJDBC;
import city.City;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      cityDaoJDBC CDJ = new cityDaoJDBC();
      City city = new City("test", "AFG", "SAD", 1000);
      
      //find all
//      List<City> cityList = CDJ.findAll();
//      printList(cityList);
//      
//######################################################## 
      
      //find by id
      
//      Optional<? extends Object> city = CDJ.findById(1);
//      
//      if (city.isPresent()) {
//    	  System.out.println(city.get().toString());
//      }
//######################################################## 
      
      //find by code
      
//     List<City> cityList= CDJ.findByCode("AFG");
//     printList(cityList);

//########################################################
      
      //find by name
      
//    List<City> cityList= CDJ.findByName("Kabul");
//    printList(cityList);
//
//########################################################
      
      //Create
      
//      Optional<City> savedCity =CDJ.create(city);
//      if(savedCity.isPresent()){
//    	  System.out.println(savedCity.get().toString());
//      }
      
//########################################################
    
      //Update
//      city.setName("testytest");
//      city.setDistrict("KKK");
//      city.setPopulation(100);
//      City updatedCity =CDJ.update(city);
//      System.out.println(updatedCity.toString());
      
//########################################################
    //DELETE
//      int id =CDJ.delete(city);
//      System.out.println(id);
    }
    
    public static void printList(List<City> cityList) {
       cityList.forEach((c)->{
     	  System.out.println(c.toString());
       });
    }
}
