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
	
	/* Método utilizado apenas na inicialização do mapa */
	
	private void fillMap() {
		for(int i = 0; i < map.length; ++i)
			Arrays.fill(map[i], "--");
	}
	
	/* O método a seguir é responsável por adicionar recompensas no mapa.
	 * Primeiramente é checado se a posição especificada encontra-se livre
	 * e então é adicionada a recompensa.
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
	
	/* O método a seguir é responsável por adicionar os obstáculos no mapa.
	 * Primeiro é checado se o obstáculo a ser adicionado obedece seus próprios
	 * critérios de criação. Logo após, é checado para cada posição do obstáculo
	 * se a posição especificada encontra-se livre no mapa. Por último, o obstáculo
	 * é adicionado e seu respectivo ícone também é adicionado à matriz de Strings
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
	
	/* O método a seguir checa se a posição p informada
	 * corresponde a algum dos obstáculos que estão presentes
	 * na lista do mapa.
	 */
	
	public Obstacle checkPositionForObstacle(Position p) {
		for(Obstacle obstacle : obstacles)
			for(Position pos : obstacle.position)
				if(p.equals(pos))
					return obstacle;
		return null;
	}
	
	/* O método a seguir checa se a posição p informada
	 * corresponde a alguma das recompensas que estão presentes
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
	
	/* O método a seguir é responsável por validar a nova posição do sapo.
	 * Primeiramente é checado se a nova posição está fora dos limites do mapa.
	 * Logo após, checa-se a existência de recompensas ou obstáculos na posição atual do sapo.
	 * Caso existam recompensas, o sapo consome-as. No caso de obstáculos, caso seja um predador
	 * ou uma armadilha o jogo termina. No entanto, se for uma pedra, o sapo volta para a posição
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
