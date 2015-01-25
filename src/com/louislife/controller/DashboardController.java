package com.louislife.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.louislife.model.Match;
import com.louislife.model.Team;
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
import javafx.stage.Popup;
import sun.applet.Main;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Controller of the Dashboard, the main navigation screen within gameoverview
 * @author Matthijs
 *
 */

public class DashboardController implements Initializable, ControlledScreen, GamePlayListener {

	private static TabPane navigationPane;
	ScreensController controller;

	/** XML Properties **/
	@FXML private Label teamLabel;
	@FXML private Label nextLabel;

	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	/**
	 * method is called as intance is loaded
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainApplication.addListener(this);

		updateDashboard();
	}

	public void updateDashboard() {
		// Laad de load games
		teamLabel.setText(Game.getInstance().getUserTeam().getName());

		// Get next match for team
		int day = Game.getInstance().getCurrentDay();
		for(int i = 0; i < Game.getInstance().getMatches().size(); i++){
			if(Game.getInstance().getMatches().get(i).getDay() >= day && Game.getInstance().getMatches().get(i).getDay() < day+7){
				Match m = Game.getInstance().getMatches().get(i);
				if (m.getTH().equals(Game.getInstance().getUserTeam())) {
					nextLabel.setText("Home: " + m.getTA().getName());
				} else if (m.getTA().equals(Game.getInstance().getUserTeam())) {
					nextLabel.setText("Away: " + m.getTH().getName());
				}
			}
		}
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

		if (new Random().nextInt(1) == 0) {
			// Random transfer request
			Team reqteam = Game.getInstance().getUserTeam();
			while (reqteam == Game.getInstance().getUserTeam()) {
				reqteam = Game.getInstance().getLeagues().get(0).getTeams().get(new Random().nextInt(Game.getInstance().getLeagues().get(0).getTeams().size()));
			}
			// Reqteam is now a random team and not the users team


			final Popup popup = new Popup();
			popup.setX(300);
			popup.setY(200);

			Label l = new Label("Louis, a transfer request has been made");
			popup.getContent().add(l);

			popup.show(teamLabel.getScene().getWindow());
		}
		
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

	@FXML protected void onClickMatches(Event e) {
		navigationPane.getSelectionModel().select(4);
	}

	@FXML protected void onClickMainMenu(Event e){
		MainApplication.mainContainer.setScreen(MainApplication.MAIN_MENU);
	}

	@Override
	public void onGamePlayed() {
		updateDashboard();
	}
}
