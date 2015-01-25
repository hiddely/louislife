package com.louislife.controller;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MatchController implements Initializable,
ControlledScreen, GamePlayListener {
	
	

	ScreensController controller;
	
	/** XML Properties **/
	@FXML private ListView<String> teamList;
	@FXML private Label matchTitle;
	@FXML private ScrollPane eventsPane;

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

		Pane content = new Pane();
		eventsPane.setContent(content);

		for (int i = 0; i < m.getEvents_home().size(); i++) {
			Event e = m.getEvents_home().get(i);
			String label = e.getMinute() + ": " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getFirstname() + " " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getSurname() + " ";
			String icon = "";
			switch (e.getType()) {
				case GOAL:
					//Player p = Game.getInstance().getLeagues().get(0).m.getEvents_home().get(i).getPlayer();
					label += "scored a goal";
					icon = "icon_football.png";
					break;
				case YELLOWCARD:
					label += "received a yellow card";
					icon = "icon_yellowcard.png";
					break;
				case REDCARD:
					label += "received a red card";
					icon = "icon_redcard.png";
					break;
				case INJURY:
					label += "was injured";
					icon = "";
					break;
			}
			Label l = new Label(label);
			l.setTextFill(Color.BLACK);
			l.setFont(new Font("Avenir Medium", 16.0));
			l.setLayoutX(40.0);
			Image ii = new Image(new File("images/"+icon).toURI().toString(), 20, 20, false, false);
			final ImageView iconview = new ImageView();
			iconview.setLayoutX(5.0);
			iconview.setLayoutY(3.0);
			iconview.setImage(ii);
			Pane p = new Pane();
			p.getChildren().add(l);
			p.getChildren().add(iconview);
			p.setLayoutY(i * 30.0);
			content.getChildren().add(p);
		}

		for (int i = 0; i < m.getEvents_away().size(); i++) {
			Event e = m.getEvents_away().get(i);
			String label = e.getMinute() + ": " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getFirstname() + " " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getSurname() + " ";
			String icon = "";
			switch (e.getType()) {
				case GOAL:
					//Player p = Game.getInstance().getLeagues().get(0).m.getEvents_home().get(i).getPlayer();
					label += "scored a goal";
					icon = "icon_football.png";
					break;
				case YELLOWCARD:
					label += "received a yellow card";
					icon = "icon_yellowcard.png";
					break;
				case REDCARD:
					label += "received a red card";
					icon = "icon_redcard.png";
					break;
				case INJURY:
					label += "was injured";
					icon = "";
					break;
			}
			Label l = new Label(label);
			l.setTextFill(Color.BLACK);
			l.setFont(new Font("Avenir Medium", 16.0));
			l.setLayoutX(40.0);
			Image ii = new Image(new File("images/"+icon).toURI().toString(), 20, 20, false, false);
			final ImageView iconview = new ImageView();
			iconview.setLayoutX(5.0);
			iconview.setLayoutY(3.0);
			iconview.setImage(ii);
			Pane p = new Pane();
			p.getChildren().add(l);
			p.getChildren().add(iconview);
			p.setLayoutY(i * 30.0);
			p.setLayoutX(300);
			content.getChildren().add(p);
		}
	}

	@Override
	public void onGamePlayed() {
		updateMatches();
	}
}
