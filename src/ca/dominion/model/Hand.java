package ca.dominion.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private ArrayList<Card> cards = new ArrayList<Card>();

	public Hand(List<Card> cards) {
		this.cards = (ArrayList<Card>) cards;
		
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}
	
	public void removeCard(Card c) {
		cards.remove(c);
	}
	
	public List<Card> getAllowedActions() {
		List<Card> allowed = new ArrayList<>();
		for (Card card : cards) {
			if(card.getStage() == Stage.ACTION){
				if (card.isPlayable(this)) allowed.add(card);
				
			}
		}
		return allowed;
	}
	
	
}
