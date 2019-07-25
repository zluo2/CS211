import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Simulator {

	public static void main(String[] args){
		healthyModel();
		sickModel();
	}
	
	public static void healthyModel(){
		System.out.println("Modeling a healthy patient******************************************");
		
		Entity.setEntity(20000);
		
		//adding mostly white blood cells, relatively few pathogens
		Entity[] targets1 = {new Virus(Strength.HIGH)};
		for (int i = 0; i < 5000; i++){				
			Entity.bloodstream[i] = new Lymphocyte("small",targets1);			
		}
		Entity[] targets2 = {new Bacteria(Strength.HIGH)};		
		for (int i = 5001; i < 6000; i++){				
			Entity.bloodstream[i] = new Neutrophil("medium",targets2,true);				
		}		
		
		Entity.bloodstream[6000] = new Cytokine();
		Entity.bloodstream[6001] = new Cytokine();	
		Entity.bloodstream[6002] = new Cytokine();		
		
		for (int i = 6003; i < 10050; i++ ){
			Entity.bloodstream[i] =  new Bacteria(Strength.HIGH);
		}
		
		for (int i = 0; i < 100; i ++)
			simulate(i);		
	}
	
	public static void sickModel(){
		System.out.println("Modeling a sick patient******************************************");
		
		//adding mostly pathogens
		Entity.setEntity(40000);
		Entity[] targets1 = {new Bacteria(Strength.HIGH)};
		for (int i = 0; i < 50; i++){				
			Entity.bloodstream[i] = new Lymphocyte("small",targets1);			
		}
		Entity[] targets2 = {new Virus(Strength.HIGH)};		
		for (int i = 51; i < 100; i++){				
			Entity.bloodstream[i] = new Neutrophil("medium",targets2,true);				
		}		
		
		Entity.bloodstream[100] = new Cytokine();
		Entity.bloodstream[101] = new Cytokine();	
		Entity.bloodstream[102] = new Cytokine();		
		
		Entity[] targets3 = {new Bacteria(Strength.HIGH)};
		for (int i = 103; i < 200; i++){
			Entity.bloodstream[i++] = new Macrophage("medium",targets3);
			Entity.bloodstream[i] =  new Neutrophil("large",targets3,false);
		}
		
		for (int i = 200; i < 10050; i++ ){
			Entity.bloodstream[i] =  new Virus(Strength.HIGH);
		}
		
		for (int i = 0; i < 1000; i ++)
			simulate(i);		
	}	
	
	//the simulation is achieved by each element touching its right neighbor
	public static void simulate(int run){
		shuffle();
		for (int i = 0; i < 20000 - 1; i++)
			if (Entity.bloodstream[i] != null)
				Entity.bloodstream[i].touchNeighbor(Entity.bloodstream[i+1]);
		printStats(run);
	}
	
	public static void printStats(int run){
		int liveWhiteBlood = 0;
		int livePathogen = 0;
		int deadWhiteBlood = 0;
		int deadPathogen = 0;		
		
		for (int i = 0; i < 20000; i++){
			if (Entity.bloodstream[i] != null){
				Strength strength = Entity.bloodstream[i].getStrength();
				if (strength != Strength.DEAD){
					if (Entity.bloodstream[i] instanceof WhiteBloodCell)
						liveWhiteBlood++;
					else if (Entity.bloodstream[i] instanceof Bacteria || Entity.bloodstream[i] instanceof Virus){
						livePathogen++;
					}
				}else{
					if (Entity.bloodstream[i] instanceof Neutrophil)
						deadWhiteBlood++;
					else if (Entity.bloodstream[i] instanceof Bacteria || Entity.bloodstream[i] instanceof Virus)
						deadPathogen++;				
				}
			}
		}

		double percentWhite = (liveWhiteBlood * 1.0) / (liveWhiteBlood + deadWhiteBlood) * 100;
		double percentPathogen = (livePathogen * 1.0) / (livePathogen + deadPathogen) * 100;
	
		System.out.println("Percent live white blood cells: " + percentWhite + "%");
		System.out.println("Percent live pathogens: " + percentPathogen + "%");	
		if (percentPathogen > 20)
			System.out.println("This patient is sick.");
		if (percentPathogen < 0.05)
			System.out.println("This patient is fully healed!");		
		if (percentWhite > percentPathogen)
			System.out.println("This patient has lots of healthy white blood cells.");	
		if (liveWhiteBlood == 0)
			System.out.println("This patient has lost all their defenses.");				
	}
	
	//this method will stir the blood to generate different neighbors 
	public static void shuffle(){
		List<Integer> solution = new ArrayList<Integer>();
		for (int i = 0; i < 20000; i++)
		    solution.add(i);
		Collections.shuffle(solution);
		
		Entity[] copy = new Entity[20000];
		for (int i = 0; i < 20000; i++){
			int index = solution.get(i);
			copy[index] = Entity.bloodstream[i];
		}
		
		for (int i = 0; i < 20000; i++){
			Entity.bloodstream[i] = copy[i];
		}
	}
}
