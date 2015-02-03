package ca.dominion.model.impl;

import ca.dominion.model.Action;

public class IncrementBuysAction implements Action {

	private int value;
	
	public IncrementBuysAction(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
