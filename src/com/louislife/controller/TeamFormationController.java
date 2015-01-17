package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

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
	@FXML private Pane p6;
	@FXML private Pane p7;
	@FXML private Pane p8;
	@FXML private Pane p9;
	@FXML private Pane p10;
	@FXML private Pane p11;
	
	
	
	
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
			Label nameLabel = new Label(player.getSurname());
			nameLabel.setTranslateY(-20);
			nameLabel.setTranslateX(15);
			Pane pane = getPane(playerNumber);
			
			

			if (pane != null) {
				pane.getChildren().add(nameLabel);

				if (pane.getLayoutY()>300){
					Label statLabel = new Label("Def: "+player.getDefensiveScore());
					statLabel.setTranslateY(40);
					statLabel.setTranslateX(15);
					pane.getChildren().add(statLabel);
				}
				
				else if (pane.getLayoutY()>100){
					Label statLabel = new Label("Stam: "+player.getStaminaScore());
					statLabel.setTranslateY(40);
					statLabel.setTranslateX(15);
					pane.getChildren().add(statLabel);
				}
				
				else if (pane.getLayoutY()>50){
					Label statLabel = new Label("Att: "+player.getOffensiveScore());
					statLabel.setTranslateY(40);
					statLabel.setTranslateX(15);
					pane.getChildren().add(statLabel);
				}
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
		case 6:
			return p6;
		case 7:
			return p7;
		case 8:
			return p8;
		case 9:
			return p9;
		case 10:
			return p10;
		case 11:
			return p11;
		}
		return null;
	}
}