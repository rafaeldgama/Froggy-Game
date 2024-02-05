package entities;

import java.util.Random;

import enums.Direction;
import enums.FrogType;
import mapEntities.Food;
import mapEntities.Frog;
import mapEntities.Map;

public class PoisonousFrog extends Frog {
	
	public PoisonousFrog (String name, String icon, int[] pos, FrogType type){
		super(name, icon, pos, type);
	}
	
	/* Na movimenta��o do sapo do tipo venenoso, primeiro � escolhida uma dire��o aleat�ria pelo
	 * pr�prio sapo e ent�o � gerado um n�mero aleat�rio entre 1 e 4, o qual ir� determinar a quantidade
	 * de movimentos que o sapo realizar�. O sapo, ent�o, movimenta-se de 1 posi��o por vez, sempre
	 * validando a nova posi��o.
	 */
	
	@Override
	public boolean move(Direction direction, Map map) {
		Random rand = new Random();
		int rnd = rand.nextInt(4) + 1;
		direction = direction.getDirection(rnd);
		rnd = rand.nextInt(4) + 1;
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
		increaseSatisfactionPoints(reward.getRewardSatisfactionPoints()*1.2);
	}

}
