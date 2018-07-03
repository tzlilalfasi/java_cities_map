public class City {

	private String name;
	private int centerX;
	private int centerY;
	
	public City(String name, int x, int y) {
		this.name=name;
		centerX=x;
		centerY=y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
	public boolean equals(Object other) { //checks if two cities have the same name
		String otherName=((City)other).getName();
		if(otherName.equals(this.name))
			return true;
		return false;
	}
	
}
