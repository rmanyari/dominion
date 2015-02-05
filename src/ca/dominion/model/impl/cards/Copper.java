package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardType;
import ca.dominion.model.Hand;
import ca.dominion.model.Stage;

import ca.dominion.model.CardName;


public class Copper implements Card {

	private CardName name;
	private int value = 1;
	
	public Copper (CardName name) {
		this.name = name;
	}
	
	@Override
	public CardType getCardType() {
		return CardType.TREASURE;
	}

	@Override
	public Stage getStage() {
		return Stage.BUY;
	}

	public boolean isPlayable(Hand hand){
		return true;
	}
	
	@Override
	public List<Action> playCard() {
		return null;
	}

	@Override
	public int getCost() {
		return 0;
	}

	@Override
	public CardName getName() {
		return name;
	}
	
	@Override
	public int getValue() {
		return value;
	}
}
