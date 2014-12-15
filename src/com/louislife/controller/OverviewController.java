package com.louislife.controller;

import java.util.Observable;

import com.louislife.model.*;

/**
 * Controller Class to control the events in the Overview screen (main game window).
 * @author Pathemeous
 *
 */

public class OverviewController extends Observable {
	
	
	/**
	 * This method must update the model to reflect one match being played and bring a feedback in the view with the results of this match.
	 */
	public void nextMatch() {
		Game.getInstance().nextWeek();
		
		Match playedMatch = new Match(,Game.getInstance().getCurrentDay(),Game.getInstance().getUserTeam,);
		//TODO: Add the Controller method for SpelGenerator
	}
}
