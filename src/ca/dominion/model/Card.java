package ca.dominion.model;

import java.util.List;

public interface Card {

	public CardType getCardType();
	public Stage getStage();
	public List<Action> playCard();
	public int getCost();
	public boolean isPlayable(List<Card> cards);
}
