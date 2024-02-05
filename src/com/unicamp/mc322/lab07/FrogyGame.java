package com.unicamp.mc322.lab07;

import java.util.Scanner;

import entities.*;
import enums.Direction;
import enums.Foods;
import enums.FrogType;
import enums.ObstacleType;
import mapEntities.Food;
import mapEntities.Frog;
import mapEntities.Map;
import mapEntities.Predator;
import mapEntities.Stone;
import mapEntities.Trap;


public class FrogyGame {
	
	public FrogyGame() {
		
	}
	
	public void runGame() {
		
		/* Instanciação do mapa e dos objetos. Aqui também é possível escolher
		 * com qual tipo de rã o jogador quer jogar. Basta instanciar a devida classe
		 * escolhida e então mudar o tipo do enum.
		 */
		
		Map map = new Map(10, 10);
		Stone stone1 = new Stone("<>", ObstacleType.STONE, new int[] {2,7});
		map.addObstacle(stone1);
		Stone stone2 = new Stone("<>", ObstacleType.STONE, new int[] {3,2});
		map.addObstacle(stone2);
		Stone stone3 = new Stone("<>", ObstacleType.STONE, new int[] {7,1});
		map.addObstacle(stone3);
		Stone stone4 = new Stone("<>", ObstacleType.STONE, new int[] {8,4});
		map.addObstacle(stone4);
		Stone stone5 = new Stone("<>", ObstacleType.STONE, new int[] {8,8});
		map.addObstacle(stone5);
		Predator predator1 = new Predator("$$", ObstacleType.PREDATOR, new int[] {4,4}, new int[] {5,5});
		map.addObstacle(predator1);
		Trap trap1 = new Trap("{}", ObstacleType.TRAP, new int[] {0,0}, new int[] {0,1});
		map.addObstacle(trap1);
		Food firefly = new Food("va", new int[] {1,3}, Foods.FIREFLY);
		map.addFood(firefly);
		Food cricket = new Food("gr", new int[] {4,7}, Foods.CRICKET);
		map.addFood(cricket);
		Frog player = new GreenFrog("Jogador 1", "J1", new int[] {8,7}, FrogType.GREEN);
		map.createFrog(player);
		System.out.println();
		System.out.print(map);
		Scanner keyboard = new Scanner(System.in);
		boolean running = true;
		while(running) {
			System.out.print("Enter the command: ");
			String command = keyboard.nextLine();
			if(command.compareTo("quit") == 0)
				running = false;
			else if(command.compareTo("w") == 0)
				running = player.move(Direction.UP, map);
			else if(command.compareTo("a") == 0)
				running = player.move(Direction.LEFT, map);
			else if(command.compareTo("s") == 0)
				running = player.move(Direction.DOWN, map);
			else if(command.compareTo("d") == 0)
				running = player.move(Direction.RIGHT, map);
			else
				System.out.println("Please enter a valid input.");
			System.out.println();
			System.out.print(map);
		}
		System.out.println("Game Over.");
		keyboard.close();
	}

}
