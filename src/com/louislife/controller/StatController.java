package com.louislife.controller;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Match;
import com.louislife.model.Team;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatController implements Initializable,
ControlledScreen, GamePlayListener {
	
	

	ScreensController controller;
	
	/** XML Properties **/
	@FXML private TableView tableView;
	@FXML private TableColumn<Team, String> colRank;
	@FXML private TableColumn<Team, String> colTeamName;
	@FXML private TableColumn<Team, String> colPlayed;
	@FXML private TableColumn<Team, String> colWin;
	@FXML private TableColumn<Team, String> colTie;
	@FXML private TableColumn<Team, String> colLoss;
	@FXML private TableColumn<Team, String> colPoints;

	private ArrayList<Match> matches;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainApplication.addListener(this);

		fillTable();
	}


	public void fillTable() {
		colRank.setCellValueFactory(new PropertyValueFactory<Team, String>("rank"));
		colTeamName.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
		colPlayed.setCellValueFactory(new PropertyValueFactory<Team, String>("played"));
		colWin.setCellValueFactory(new PropertyValueFactory<Team, String>("win"));
		colTie.setCellValueFactory(new PropertyValueFactory<Team, String>("tie"));
		colLoss.setCellValueFactory(new PropertyValueFactory<Team, String>("loss"));
		colPoints.setCellValueFactory(new PropertyValueFactory<Team, String>("points"));

		tableView.getItems().setAll(Game.getInstance().getRankList(0));
	}

	@Override
	public void onGamePlayed() {
		fillTable();
	}
}
