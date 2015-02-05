package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardName;
import ca.dominion.model.CardType;
import ca.dominion.model.Hand;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.IncrementActionsAction;
import ca.dominion.model.impl.IncrementBuysAction;
import ca.dominion.model.impl.IncrementTreasureAction;

public class Market implements Card{

	private CardName name;
	private int value = 0;

	public Market(CardName name) {
		this.name = name;
	}
	
	@Override
	public CardName getName() {
		return name;
	}
	
	@Override
	public CardType getCardType() {
		return CardType.ACTION;
	}

	@Override
	public Stage getStage() {
		return Stage.ACTION;
	}

	@Override
	public List<Action> playCard() {
		ArrayList<Action> l = new ArrayList<Action>();
		l.add(new IncrementBuysAction(1));
		l.add(new IncrementActionsAction(1));
		l.add(new IncrementTreasureAction(1));
		return l;
	}

	@Override
	public int getCost() {
		return 5;
	}

	public boolean isPlayable(Hand hand){
		return true;
	}

	@Override
	public int getValue() {
		return value;
	}
}
