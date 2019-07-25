
public class Highway extends Tile{

	private int carDensity; //carDensity
	
	public Highway(int carDensity){
		super();
		this.carDensity = carDensity;
	}
	
	@Override
	public Force strengthen(Force force){
		double temp = force.getLoad();
		temp+= carDensity * this.CARS_WASTE;
		force.setLoad(temp);
		return force;
	}
	
	public boolean canPropagate(){
		return true;
	}
}
