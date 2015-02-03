package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardType;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.DrawNewCardAction;
import ca.dominion.model.impl.IncrementBuysAction;

public class CouncilRoom implements Card{

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
		l.add(new DrawNewCardAction(4));
		l.add(new IncrementBuysAction(1));
		return l;
	}

	@Override
	public int getCost() {
		return 5;
	}

	@Override
	public boolean isPlayable(List<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

}
