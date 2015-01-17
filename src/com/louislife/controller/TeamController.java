package com.louislife.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Player;

public class TeamController implements Initializable,
ControlledScreen {
	
	

	ScreensController controller;
	
	/** XML Properties **/
	@FXML private ListView<String> teamList;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ArrayList<Player> players = Game.getInstance().getUserTeam().getPlayers();
		String[] playerListDisplay = new String[players.size()];
		
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			playerListDisplay[i] = p.getJerseyNumber()+1 + " - "  + p.getSurname()+", "+p.getFirstname() + " " +"\n Att: "+p.getOffensiveScore()+" Mid: "+p.getStaminaScore()+" Def: "+p.getDefensiveScore() ;
		
		}
		// En zet ze dynamisch in de UI
		ObservableList<String> items =FXCollections.observableArrayList(playerListDisplay);
		teamList.setItems(items);


	}
}
