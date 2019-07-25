
public class Nature extends Tile{

	private int treeDensity;//treeDensity
	
	public Nature(int treeDensity){
		super();
		this.treeDensity = treeDensity;
	}
	
	public Force strengthen(Force force){
		
		return force;
	}
	
	@Override
	public Force weaken(Force force){
		
		Force newOne = force.clone();
		double temp = newOne.getLoad();
		
		newOne.setLoad(temp *(100-this.treeDensity)*0.01);
		return newOne;
	}
	
	public boolean canPropagate(){
		
		if (treeDensity <= 50)
			return true;
		else return false;
	}
}
