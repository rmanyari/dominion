package ca.dominion;

import java.util.ArrayList;

import ca.dominion.model.*;
import ca.dominion.model.impl.*;
import ca.dominion.model.impl.cards.*;

public class Application {

	public static void main(String[] args) {
		
		System.out.println("test");
		
		
		ArrayList<Card> actionCards = new ArrayList<Card>();
		actionCards.add(new Village(CardName.VILLAGE));
		actionCards.add(new Smithy(CardName.SMITHY));	
		 
		GameDeck gameDeck = new GameDeck(actionCards);
	 
		Player player1 = new Player(gameDeck);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		
		Game game = new Game(gameDeck, players);
		
		game.playTurn(Stage.ACTION);
		
		
	}

}
