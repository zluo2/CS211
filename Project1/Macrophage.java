
public class Macrophage extends WhiteBloodCell{
	
	private int count; // int count
	
	public Macrophage(String nucleusType, Entity[] targets){
		super(nucleusType, targets);
		this.setDNA("macrophage");
	}

	@Override
	public void absorb(Entity in){
		in.kill();
		count++;	
		release();
	}
	
	@Override
	public void release(){
		if (count>100)
			this.kill();
	}
	
	@Override
	public void touchNeighbor(Entity neighbor){
		super.touchNeighbor(neighbor);
		if (neighbor!=null&&this.getStrength()!=Strength.DEAD){
		  if (neighbor.getDNA().equals("neutrophil"))
			if (neighbor.getStrength()==Strength.LOW)
				this.absorb(neighbor);
		}
	}
}
