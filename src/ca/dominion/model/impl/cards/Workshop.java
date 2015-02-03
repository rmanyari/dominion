package ca.dominion.model.impl.cards;

import java.util.ArrayList;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardType;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.GainNewCardAction;

public class Workshop implements Card{

	@Override
	public CardType getCardType() {
		return CardType.ACTION;
	}

	@Override
	public Stage getStage() {
		return Stage.ACTION;
	}

	@Override
	public ArrayList<Action> playCard() {
		ArrayList<Action> l = new ArrayList<Action>();
		l.add(new GainNewCardAction(4));
		return l;
	}

	@Override
	public int getCost() {
		return 3;
	}

}
