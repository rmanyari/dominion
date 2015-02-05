package ca.dominion.model;

import java.util.List;


public interface Card {
	
	public CardType getCardType();
	public Stage getStage();
	public List<Action> playCard();
	public int getCost();
	public CardName getName();
	public boolean isPlayable(Hand hand);
	int getValue();
}
