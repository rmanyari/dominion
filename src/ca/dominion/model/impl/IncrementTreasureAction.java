package ca.dominion.model.impl;

import ca.dominion.model.Action;

public class IncrementTreasureAction implements Action {

	private int value;
	
	public IncrementTreasureAction(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
