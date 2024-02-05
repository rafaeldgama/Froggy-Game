package enums;

public enum Direction {
	
	UP(1),
	DOWN(2),
	LEFT(3),
	RIGHT(4);
	
	private final int value;
	private Direction(int value) {
		this.value = value;
	}
	
	public Direction getDirection(int value) {
		if (value == 1)
			return Direction.UP;
		else if(value == 2)
			return Direction.DOWN;
		else if(value == 3)
			return Direction.LEFT;
		else
			return Direction.RIGHT;
	}

}
