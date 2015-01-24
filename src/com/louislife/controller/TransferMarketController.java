package com.louislife.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Player;
import com.louislife.model.Team;

public class TransferMarketController implements Initializable,
		ControlledScreen, GamePlayListener {
	ScreensController controller;
	ArrayList<Team> teams;
	
	@FXML
	private Label nameLabel;
	@FXML
	private Label attLabel;
	@FXML
	private Label defLabel;
	@FXML
	private Label stamLabel;	
	@FXML
	private Slider bidSlider;	
	@FXML
	private ListView<String> teamsList;
	@FXML
	private ListView<String> playerList;
	
	
	
	

	public TransferMarketController() {
		teams= new ArrayList<Team>();
		teams.addAll(Game.getInstance().getLeagues().get(0).getTeams());
		teams.remove(Game.getInstance().getUserTeam());
		
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teamsList.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				updatePlayerList();
			}
			
		});
		
		playerList.setOnMouseClicked(new EventHandler<Event>(){
			@Override
			public void handle(Event event){
				int index = playerList.getSelectionModel().getSelectedIndex();
				Team selectedTeam= teams.get(teamsList.getSelectionModel().getSelectedIndex());
				Player selectedPlayer= selectedTeam.getPlayers().get(index);
				nameLabel.setText(selectedPlayer.getFirstname()+" "+selectedPlayer.getSurname());
				attLabel.setText(Integer.toString((int)(selectedPlayer.getOffensiveScore())));
				
				
			}
			
			
		});
		
		updateTeamsList();
		updatePlayerList();
		
		MainApplication.addListener(this);
	}
	
	public void updateTeamsList(){
		
		String[] teamsListDisplay = new String[teams.size()];
		
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			teamsListDisplay[i] = team.getName();
		
		}
		// En zet ze dynamisch in de UI
		ObservableList<String> items =FXCollections.observableArrayList(teamsListDisplay);
		teamsList.setItems(items);
				
		
	}
	
	
	public void updatePlayerList(){
		
		int index= teamsList.getSelectionModel().getSelectedIndex();
		if (index>=0){
			ArrayList<Player> players= teams.get(index).getPlayers();
			String[] teamListDisplay = new String[players.size()];
			
			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				teamListDisplay[i] =   p.getSurname()+", "+p.getFirstname() + " " +"\n Att: "+p.getOffensiveScore()+" Mid: "+p.getStaminaScore()+" Def: "+p.getDefensiveScore() ;
	
			
			}
			// En zet ze dynamisch in de UI
			ObservableList<String> items =FXCollections.observableArrayList(teamListDisplay);
			playerList.setItems(items);
		};
		
		
	}

	@Override
	public void onGamePlayed() {
		updateTeamsList();
		updatePlayerList();
		
	}

}
