package mapEntities;

import enums.ObstacleType;

public class Stone extends Obstacle {
	
	public Stone(String icon, ObstacleType type, int[] pos) {
		super(icon, type);
		position.add(new Position(pos));
	}
	
	@Override
	public boolean validateObstaclePosition() {
		return true;
	}
	
}