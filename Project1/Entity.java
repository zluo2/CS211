
public abstract class Entity {
     
	private String DNA; //String DNA
	private Strength strength; //Strength strength
	protected static Entity[] bloodstream; //Entity[] bloodstream
	
	public Entity (Strength strength){
		this.strength = strength;
	}

	public String getDNA() {
		return DNA;
	}

	public void setDNA(String dna) {
		this.DNA = dna;
	}

	public Strength getStrength() {
		return strength;
	}

	public void setStrength(Strength strength) {
		this.strength = strength;
	}
	
	public void kill(){
		this.strength = Strength.DEAD;
	}
	
	public abstract void touchNeighbor(Entity neighbor);
	
	public static void setEntity(int size){
		bloodstream = new Entity[size];
	}
}
