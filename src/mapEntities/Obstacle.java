package mapEntities;

import java.util.ArrayList;
import java.util.List;
import enums.ObstacleType;

public abstract class Obstacle {
	
	private String icon;
	private ObstacleType type;
	protected List<Position> position = new ArrayList<Position>();
	
	public Obstacle(String s, ObstacleType t) {
		icon = s;
		type = t;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public ObstacleType getType() {
		return type;
	}
	
	public abstract boolean validateObstaclePosition();

}
