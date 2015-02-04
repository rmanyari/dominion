package ca.dominion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ca.dominion.model.impl.*;

public class Game extends Observable{
	
	private ArrayList<Player> players;
	private int turn = 0;
	private GameDeck deck;
	
	public Game(GameDeck deck, List<Player> players){
		this.players.addAll(players);
		for (Player player : players) {
			addObserver(player);
		}
		this.deck = deck;
	}
		
	public void playTurn(Stage stage){
		setChanged();
		String message = stage.name() + players.get(players.size() % turn).getId();
		if(stage == Stage.BUY){
			turn++;
		}
		notifyObservers(message);
	}
	
}
