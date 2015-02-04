package ca.dominion.model.impl;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Card;
import ca.dominion.model.CardName;
import ca.dominion.model.impl.cards.*;;

public class GameDeck {
	private ArrayList<Pile> cardsPiles = new ArrayList<Pile>();
	private ArrayList<Card> cardsInDeck = new ArrayList<Card>();

	public GameDeck (List<Card> actionCards) {

		//add Treasure Piles
		Pile copperPile = new Pile(new Copper(CardName.COPPER), 45);
		Pile silverPile = new Pile(new Silver(CardName.SILVER), 30);
		Pile goldPile = new Pile(new Gold(CardName.GOLD), 25);
		cardsPiles.add(copperPile);
		cardsPiles.add(silverPile);
		cardsPiles.add(goldPile);
		
		Pile estatePile = new Pile(new Estate(CardName.ESTATE), 20);
		cardsPiles.add(estatePile);

		cardsInDeck = (ArrayList<Card>) actionCards;
		for (Card c: actionCards) {
			cardsInDeck.add(c);
			Pile aPile = new Pile(c, 25);
			cardsPiles.add(aPile);
		}
		
	}
	
	public Card buyCard(Card card) {
		for(Pile p: cardsPiles) {
			if (p.getPileType() == card.getName()) {
				return p.drawCard();
			}
		}
		return null;
	}
	
	public List<Card> getAvailableCards() {
		return cardsInDeck;
	}
}
