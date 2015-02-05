package ca.dominion.model;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.impl.*;

public class Game{
	
	private List<Player> players = new ArrayList<Player>();
	private int turn = 0;
	protected GameDeck deck;
	protected Stage stage;
	
	public Game(GameDeck deck, List<Player> players){
		this.players.addAll(players);
		this.deck = deck;
	}
		
	public void playTurn(){
		Player p = players.get(players.size() % 1);
		boolean isDoneActionStage = false;
		boolean isDoneBuyStage = false;
		boolean firstActionStage = true;
		boolean firstBuyStage = true;
		
		while(!isDoneActionStage){
			System.out.println("Playing action");
			List<Action> actions = p.play(Stage.ACTION, firstActionStage);
			actionOnOtherPlayers(p.getId(), actions);
			firstActionStage = false;
			isDoneActionStage = p.isDoneWithStage(Stage.ACTION);
		}
		
		while(!isDoneBuyStage){
			System.out.println("Playing buy");
			p.play(Stage.BUY, firstBuyStage);
			firstBuyStage = false;
			isDoneBuyStage = p.isDoneWithStage(Stage.BUY);
		}
		
	}
	
	private void actionOnOtherPlayers(String uuid, List<Action> actions){
		
	}
	
}
