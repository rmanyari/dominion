package ca.dominion.model;

import java.util.ArrayList;

import ca.dominion.exceptions.NotEnoughGoldException;

public class Player {

	ArrayList<Card> drawPile = new ArrayList<Card>();
	ArrayList<Card> discardPile = new ArrayList<Card>();
	ArrayList<Card> hand = new ArrayList<Card>();
	
	private int availableGold = 0;
	
	public void useGold(int amount) throws NotEnoughGoldException{
		if(amount >= availableGold)
			availableGold -= amount;
		else
			throw new NotEnoughGoldException();
	}
	
	public void addGold(int amount){
		availableGold += amount;
	}
	
	public int getAvailableGold(){
		return availableGold;
	}
	
	public int getVictoryPoints(){
		for (Card card : drawPile) {
			if(card.getCardType() == CardType.VICTORY){
				//TODO	
			}
		}
		return 0;
	}
	
}
