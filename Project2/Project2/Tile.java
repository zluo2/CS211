
public abstract class Tile implements Modifiable{

	private int row;//row
	private int col;//column 
	private String measurement;//measurement
	int PEOPLE_WASTE = 2;//People_waste
	int CARS_WASTE = 5;//cars_waste
	
	public abstract boolean canPropagate();

	public Tile (){
		row = 0;
		col = 0;
		measurement = null;
		
	}
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return col;
	}

	public void setColumn(int col) {
		this.col = col;
	}

	public String getMeasurement() {
		if (measurement == null)
			this.setMeasurement(0);
		return measurement;
	}

	public void setMeasurement(double d) {
		this.measurement = Integer.toString((int)Math.round(d));
	}
	
	@Override
	public String toString(){
		return "row: "+Integer.toString(row)+" col: "+Integer.toString(col);
	} 
	
	public Force weaken(Force force){
		
		Force newOne = force.clone();
		newOne.decay();
		return newOne;
	}
}
