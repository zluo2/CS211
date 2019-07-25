
public class Lymphocyte extends WhiteBloodCell{
	
	public Lymphocyte (String nucleusType, Entity[]targets){
		super(nucleusType, targets);
		super.setDNA("lymphocyte");
	}
	
	@Override
	public void absorb(Entity in){
		in.kill();
	    release();
	}
	
	@Override
	public void release(){
		int chance = (int) (Math.random()*100);
		if (chance < 80)
			this.kill();
	}
	

}
