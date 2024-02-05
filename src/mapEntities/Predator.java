package mapEntities;

import enums.ObstacleType;

public class Predator extends Stone {
	
	public Predator(String icon, ObstacleType type, int[] pos1) {
		super(icon, type, pos1);
	}
		
	public Predator(String icon, ObstacleType type, int[] pos1, int[] pos2) {
		super(icon, type, pos1);
		position.add(new Position(pos2));
	}
	
	/* O m�todo a seguir checa se as posi��es do predador s�o adjacentes
	 * entre si.
	 */
	
	@Override
	public boolean validateObstaclePosition() {
		if(position.size() == 1)
			return true;
		else
			return position.get(0).checkAdjacency(position.get(1));
	}
	
}
