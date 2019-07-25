
public class Cytokine extends Entity{
	
	public Cytokine(){
		super(Strength.DEAD);
		setDNA("cytokine");
	}
	
	@Override	
	public void touchNeighbor(Entity neighbor){
		if (neighbor != null && neighbor.getDNA().equals("neutrophil"))
			kill();
	}

}