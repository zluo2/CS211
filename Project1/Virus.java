
public class Virus extends Entity{
	
	public Virus(Strength strength){
		super(strength);
		this.setDNA("virus");
	}

	@Override
	public void touchNeighbor(Entity neighbor){
		if (neighbor!=null&&this.getStrength()!=Strength.DEAD){
			if (neighbor.getDNA().equals("neutrophil"))
				neighbor.setDNA("virus");
		}
	}
}
