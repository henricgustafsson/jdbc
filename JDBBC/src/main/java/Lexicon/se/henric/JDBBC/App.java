package Lexicon.se.henric.JDBBC;

import JDBC.cityDaoJDBC;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      cityDaoJDBC CDJ = new cityDaoJDBC();
      
      CDJ.findAll();
    }
}
