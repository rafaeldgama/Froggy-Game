package entities;

import enums.Direction;
import enums.FrogType;
import mapEntities.Food;
import mapEntities.Frog;
import mapEntities.Map;

public class GreenFrog extends Frog {
	
	public GreenFrog (String name, String icon, int[] pos, FrogType type){
		super(name, icon, pos, type);
	}
	
	@Override
	public boolean move(Direction direction, Map map) {
		setPreviousPosition(getCurrentPosition());
		if(direction == Direction.UP)
			super.goUp();
		else if(direction == Direction.DOWN)
			super.goDown();
		else if(direction == Direction.RIGHT)
			super.goRight();
		else
			super.goLeft();
		return map.validateNewFrogPosition(this);
	}
	
	@Override
	public void eat(Food reward) {
		increaseSatisfactionPoints((double) reward.getRewardSatisfactionPoints());
	}
	
}
