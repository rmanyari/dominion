package ca.dominion.model;

import java.util.*;
import ca.dominion.model.impl.*;
import ca.dominion.model.impl.cards.*;

public class Player{

	private Pile drawPile;
	private Pile discardPile;
	private Hand hand;
	
	private int availableTreasure = 0;
	private int availableActions = 1;
	private int availableBuys = 1;

	private String id;
	private Random randomGenerator = new Random();
	private GameDeck gameDeck;
	
	public Player(GameDeck gameDeck) {
		this.gameDeck = gameDeck;
		drawPile = new Pile(CardName.NONE);
		discardPile = new Pile(CardName.NONE);
		
		id = UUID.randomUUID().toString();
		randomGenerator = new Random();
		
		ArrayList<Card> startingCards = new ArrayList<Card>();
		for (int i = 0; i<7; i++) startingCards.add(new Copper(CardName.COPPER));
		for (int i = 0; i<3; i++) startingCards.add(new Estate(CardName.ESTATE));
		shuffleCards(startingCards);
		this.drawPile = new Pile(CardName.NONE, startingCards);
		
		this.hand = new Hand();
		
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
	
	public int getVictoryPoints(){
		return 0;
	}

	public String getId(){
		return id;
	}
	
	public void drawCard() {
		Card c = drawPile.drawCard();
		drawPile.removeCard(c);
		hand.draw(c);
		System.out.println("drew card");
	}
	
	public void drawCard(int num) {
		for (int i = 0; i<num; i++) {
			Card c = drawPile.drawCard();
			drawPile.removeCard(c);
			hand.draw(c);
		}
		System.out.println("drew "+ num + " cards");

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
			if (c.getCost()<availableTreasure) avCardsToBuy.add(c);
		}
		return avCardsToBuy;
	}
	
	//we buy as many cards as possible randomly choosing one each time
	public void playBuyPhase() {
		cashInTreasure();
		
		ArrayList<Card> avCardsToBuy = (ArrayList<Card>) getCardsAbleToBuy();
		System.out.println("in buy phase, av cards: " + avCardsToBuy.size());

		while(availableBuys>0 && avCardsToBuy.size()>0) {
			Card c = getRandomCard(avCardsToBuy);
			buyCard(c);
			availableTreasure -= c.getCost();
			avCardsToBuy = (ArrayList<Card>) getCardsAbleToBuy();
		}
		
		endBuyPhase();
	}
	public void endBuyPhase() {

	}
	
	private void cashInTreasure() {
		availableTreasure = hand.getTotalTreausure();
		List<Card> treasureCards = hand.getTreausureCards();
		for (Card c: treasureCards) {
			hand.discard(c);
			discardPile.addCard(c);
		}
		
	}
	
	private void buyCard(Card card) {
		Card bCard = gameDeck.buyCard(card);
		discardPile.addCard(bCard);
		availableTreasure -= card.getCost();
		availableBuys -=1;
		System.out.println("bought Card: " + card.getName().toString() + ", remaining treasure: " + availableTreasure);

	}
	
	public List<Action> play(Stage stage){
		
		if(stage == Stage.ACTION && availableActions > 0){
			playActionPhase();
			availableActions--;
		}
		
		if(stage == Stage.BUY && availableBuys > 0){
			playBuyPhase();
			availableBuys--;
		}
		
		return null;
	}
	
	/***
	 * @param stage The stage the turn is at. Could be ACTION/BUY
	 * @return Whether the player can do more actions in the stage.
	 */
	public boolean isDoneWithStage(Stage stage){
		if(stage == Stage.ACTION){
			if(availableActions <= 0){
				availableActions = 1;
				return true;
			}else{
				return false;
			}
		}
		if(stage == Stage.BUY){
			if(availableBuys <= 0){
				availableBuys = 1;
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
}