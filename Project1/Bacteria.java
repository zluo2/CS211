
public class Bacteria extends Entity{

	public Bacteria (Strength strength){
		super(strength);
        this.setDNA("bacteria");
	}

	@Override
	public void touchNeighbor(Entity neighbor){
		if (neighbor!=null&&this.getStrength()!=Strength.DEAD){
			for (int i=0;i<Bacteria.bloodstream.length;i++)
				if (bloodstream[i] == null){
					bloodstream[i] = new Bacteria(Strength.HIGH);
					break;
			}
		}
	}

}
