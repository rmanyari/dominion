package ca.dominion.model.impl.cards;

import java.util.ArrayList;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardType;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.DrawNewCardAction;

public class Smithy implements Card{

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
		l.add(new DrawNewCardAction(3));
		return l;
	}

	@Override
	public int getCost() {
		return 4;
	}
	
}
