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
	
	/* Na movimentação do sapo do tipo Tomato, primeiro é gerado um número aleatório
	 * de acordo com a direção escolhida pelo jogador e então o sapo se move de uma posição por vez
	 * até atingir o número de movimentos gerado. Além disso, a cada movimento é validada a nova posição do sapo.
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
