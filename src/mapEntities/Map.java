package mapEntities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import enums.ObstacleType;

public class Map {
	
	private Integer length;
	private Integer height;
	private String[][] map;
	private List<Food> foods = new ArrayList<Food>();
	private List<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	protected Map() {
		
	}
	
	public Map(int l, int h) {
		length = l;
		height = h;
		map = new String[l][h];
		fillMap();
	}
	
	public Integer getLength() {
		return length;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	/* M�todo utilizado apenas na inicializa��o do mapa */
	
	private void fillMap() {
		for(int i = 0; i < map.length; ++i)
			Arrays.fill(map[i], "--");
	}
	
	/* O m�todo a seguir � respons�vel por adicionar recompensas no mapa.
	 * Primeiramente � checado se a posi��o especificada encontra-se livre
	 * e ent�o � adicionada a recompensa.
	 */
	
	public boolean addFood(Food reward) {
		int[] rewardPos = reward.getPosition();
		if(map[rewardPos[0]][rewardPos[1]] == "--") {
			foods.add(reward);
			map[rewardPos[0]][rewardPos[1]] = reward.getName();
			return true;
		}
		else
			return false;
	}
	
	/* O m�todo a seguir � respons�vel por adicionar os obst�culos no mapa.
	 * Primeiro � checado se o obst�culo a ser adicionado obedece seus pr�prios
	 * crit�rios de cria��o. Logo ap�s, � checado para cada posi��o do obst�culo
	 * se a posi��o especificada encontra-se livre no mapa. Por �ltimo, o obst�culo
	 * � adicionado e seu respectivo �cone tamb�m � adicionado � matriz de Strings
	 * que representa o mapa.
	 */
	
	public boolean addObstacle(Obstacle obstacle) {
		if(obstacle.validateObstaclePosition()) {
			boolean positionFree = true;
			for(Position p : obstacle.position) {
				if(map[p.getX()][p.getY()] == "--")
					positionFree = true;
				else
					positionFree = false;
			}
			if(positionFree) {
				obstacles.add(obstacle);
				for(Position p : obstacle.position)
					map[p.getX()][p.getY()] = obstacle.getIcon();
			}
			return positionFree;
		}
		else
			return false;
	}
	
	public boolean createFrog(Frog playerFrog) {
		Position frogpos = playerFrog.getCurrentPosition();
		if(map[frogpos.getX()][frogpos.getY()] == "--") {
			map[frogpos.getX()][frogpos.getY()] = playerFrog.getIcon();
			return true;
		}
		else
			return false;
	}
	
	/* O m�todo a seguir checa se a posi��o p informada
	 * corresponde a algum dos obst�culos que est�o presentes
	 * na lista do mapa.
	 */
	
	public Obstacle checkPositionForObstacle(Position p) {
		for(Obstacle obstacle : obstacles)
			for(Position pos : obstacle.position)
				if(p.equals(pos))
					return obstacle;
		return null;
	}
	
	/* O m�todo a seguir checa se a posi��o p informada
	 * corresponde a alguma das recompensas que est�o presentes
	 * na lista do mapa.
	 */
	
	public Food checkPositionForReward(Position p) {
		for(Food reward : foods)
			if(p.equals(reward.getRewardPosition()))
				return reward;
		return null;
	}
	
	public void updateFrogPosition(Frog player) {
		Position previousPos = player.getPreviousPosition();
		map[previousPos.getX()][previousPos.getY()] = "--";
		if(player.getCurrentPosition().getX() < 0 || player.getCurrentPosition().getX() >= height || player.getCurrentPosition().getY() < 0 || player.getCurrentPosition().getY() >= length)
			return;
		Position currentPos = player.getCurrentPosition();
		map[currentPos.getX()][currentPos.getY()] = player.getIcon();
	}
	
	/* O m�todo a seguir � respons�vel por validar a nova posi��o do sapo.
	 * Primeiramente � checado se a nova posi��o est� fora dos limites do mapa.
	 * Logo ap�s, checa-se a exist�ncia de recompensas ou obst�culos na posi��o atual do sapo.
	 * Caso existam recompensas, o sapo consome-as. No caso de obst�culos, caso seja um predador
	 * ou uma armadilha o jogo termina. No entanto, se for uma pedra, o sapo volta para a posi��o
	 * imediamente anterior.
	 */
	
	public boolean validateNewFrogPosition(Frog player) {
		if(player.getCurrentPosition().getX() < 0 || player.getCurrentPosition().getX() >= height || player.getCurrentPosition().getY() < 0 || player.getCurrentPosition().getY() >= length) {
			updateFrogPosition(player);
			System.out.println("You have gone out of bounds on the map.");
			return false;
		}
		Food reward = checkPositionForReward(player.getCurrentPosition());
		if(reward != null) {
			player.eat(reward);
			updateFrogPosition(player);
			return true;
		}
		Obstacle obstacle = checkPositionForObstacle(player.getCurrentPosition());
		if(obstacle != null) {
			if(obstacle.getType() == ObstacleType.PREDATOR || obstacle.getType() == ObstacleType.TRAP) {
				updateFrogPosition(player);
				return false;
			}
			else
				player.setCurrentPosition(player.getPreviousPosition());
		}
		updateFrogPosition(player);
		return true;
	}
	
	@Override
	public String toString() {
		String textMap = "";
		for(int i = 0; i < map.length; ++i) {
			for(int j = 0; j < map[0].length; ++j)
				textMap = textMap + map[i][j] + " ";
			textMap = textMap + String.format("%n");
		}
		return textMap;
	}

}
