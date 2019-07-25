
public abstract class WhiteBloodCell extends Entity{

	private String nucleusType; //String nucleusType
	private Entity[] targets; // Entity[] targets
	
    public abstract void absorb(Entity in);
    
	public abstract void release();
	
	public WhiteBloodCell(String nucleusType, Entity[] targets){
		
		super(Strength.HIGH);
		this.setDNA("whiteBloodCell");
		this.nucleusType = nucleusType;
		this.targets = targets;

	}
	
	@Override
	public void touchNeighbor(Entity neighbor){
		if (neighbor != null &&this.getStrength()!=Strength.DEAD&&neighbor.getStrength()!=Strength.DEAD){
			for (int i=0; i<targets.length; i++){
				if (targets[i].getDNA().equals(neighbor.getDNA())){
					int battle = 1 + (int)(Math.random() * ((10 - 1) + 1));
					if ((battle > 5&&this.getStrength()==Strength.MEDIUM)||(battle>1&&this.getStrength()==Strength.HIGH))
							absorb(neighbor);
				}
			}
			
		}
	}
	
	 public void increaseStrength(){
         switch (getStrength()){
             case LOW:
                 setStrength(Strength.MEDIUM);
                 break;
             case MEDIUM:
                 setStrength(Strength.HIGH);
                 break;
         }
     }        
	 
	 public void reduceStrength(){
         switch (getStrength()){
             case LOW:
                 setStrength(Strength.DEAD);
                 break;
             case MEDIUM:
                 setStrength(Strength.LOW);
                 break;
             case HIGH:
                 setStrength(Strength.MEDIUM);
                 break;
         }
     }        
	
	
}
