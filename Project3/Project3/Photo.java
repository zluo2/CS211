
public class Photo {

	private int size;//size
	private String name;//name
	private String location;//location
	
	public Photo(String name, int size, String location){
		this.name = name;
		this.size = size;
		this.location = location;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public boolean equals(Object o){
		
		boolean equal = false;
		
		if (o instanceof Photo){
			if (this.getLocation().equalsIgnoreCase(((Photo)o).getLocation()))
				equal = true;
		}
		
		return equal;
	}
	
	
}
