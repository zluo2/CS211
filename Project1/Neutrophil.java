
public class Neutrophil extends WhiteBloodCell{
	
	private boolean sniffCytokines; // boolean sniffCytokines
	
	public Neutrophil(String nucleusType, Entity[] targets, boolean sniffCytokines){
		
		super(nucleusType, targets);
		this.sniffCytokines = sniffCytokines;
		super.setDNA("neutrophil");
	}
	
	@Override
	public void absorb (Entity in){
		in.kill();
		this.reduceStrength();
		this.release();
	}
	
	@Override
	public void release(){
		
		
		int length = 0;
		for (int i=0; i<bloodstream.length; i++)
			if (Neutrophil.bloodstream[i]==null){
				length=i;
				break;
			}
				
		if (super.getDNA().equals("virus")){
			for (int i=length; i<length+3; i++)
				Neutrophil.bloodstream[i] = new Virus(Strength.HIGH);
		}
		else {
			for (int i=length; i<length+3; i++)
				Neutrophil.bloodstream[i] = new Cytokine();
		}
	}
	
    @Override
	public void touchNeighbor(Entity neighbor){
		super.touchNeighbor(neighbor);
		if (neighbor != null&&super.getStrength()!=Strength.DEAD){
			if (neighbor.getDNA().equals("cytokine"))
				this.increaseStrength();
				
	    }
	}

	

}
