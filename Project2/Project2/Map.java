
public class Map {

	private Tile[][] map;//map
	private int freeRow;//freeRow
	private int freeColumn;//freeColum
	
	private int Row;
	private int Column;
	
	public Map(int width, int height){
		map = new Tile[height][width];
		
		this.Row = height;
		this.Column = width;
		
		this.freeRow = 0;
		this.freeColumn = 0;
	}
	
	public boolean addTile(Tile tile){
		
		boolean judge = false;
		
		if (freeRow!=Row){
			judge = true;
			map[freeRow][freeColumn]=tile;
			tile.setRow(freeRow);
			tile.setColumn(freeColumn);
			
			freeColumn++;
			if (freeColumn==Column){
				freeRow++;
				freeColumn = 0;
			}
		}
		
		return judge;
	}
	
	public Tile getTile (int row, int col){
		return map[row][col];
	}
	
	public Tile[] getNeighbors(Tile tile, Direction direction){
		Tile [] neighbors = new Tile [0];
		Tile neighbor1 = null;
		Tile neighbor2 = null;
		Tile neighbor3 = null;
		
		if (direction == Direction.NORTH)
			if (tile.getRow()!=0){ 
				if (tile.getColumn()-1>=0&&tile.getColumn()+1<Column){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()-1];
					neighbor2 = map[tile.getRow()-1][tile.getColumn()];
					neighbor3 = map[tile.getRow()-1][tile.getColumn()+1];
					neighbors = new Tile[3];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
					neighbors[2] = neighbor3;
				}
				if (tile.getColumn()-1>=0&&tile.getColumn()+1>=Column){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()-1];
					neighbor2 = map[tile.getRow()-1][tile.getColumn()];	
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getColumn()-1<0&&tile.getColumn()+1<Column){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()];
					neighbor2 = map[tile.getRow()-1][tile.getColumn()+1];	
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getColumn()-1<0&&tile.getColumn()+1>=Column){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()];	
					neighbors = new Tile[1];
					neighbors[0] = neighbor1;
				}
			}
		
					
		if (direction == Direction.SOUTH)
			if (tile.getRow()+1!=Row){
				if (tile.getColumn()-1>=0&&tile.getColumn()+1<Column){
					neighbor1 = map[tile.getRow()+1][tile.getColumn()-1];
					neighbor2 = map[tile.getRow()+1][tile.getColumn()];
					neighbor3 = map[tile.getRow()+1][tile.getColumn()+1];		
					neighbors = new Tile[3];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
					neighbors[2] = neighbor3;
				}
				if (tile.getColumn()-1>=0&&tile.getColumn()+1>=Column){
					neighbor1 = map[tile.getRow()+1][tile.getColumn()-1];
					neighbor2 = map[tile.getRow()+1][tile.getColumn()];	
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getColumn()-1<0&&tile.getColumn()+1<Column){
					neighbor1 = map[tile.getRow()+1][tile.getColumn()];
					neighbor2 = map[tile.getRow()+1][tile.getColumn()+1];
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getColumn()-1<0&&tile.getColumn()+1>=Column){
					neighbor1 = map[tile.getRow()+1][tile.getColumn()];	
					neighbors = new Tile[1];
					neighbors[0] = neighbor1;
				}
			}
		
		if (direction == Direction.WEST)
			if (tile.getColumn()!=0){
				if (tile.getRow()-1>=0&&tile.getRow()+1<Row){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()-1];
					neighbor2 = map[tile.getRow()][tile.getColumn()-1];
					neighbor3 = map[tile.getRow()+1][tile.getColumn()-1];	
					neighbors = new Tile[3];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
					neighbors[2] = neighbor3;
				}
				if (tile.getRow()-1>=0&&tile.getRow()+1>=Row){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()-1];
					neighbor2 = map[tile.getRow()][tile.getColumn()-1];
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getRow()-1<0&&tile.getRow()+1<Row){
					neighbor1 = map[tile.getRow()][tile.getColumn()-1];
					neighbor2 = map[tile.getRow()+1][tile.getColumn()-11];
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getRow()-1<0&&tile.getRow()+1>=Row){
					neighbor1 = map[tile.getRow()][tile.getColumn()-1];
					neighbors = new Tile[1];
					neighbors[0] = neighbor1;
				}
		}
		
		if (direction == Direction.EAST)
			if (tile.getColumn()+1!=Column){
				if (tile.getRow()-1>=0&&tile.getRow()+1<Row){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()+1];
					neighbor2 = map[tile.getRow()][tile.getColumn()+1];
					neighbor3 = map[tile.getRow()+1][tile.getColumn()+1];
					neighbors = new Tile[3];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
					neighbors[2] = neighbor3;
				}
				if (tile.getRow()-1>=0&&tile.getRow()+1>=Row){
					neighbor1 = map[tile.getRow()-1][tile.getColumn()+1];
					neighbor2 = map[tile.getRow()][tile.getColumn()+1];
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getRow()-1<0&&tile.getRow()+1<Row){
					neighbor1 = map[tile.getRow()][tile.getColumn()+1];
					neighbor2 = map[tile.getRow()+1][tile.getColumn()+1];
					neighbors = new Tile[2];
					neighbors[0] = neighbor1;
					neighbors[1] = neighbor2;
				}
				if (tile.getRow()-1<0&&tile.getRow()+1>=Row){
					neighbor1 = map[tile.getRow()][tile.getColumn()+1];	
					neighbors = new Tile[1];
					neighbors[0] = neighbor1;
				}
		}
		
		return neighbors;
	}

	public void propagate(Force force, int row, int column, Direction direction){
		
		Tile tile = getTile(row, column);

		Force weaken = tile.weaken(force);
		Force strengthen = tile.strengthen(force);
		tile.setMeasurement((strengthen.getLoad() + weaken.getLoad()) / 2);
		Tile[] neighbors = getNeighbors(tile, direction);
		force.setLoad((strengthen.getLoad() + weaken.getLoad()) / 2);

		// recursion below

		for (int i = 0; i < neighbors.length; i++)
			propagate(force, neighbors[i].getRow(), neighbors[i].getColumn(), direction);

	}
	
	@Override
	public String toString(){
		String allString = null;
		for(int i=0; i<Row; i++){
			for(int j=0; j<Column; j++){
				String string = map[i][j].getClass().getName();
					allString += "class "+string+" "+map[i][j].getMeasurement()+" ";
			}
			allString +="\n";
		}
		allString +="\n";
		String string = allString.substring(4, allString.length()-1);
		return string;
	}
	
	
}
