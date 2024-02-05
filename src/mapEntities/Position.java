package mapEntities;

public class Position {
	
	private Integer x;
	private Integer y;
	
	public Position(int[] position) {
		x = position[0];
		y = position[1];
	}
	
	public Integer getX() {
		return x;
	}
	
	public Integer getY() {
		return y;
	}
	
	protected void setX(int x) {
		this.x = x;
	}
	
	protected void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Position) {
			Position otherPosition = (Position) o;
			return (x == otherPosition.x && y == otherPosition.y);
		}
		else
			return false;
	}
	
	public boolean checkAdjacency(Position p) {
		if(p.x == x + 1 || p.x == x - 1)
			return true;
		else if(p.y == y + 1 || p.y == y - 1)
			return true;
		else
			return false;
	}
	
	public boolean checkManhattanDistance(Position p) {
		return (Math.abs(x - p.x) + Math.abs(y - p.y)) <= 2;
	}

}