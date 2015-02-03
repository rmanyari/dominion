package ca.dominion.model.impl;

import ca.dominion.model.Action;

public class GainNewCardAction implements Action{

	private int value;
	
	public GainNewCardAction(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
