package mapEntities;

import enums.Foods;

public class Food {
	
	private String name;
	private Position position;
	private Foods type;
	private Integer satisfactionPoints;
	
	public Food(String name, int[] pos, Foods type) {
		this.name = name;
		position = new Position(pos);
		this.type = type;
		if(type == Foods.FIREFLY)
			satisfactionPoints = 15;
		else
			satisfactionPoints = 20;
	}
	
	public String getName() {
		return name;
	}
	
	public Position getRewardPosition() {
		return position;
	}
	
	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = position.getX();
		pos[1] = position.getY();
		return pos;
	}
	
	public Foods getType() {
		return type;
	}
	
	public Integer getRewardSatisfactionPoints() {
		return satisfactionPoints;
	}

}
