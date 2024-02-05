package mapEntities;

import enums.Direction;
import enums.FrogType;

public abstract class Frog {
	
	private String name;
	private String icon;
	private FrogType type;
	private Position previousPosition;
	private Position currentPosition;
	private Double satisfactionPoints;
	
	public Frog(String name, String icon, int[] pos, FrogType type) {
		this.name = name;
		this.icon = icon;
		this.type = type;
		previousPosition = new Position(pos);
		currentPosition = new Position(pos);
		satisfactionPoints = 0.0;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	public FrogType getType() {
		return type;
	}
	
	public Position getPreviousPosition() {
		return previousPosition;
	}

	protected void setPreviousPosition(Position newPosition) {
		previousPosition.setX(newPosition.getX());
		previousPosition.setY(newPosition.getY());
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	protected void setCurrentPosition(Position newPosition) {
		currentPosition.setX(newPosition.getX());
		currentPosition.setY(newPosition.getY());
	}

	public Double getSatisfactionPoints() {
		return satisfactionPoints;
	}
	
	protected void increaseSatisfactionPoints(double amount) {
		satisfactionPoints += amount;
	}
	
	/* Os métodos a seguir são utilizados na movimentação das classes de sapo no mapa. Para ir para cima,
	 * por exemplo, a coordenada x da posição atual do sapo na matriz que representa o mapa
	 * deve ser subtraída de 1.
	 */
	
	protected void goUp() {
		currentPosition.setX(currentPosition.getX()-1);
	}
	
	protected void goDown() {
		currentPosition.setX(currentPosition.getX()+1);
	}
	
	protected void goRight() {
		currentPosition.setY(currentPosition.getY()+1);
	}
	
	protected void goLeft() {
		currentPosition.setY(currentPosition.getY()-1);
	}

	public abstract boolean move(Direction direction, Map map);
	
	public abstract void eat(Food food);

}