package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.louislife.util.XMLParser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Controller of the Dashboard, the main navigation screen within gameoverview
 * @author Matthijs
 *
 */

public class DashboardController implements Initializable, ControlledScreen {

	private static TabPane navigationPane;
	ScreensController controller;

	/** XML Properties **/
	@FXML private Label teamLabel;
	
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
		teamLabel.setText(Game.getInstance().getUserTeam().getName());
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
		
		MainApplication.sendNextGame();

		// Save game
		XMLParser parser = null;
		try {
			parser = new XMLParser(Game.getInstance().getXmlName());
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		parser.writeGame(Game.getInstance());
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
	
	@FXML protected void onClickMainMenu(Event e){
		MainApplication.mainContainer.setScreen(MainApplication.MAIN_MENU);
	}
	
}
