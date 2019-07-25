
public class IndustrialBuilding extends Building{
	
	private double emissions;//emissions
	private int EMISSIONS_CONSTANT = 10;//emission_constant
	
	public IndustrialBuilding (int people, int cars, int height, double emissions){
		super(people, cars, height);
		this.emissions = emissions;
	}
	
	@Override
	public Force strengthen (Force force){
		super.strengthen(force);
		double temp = force.getLoad();
		temp += emissions*EMISSIONS_CONSTANT;
		force.setLoad(temp);
 		return force;
	}
	
	@Override
	public boolean canPropagate(){
		return true;
	}

}
