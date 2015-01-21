package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;

/**
 * Controller of the Dashboard, the main navigation screen within gameoverview
 * @author Matthijs
 *
 */

public class DashboardController implements Initializable, ControlledScreen {

	private static TabPane navigationPane;
	ScreensController controller;

	/** XML Properties **/
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	/**
	 * method is called as intance is loaded
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Laad de load games

	}
	
	/**
	 * Method that sets the navigationPane of the parent gameoverview, so we can access and manipulate it.
	 * @param tabs tabPane to set
	 */
	public static void setNavigationPane( TabPane tabs){
		navigationPane=tabs;
	}
	
	@FXML protected void onClickNextGame(Event e) {

		System.out.println("Next game");
		int day = Game.getInstance().getCurrentDay();
		for(int i = 0; i < Game.getInstance().getMatches().size(); i++){
			if(Game.getInstance().getMatches().get(i).getDay() >= day && Game.getInstance().getMatches().get(i).getDay() < day+7){
				Game.getInstance().getMatches().get(i).play(System.currentTimeMillis());
				
				// Award prize money
				Game.getInstance().getMatches().get(i).calculateHomeCredit();
				Game.getInstance().getMatches().get(i).calculateAwayCredit();
				
				System.out.println("Played: " + Game.getInstance().getMatches().get(i).toString());
			}
		}
		Game.getInstance().nextWeek();
		
		GameOverviewController g = (GameOverviewController)MainApplication.mainContainer.getController(MainApplication.OVERVIEW);
		g.updateDate();
	}
	
	@FXML protected void onClickTeam(Event e) {
		navigationPane.getSelectionModel().select(1);

	}
	
	@FXML protected void onClickLeaderboard(Event e) {
		navigationPane.getSelectionModel().select(2);
	}
	
	@FXML protected void onClickMarket(Event e) {
		navigationPane.getSelectionModel().select(3);
	}
	
}
