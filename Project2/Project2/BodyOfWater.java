
public class BodyOfWater extends Tile{
	
	@Override
	public Force strengthen(Force f){
		return f;
	}
	
	@Override
	public boolean canPropagate(){
		return true;
	}

}
