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
	
	/* Na movimentação do sapo do tipo venenoso, primeiro é escolhida uma direção aleatória pelo
	 * próprio sapo e então é gerado um número aleatório entre 1 e 4, o qual irá determinar a quantidade
	 * de movimentos que o sapo realizará. O sapo, então, movimenta-se de 1 posição por vez, sempre
	 * validando a nova posição.
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
