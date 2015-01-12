package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;

public class DashboardController implements Initializable, ControlledScreen {

	ScreensController controller;

	/** XML Properties **/
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Laad de load games

	}
	
	@FXML protected void onClickNextGame(Event e) {
		System.out.println("Next game");
		int day = Game.getInstance().getCurrentDay();
		for(int i = 0; i < Game.getInstance().getMatches().size(); i++){
			if(Game.getInstance().getMatches().get(i).getDay() >= day && Game.getInstance().getMatches().get(i).getDay() < day+7){
				Game.getInstance().getMatches().get(i).play(System.currentTimeMillis());
				System.out.println("Played: " + Game.getInstance().getMatches().get(i).toString());
			}
		}
		Game.getInstance().nextWeek();
	}
	
	@FXML protected void onClickTeam(Event e) {
		System.out.println("Team");
	}
	
	@FXML protected void onClickLeaderboard(Event e) {
		System.out.println("Leaderboard");
	}
	
	@FXML protected void onClickMarket(Event e) {
		System.out.println("Market");
	}
	
}
