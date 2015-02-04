package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardName;
import ca.dominion.model.CardType;
import ca.dominion.model.Hand;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.GainNewCardAction;

public class Workshop implements Card{

	private CardName name;
	
	public Workshop(CardName name) {
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
	public ArrayList<Action> playCard() {
		ArrayList<Action> l = new ArrayList<Action>();
		l.add(new GainNewCardAction(4));
		return l;
	}

	@Override
	public int getCost() {
		return 3;
	}

	public boolean isPlayable(Hand hand){
		return true;
	}
}
