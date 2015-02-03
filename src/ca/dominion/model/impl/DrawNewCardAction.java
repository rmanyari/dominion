package ca.dominion.model.impl;

import ca.dominion.model.Action;

public class DrawNewCardAction implements Action{

	private int value;
	
	public DrawNewCardAction(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
