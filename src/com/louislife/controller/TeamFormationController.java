package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Player;
import com.louislife.model.Team;

public class TeamFormationController implements Initializable,
ControlledScreen {

	ScreensController controller;

	/** XML Properties **/
	@FXML private Pane container;
	
	@FXML private Pane p1;
	@FXML private Pane p2;
	@FXML private Pane p3;
	@FXML private Pane p4;
	@FXML private Pane p5;
	// More to add
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setTeam(Game.getInstance().getUserTeam());

	}
	
	public void setTeam(Team team) {
		int playerNumber = 1;
		for (Player player : team.getPlayers()) {
			Label label = new Label(player.getSurname());
			Pane pane = getPane(playerNumber);
			if (pane != null) {
				pane.getChildren().add(label);
			}
			playerNumber++;
		}
	}
	
	private Pane getPane(int i) {
		switch(i) {
		case 1:
			return p1;
		case 2:
			return p2;
		case 3:
			return p3;
		case 4:
			return p4;
		case 5:
			return p5;
		}
		return null;
	}
}