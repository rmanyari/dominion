package ca.dominion.model.impl;

import ca.dominion.model.Action;

public class DrawUntilAction implements Action{

	private int value;
	
	public DrawUntilAction(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
