package ca.dominion.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import ca.dominion.exceptions.NotEnoughTreasureException;
import java.util.Random;

import ca.dominion.model.impl.GameDeck;
import ca.dominion.model.impl.Pile;
import ca.dominion.model.impl.cards.*;

public class Player implements Observer{

	private Pile drawPile;
	private Pile discardPile;
	private Hand hand;
	
	private int availableTreasure = 0;
	private String id;
	private Stage currentStage;
	private Random randomGenerator = new Random();
	private GameDeck gameDeck;
	
	public Player(GameDeck gameDeck) {
		this.gameDeck = gameDeck;
		drawPile = new Pile(CardName.NONE);
		discardPile = new Pile(CardName.NONE);
		
		id = UUID.randomUUID().toString();
		randomGenerator = new Random();
		
		ArrayList<Card> handCards = new ArrayList<Card>();
		for (int i = 0; i<7; i++) handCards.add(new Copper(CardName.COPPER));
		for (int i = 0; i<3; i++) handCards.add(new Estate(CardName.ESTATE));
		shuffleCards(handCards);
		this.hand = new Hand(handCards);
		
		
	}
	
	public void useTreasure(int amount) throws NotEnoughTreasureException {
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
		return 0;
	}

	public String getId(){
		return id;
	}
	
	public void drawCard() {
		Card c = drawPile.drawCard();
		drawPile.removeCard(c);
		hand.addCard(c);
	}
	
	public void shuffleCards(List<Card> cards) {
		Collections.shuffle(cards);
	}

	public Card getRandomCard(List<Card> cards) {
		if (cards.size() == 0) return null;
		int index = randomGenerator.nextInt(cards.size());
		return (Card) cards.get(index);
	}
	
	public void playActionPhase() {
		List<Card> allowed = hand.getAllowedActions();
		while (allowed.size()!=0) {
			Card card = getRandomCard(allowed);
			List<Action> actions = card.playCard();
			for (Action a: actions) {
				switch (a.getClass().getName()){
					case "DrawNewCardAction":
						drawCard();
						System.out.print("draw action");
					default:
						
				}
						
			}
			
			allowed = hand.getAllowedActions();
		}
	}
	
	public List<Card> getCardsAbleToBuy() {
		ArrayList<Card> avCardsToBuy = (ArrayList<Card>) gameDeck.getAvailableCards();
		
		for (Card c: avCardsToBuy) {
			if (c.getCost()>getAvailableTreasure()) avCardsToBuy.remove(c);
		}
		return avCardsToBuy;
	}
	
	public void playBuyPhase() {
		int treasure = getAvailableTreasure();
		ArrayList<Card> avCardsToBuy = (ArrayList<Card>) getCardsAbleToBuy();
		Card c = getRandomCard(avCardsToBuy);
		if (c != null) {
			buyCard(c);
		}
		
	}
	
	private void buyCard(Card card) {
		Card bCard = gameDeck.buyCard(card);
		discardPile.addCard(bCard);
		
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
				playActionPhase();
				
			}else{
				
				// Buy
				playBuyPhase();
			}
		}
	}
	
}
