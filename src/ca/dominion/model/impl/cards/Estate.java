package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardName;
import ca.dominion.model.CardType;
import ca.dominion.model.Hand;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.IncrementTreasureAction;

public class Estate implements Card{

	private CardName name;
	
	public Estate (CardName name) {
		this.name = name;
	}
	
	@Override
	public CardName getName() {
		return name;
	}
	
	
	@Override
	public CardType getCardType() {
		return CardType.VICTORY;
	}

	@Override
	public Stage getStage() {
		return Stage.NONE;
	}

	@Override
	public List<Action> playCard() {
		return null;
	}

	@Override
	public int getCost() {
		return 2;
	}
	
	public boolean isPlayable(Hand hand){
		return true;
	}
}
