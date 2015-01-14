package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;

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
