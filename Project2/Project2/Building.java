
public class Building extends Tile{
	
	private int people;//people
	private int cars;//cars
	private int height;//height
	
	public Building (int people, int cars, int height){
		
		super();
		this.people = people;
		this.cars = cars;
		this.height = height;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getCars() {
		return cars;
	}

	public void setCars(int cars) {
		this.cars = cars;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Force strengthen (Force force){
		
		double temp = force.getLoad();
		temp+= this.cars * this.CARS_WASTE + this.people * this.PEOPLE_WASTE;
		force.setLoad(temp);
		return force;
	} 

	@Override
	public boolean canPropagate(){
		if (0< height && height < 100) return true;
		else return false;
	}
}
