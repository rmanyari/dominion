package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardType;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.IncrementTreasureAction;

public class Copper implements Card{

	@Override
	public CardType getCardType() {
		return CardType.TREASURE;
	}

	@Override
	public Stage getStage() {
		return Stage.BUY;
	}

	public boolean isPlayable(List<Card> cards){
		return true;
	}
	
	@Override
	public List<Action> playCard() {
		ArrayList<Action> l = new ArrayList<Action>();
		l.add(new IncrementTreasureAction(1));
		return l;
	}

	@Override
	public int getCost() {
		return 0;
	}

}
