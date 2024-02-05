package entities;

import java.util.Random;

import enums.Direction;
import enums.FrogType;
import mapEntities.Food;
import mapEntities.Frog;
import mapEntities.Map;

public class TomatoFrog extends Frog {
	
	public TomatoFrog (String name, String icon, int[] pos, FrogType type){
		super(name, icon, pos, type);
	}
	
	/* Na movimenta��o do sapo do tipo Tomato, primeiro � gerado um n�mero aleat�rio
	 * de acordo com a dire��o escolhida pelo jogador e ent�o o sapo se move de uma posi��o por vez
	 * at� atingir o n�mero de movimentos gerado. Al�m disso, a cada movimento � validada a nova posi��o do sapo.
	 */
	
	@Override
	public boolean move(Direction direction, Map map) {
		Random rand = new Random();
		int rnd = 0;
		if(direction == Direction.RIGHT || direction == Direction.LEFT)
			rnd = rand.nextInt(3) + 1;
		else if(direction == Direction.UP)
			rnd = rand.nextInt(2) + 2;
		else {
			int[] values = {1,2,4};
			rnd = values[rand.nextInt(values.length)];
		}
		for(int i = 0; i < rnd; ++i) {
			setPreviousPosition(getCurrentPosition());
			if(direction == Direction.UP)
				super.goUp();
			else if(direction == Direction.DOWN)
				super.goDown();
			else if(direction == Direction.RIGHT)
				super.goRight();
			else
				super.goLeft();
			if(!map.validateNewFrogPosition(this))
				return false;
		}
		return true;
	}
	
	@Override
	public void eat(Food reward) {
		increaseSatisfactionPoints(reward.getRewardSatisfactionPoints()*1.1);
	}

}
