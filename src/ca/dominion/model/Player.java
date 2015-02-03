package ca.dominion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import ca.dominion.exceptions.NotEnoughTreasureException;

public class Player implements Observer{

	ArrayList<Card> drawPile = new ArrayList<Card>();
	ArrayList<Card> discardPile = new ArrayList<Card>();
	ArrayList<Card> hand = new ArrayList<Card>();
	
	private int availableTreasure = 0;
	private String id;
	private Stage currentStage;
	
	public Player(){
		id = UUID.randomUUID().toString();
	}
	
	public void useTreasure(int amount) throws NotEnoughTreasureException{
		if(amount >= availableTreasure)
			availableTreasure -= amount;
		else
			throw new NotEnoughTreasureException();
	}
	
	public void addTreasure(int amount){
		availableTreasure += amount;
	}
	
	public int getAvailableTreasure(){
		return availableTreasure;
	}
	
	public int getVictoryPoints(){
		for (Card card : drawPile) {
			if(card.getCardType() == CardType.VICTORY){
				//TODO	
			}
		}
		return 0;
	}

	public String getId(){
		return id;
	}
	
	public List<Card> getAllowedActions(){
		List<Card> allowed = new ArrayList<>();
		for (Card card : hand) {
			if(card.getStage() == currentStage){
				
			}
		}
		return null;
	}
	
	@Override
	public void update(Observable o, Object turn) {
		String message = (String)turn;
		String[] parts = message.split("-");
		currentStage = Stage.valueOf(parts[0]);
		
		if(parts[0].equals(Stage.REACTION.name())){
			
			// Ignore ID and do wtv
			
		}else if(parts[1].equals(id)){
			if(parts[0].equals(Stage.ACTION.name())){
				
				// Action
				
			}else{
				
				// Buy
				
			}
		}
	}
	
}
