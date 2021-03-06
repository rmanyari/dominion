package ca.dominion.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private ArrayList<Card> cards = new ArrayList<Card>();

	public Hand() {
		
	}
	
	public Hand(List<Card> cards) {
		this.cards = (ArrayList<Card>) cards;
		
	}
	
	public void draw(Card c) {
		cards.add(c);
	}
	
	public void discard(Card c) {
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
	
	public int getTotalTreausure() {
		int treasure = 0;
		for (Card c : cards) {
			if(c.getStage() == Stage.BUY){
				treasure += c.getValue();
			}
		}
		return treasure;
	}
	
	public List<Card> getTreausureCards() {
		List<Card> treausureCards = new ArrayList<>();
		for (Card c : cards) {
			if(c.getStage() == Stage.BUY){
				treausureCards.add(c);
			}
		}
		return treausureCards;
	}
}
