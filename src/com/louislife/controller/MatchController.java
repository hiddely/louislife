package com.louislife.controller;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Match;
import com.louislife.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.*;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MatchController implements Initializable,
ControlledScreen {
	
	

	ScreensController controller;
	
	/** XML Properties **/
	@FXML private ListView<String> teamList;
	@FXML private Label matchTitle;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ArrayList<Match> matches = new ArrayList<Match>();
		int day = Game.getInstance().getCurrentDay();
		for(int i = 0; i < Game.getInstance().getMatches().size(); i++){
			if(Game.getInstance().getMatches().get(i).getDay() >= day-7 && Game.getInstance().getMatches().get(i).getDay() < day){
				Game.getInstance().getMatches().get(i).play(System.currentTimeMillis());
				matches.add(Game.getInstance().getMatches().get(i));
				System.out.println("Played: " + Game.getInstance().getMatches().get(i).toString());
			}
		}
		String[] playerListDisplay = new String[matches.size()];
		
		for (int i = 0; i < matches.size(); i++) {
			Match p = matches.get(i);
			playerListDisplay[i] = p.getTH().getName() + " vs. " + p.getTA().getName() + "\n" + p.getScore_home() + " - " + p.getScore_away();
		System.out.println(playerListDisplay[i]);
		}
		// En zet ze dynamisch in de UI
		ObservableList<String> items =FXCollections.observableArrayList(playerListDisplay);
		teamList.setItems(items);


	}
}
