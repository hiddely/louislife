package com.louislife.controller;

import com.louislife.model.Game;

public abstract class ExplainableController {
	private boolean explained;

	public ExplainableController() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean hasBeenExplained(){
		if (Game.getInstance().getCurrentDay()==0&&!explained){
			System.out.print(Game.getInstance().getCurrentDay());
			return false;}
		return true;
	}
	public void setExplained(boolean bool){
		explained=bool;
	}
	public boolean getExplained( ){
		return explained;
	}
	public abstract void explain();

}
