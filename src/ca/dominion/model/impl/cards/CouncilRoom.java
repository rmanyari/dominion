package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardName;
import ca.dominion.model.CardType;
import ca.dominion.model.Hand;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.DrawNewCardAction;
import ca.dominion.model.impl.IncrementBuysAction;

public class CouncilRoom implements Card {

	private CardName name;
	private int value = 0;

	public CouncilRoom (CardName name) {
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
		l.add(new DrawNewCardAction(4));
		l.add(new IncrementBuysAction(1));
		return l;
	}

	@Override
	public int getCost() {
		return 5;
	}

	@Override
	public boolean isPlayable(Hand hand){
		return true;
	}
<<<<<<< HEAD
	
=======

	@Override
	public int getValue() {
		return value;
	}
>>>>>>> 6f317d593f4b2e7f104f30e710a87a1ebbd8f878
}
