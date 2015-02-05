package ca.dominion.model.impl;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.*;

public class Pile {

	private ArrayList<Card> cards = new ArrayList<Card>();
	private CardName pileType;
	
	public Pile (CardName pileType) {
		this.pileType = pileType;
	}
	
	public Pile (CardName pileType, List<Card> cards) {
		this.pileType = pileType;
		this.cards = (ArrayList<Card>) cards;
	}
	
	public Pile (Card card, int num) {
		this.pileType = card.getName();
		ArrayList<Card> cardsList = new ArrayList<Card>();
		for (int i = 0; i<num; i++) cardsList.add(card);
		this.cards = cardsList;
	}
	
	public Card drawCard() {
		Card c = cards.get(getSize()-1);
		cards.remove(c);
		return c;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void removeCard(Card card) {
		cards.remove(card);
	}
	
	public int getSize() {
		return cards.size();
	}

	public CardName getPileType() {
		return pileType;
	}
}
