package com.louislife.controller;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
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
import javafx.scene.control.Label;
import javafx.scene.input.*;
import sun.applet.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MatchController implements Initializable,
ControlledScreen, GamePlayListener {
	
	

	ScreensController controller;
	
	/** XML Properties **/
	@FXML private ListView<String> teamList;
	@FXML private Label matchTitle;

	private ArrayList<Match> matches;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainApplication.addListener(this);

		updateMatches();
	}

	public void updateMatches() {
		matches = new ArrayList<Match>();
		int day = Game.getInstance().getCurrentDay();
		for(int i = 0; i < Game.getInstance().getMatches().size(); i++){
			//if(Game.getInstance().getMatches().get(i).getDay() >= day-7 && Game.getInstance().getMatches().get(i).getDay() < day){
			if (Game.getInstance().getMatches().get(i).isPlayed()) {
				matches.add(Game.getInstance().getMatches().get(i));
				System.out.println("LOADINGMATCH: " + Game.getInstance().getMatches().get(i).toString());
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

		teamList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

		        /* Put a string on a dragboard */
				int i = teamList.getSelectionModel().getSelectedIndex();

				// Set match data
				setMatchDetails(matches.get(i));

				event.consume();
			}
		});
		teamList.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				/* Put a string on a dragboard */
				int i = teamList.getSelectionModel().getSelectedIndex();

				// Set match data
				setMatchDetails(matches.get(i));

				event.consume();
			}
		});
	}

	public void setMatchDetails(Match m) {
		matchTitle.setText("Match " + m.getTH().getName() + " vs. " + m.getTA().getName() + " " + m.getScore_home() + " - " + m.getScore_away());
	}

	@Override
	public void onGamePlayed() {
		updateMatches();
	}
}
