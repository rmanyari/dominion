package ca.dominion.model.impl.cards;

import java.util.ArrayList;
import java.util.List;

import ca.dominion.model.Action;
import ca.dominion.model.Card;
import ca.dominion.model.CardType;
import ca.dominion.model.Stage;
import ca.dominion.model.impl.TrashYourselfAction;

public class Feast implements Card{

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
		l.add(new TrashYourselfAction());
		return l;
	}

	@Override
	public int getCost() {
		return 4;
	}

	public boolean isPlayable(List<Card> cards){
		return true;
	}
}
