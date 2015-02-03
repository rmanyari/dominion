package ca.dominion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable{
	
	private ArrayList<Player> players;
	private int turn = 0;
	
	public Game(List<Player> players){
		this.players.addAll(players);
		for (Player player : players) {
			addObserver(player);
		}
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
