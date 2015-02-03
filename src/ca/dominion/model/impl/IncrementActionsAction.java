package ca.dominion.model.impl;

import ca.dominion.model.Action;

public class IncrementActionsAction implements Action {
	
	private int value;
	
	public IncrementActionsAction(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
