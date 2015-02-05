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

public class Player implements Observer {

	private Pile drawPile;
	private Pile discardPile;
	private Hand hand;
	
	private int availableTreasure;
	private int availableBuys;
	private int availableActions;

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
		
		resetAbilities();
		
	}
	
	private void resetAbilities() {
		availableActions = 1;
		availableBuys = 1;
		availableTreasure = 5;
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
		
		System.out.println("drew card");

	}
	
	public void shuffleCards(List<Card> cards) {
		Collections.shuffle(cards);
	}

	public Card getRandomCard(List<Card> cards) {
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
					default:
						
				}
						
			}
			
			allowed = hand.getAllowedActions();
		}
		
		endActionPhase();
	}
	
	public void endActionPhase() {
		
	}
	public void discardHand() {
		
	}
	
	public List<Card> getCardsAbleToBuy() {
		ArrayList<Card> allCardstoBuy = (ArrayList<Card>) gameDeck.getAvailableCards();
		System.out.println(allCardstoBuy.size());

		ArrayList<Card> avCardsToBuy = new ArrayList<Card>();

		for (Card c: allCardstoBuy) {
			if (c.getCost()<getAvailableTreasure()) avCardsToBuy.add(c);
		}
		return avCardsToBuy;
	}
	
	//we buy as many cards as possible randomly choosing one each time
	public void playBuyPhase() {
		
		ArrayList<Card> avCardsToBuy = (ArrayList<Card>) getCardsAbleToBuy();
		System.out.println("in buy phase, av cards: " + avCardsToBuy.size());

		while(availableBuys>0 && avCardsToBuy.size()>0) {
			Card c = getRandomCard(avCardsToBuy);
			buyCard(c);
			avCardsToBuy = (ArrayList<Card>) getCardsAbleToBuy();
		}
	}
	
	private void buyCard(Card card) {
		Card bCard = gameDeck.buyCard(card);
		discardPile.addCard(bCard);
		availableTreasure -= card.getCost();
		availableBuys -=1;
		System.out.println("bought Card: " + card.getName().toString() + ", remaining treasure: " + availableTreasure);

	}
	
	@Override
	public void update(Observable o, Object turn) {
		String message = (String)turn;
		String[] parts = message.split("_");

		currentStage = Stage.valueOf(parts[0]);

		if(parts[0].equals(Stage.REACTION.name())){
			
			// Ignore ID and do wtv
			
			
		}else if(parts[1].equals(id)){
			if(parts[0].equals(Stage.ACTION.name())){
				// Action
				resetAbilities();
				playActionPhase();
				
			}else{
				// Buy
				playBuyPhase();
			}
		}
	}
	
}
