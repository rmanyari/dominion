package ca.dominion;

import java.util.ArrayList;

import ca.dominion.model.*;

public class Application {

	
	public static void main(String[] args) {
		
		System.out.println("test");
		
		Player player1 = new Player();
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		
		Game game = new Game(players);
		game.playTurn(Stage.ACTION);
		
		
	}

}
