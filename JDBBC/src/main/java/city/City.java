package city;

public class City {

	
	//int id, string name, string countrycode, string district, int population
	
	private static int COUNTER=0;
	private int id;
	private String name;
	private String countryCode;
	private String district;
	private int population;
	
	public City(int id,String name, String countrycode, String district, int population) {
		
		
		this.name = name;
		this.countryCode = countrycode;
		this.district = district;
		this.population = population;
	}

	public City(String name, String countrycode, String district, int population) {
		this(COUNTER++,name,countrycode,district,population);
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getId() {
		return id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + population;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population != other.population)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City \nid:" + id + "\nname:" + name + "\ncountryCode=" + countryCode + "\ndistrict=" + district
				+ ", population=" + population + "]";
	}
	
	
}