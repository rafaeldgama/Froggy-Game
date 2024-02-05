package mapEntities;

import enums.ObstacleType;

public class Trap extends Predator {
	
	public Trap(String icon, ObstacleType type, int[] pos1) {
		super(icon, type, pos1);
	}
	
	public Trap(String icon, ObstacleType type, int[] pos1, int[] pos2) {
		super(icon, type, pos1, pos2);
	}
	
	public Trap(String icon, ObstacleType type, int[] pos1, int[] pos2, int[] pos3) {
		super(icon, type, pos1, pos2);
		position.add(new Position(pos3));
	}
	
	/* O m�todo a seguir checa se as posi��es da armadilha obedecem o crit�rio
	 * de estarem a uma dist�ncia de manhattan de no m�ximo 2 umas das outras.
	 */
	
	@Override
	public boolean validateObstaclePosition() {
		if(position.size() == 1)
			return true;
		else if(position.size() == 2)
			return position.get(0).checkManhattanDistance(position.get(1));
		else {
			boolean manhattanOk = true;
			for(int i = 0; i < position.size(); ++i)
				for(int j = i + 1; j < position.size(); ++j)
					manhattanOk = position.get(i).checkManhattanDistance(position.get(j));
			return manhattanOk;
		}
	}

}
