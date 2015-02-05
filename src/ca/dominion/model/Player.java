package ca.dominion.model;

import java.util.*;
import ca.dominion.model.impl.*;
import ca.dominion.model.impl.cards.*;
import ca.dominion.exceptions.NotEnoughTreasureException;

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
			if (c.getCost()>availableTreasure) avCardsToBuy.remove(c);
		}
		return avCardsToBuy;
	}
	
	public void playBuyPhase() {
		ArrayList<Card> avCardsToBuy = (ArrayList<Card>) getCardsAbleToBuy();
		Card c = getRandomCard(avCardsToBuy);
		if (c != null) {
			buyCard(c);
			availableTreasure -= c.getCost();
		}
		
	}
	
	private void buyCard(Card card) {
		Card bCard = gameDeck.buyCard(card);
		discardPile.addCard(bCard);
		
	}
	
	public List<Action> play(Stage stage, boolean firstTime){
		if(firstTime){
			availableActions = 1;
			availableBuys = 1;
		}
		
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
			return availableActions <= 0;
		}
		if(stage == Stage.BUY){
			return availableBuys <= 0;
		}
		return true;
	}
	
}
