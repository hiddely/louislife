package com.louislife.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.louislife.model.Match;
import com.louislife.model.Team;
import com.louislife.util.XMLParser;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

public class DashboardController extends ExplainableController implements Initializable, ControlledScreen, GamePlayListener {

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
		if(!super.getExplained())
			explain();
		
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


		// To end game: check if day is more than rounds
		int day = Game.getInstance().getCurrentDay();
		if (day > (Game.getInstance().getLeagues().get(0).getTeams().size()-1)*2*7) {
			// End game pop up

		}

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
			Label l = new Label("Louis, a transfer request has been made");

			ArrayList<Node> list= new ArrayList<Node>();
			list.add(l);

			MainApplication.makePopup(list);
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

	@Override
	public void explain() {
		Label label= new Label();
        label.setText("Welcome Louis!");
        Font myFont = Font.font(null, FontWeight.BOLD, 20);
        label.setFont(myFont);
        label.setTextFill(Color.WHITE);
        Label label2= new Label();
        label2.setText("Manage your team, and become this seasons champion!");
        Font myFont2 = Font.font(null, FontWeight.BOLD, 12);
        label2.setFont(myFont2);
        label2.setTextFill(Color.WHITE);
        ArrayList<Node> list= new ArrayList<Node>();
        list.add(label);
        list.add(label2);
        MainApplication.makePopup(list);
        super.setExplained(true);

	}
}
