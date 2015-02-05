package ca.dominion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ca.dominion.model.impl.*;

public class Game extends Observable{
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private int turn = 1;
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
		String message = stage.name() + "_" + players.get(players.size()-1).getId(); //removed %turn so just 1 person is playing
		System.out.println(message);

		if(stage == Stage.BUY){
			turn++;
		}
		notifyObservers(message);
		
		//only play 30 moves
		if (turn < 30) {
			Stage nextStage = (stage == Stage.ACTION) ? Stage.BUY: Stage.ACTION;
			playTurn(nextStage);
		}
	}
	
}
