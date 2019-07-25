
public class Force {
	
	private double load;//record how much pollutant exists
	private String name;//name of pollutant
	
	public Force(double load, String name){
		this.load = load;
		this.name = name;
	}

	public double getLoad() {
		return load;
	}

	public void setLoad(double load) {
		this.load = load;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void decay(){
		this.load = load * 0.8;
	}
	
	public Force clone(){
		Force newOne = new Force(this.load, this.name);
		return newOne;
	}
	
	@Override
	public String toString(){
		int temp = (int) Math.round(load);
		return name+" has a load of "+Integer.toString(temp);
	}

}
