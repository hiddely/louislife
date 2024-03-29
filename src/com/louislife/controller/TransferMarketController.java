package com.louislife.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

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
	@FXML 
	private TextField bidField;
	@FXML
	private Button bidButton;
	
	
	
	

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
		
		bidField.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				
				if (Integer.parseInt(bidField.getText())==0
						||Integer.parseInt(bidField.getText())<0
						||Integer.parseInt(bidField.getText())>Game.getInstance().getUserTeam().getBalance()) {
					bidField.setText("0");
				}
				bidSlider.setValue((double)Integer.parseInt(bidField.getText()));

				
			}
			
		});

			
			
			
		teamsList.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				updatePlayerList();
			}
			
		});
		
		playerList.setOnMouseClicked(new EventHandler<Event>(){
			@Override
			public void handle(Event event){
				updatePlayerLabels();
				
			}
		});
		
		bidButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				placeBid();
			}
			
			
		});
		
		bidSlider.setMin(0);
		bidSlider.setMax(1);
		bidSlider.valueProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				updateSlider();
				bidField.setText(Integer.toString(newValue.intValue()));
				

				
			}
			
			
			
			
		});
		
		updateTeamsList();
		updatePlayerList();
		updateSlider();
		
		MainApplication.addListener(this);
	}
	public void updateSlider(){
		bidSlider.setMin(0);
		if (Game.getInstance().getUserTeam().getBalance()==0)
			bidSlider.setDisable(true);
		else{
			bidSlider.setDisable(false);
			bidSlider.setMax(Game.getInstance().getUserTeam().getBalance());
		}
		
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
	public void placeBid(){

		int index = playerList.getSelectionModel().getSelectedIndex();
		if (index!=-1){
			Team selectedTeam= teams.get(teamsList.getSelectionModel().getSelectedIndex());
			Player selectedPlayer= selectedTeam.getPlayers().get(index);
			if (selectedTeam.acceptsBid(selectedPlayer, Integer.parseInt(bidField.getText()))){
				Game.getInstance().getUserTeam().setBalance(Game.getInstance().getUserTeam().getBalance()-Integer.parseInt(bidField.getText()));
				selectedTeam.setBalance(selectedTeam.getBalance()+Integer.parseInt(bidField.getText()));
				Game.getInstance().getUserTeam().addPlayer(selectedPlayer);
				selectedTeam.getPlayers().remove(selectedPlayer);
				Label label= new Label();
		        label.setText("We'll accept that offer Louis,\nbut only because you're so handsome!");
		        Font myFont = Font.font(null, FontWeight.BOLD, 20);
		        label.setFont(myFont);
		        label.setTextFill(Color.GREEN);
		        ArrayList<Node> list= new ArrayList<Node>();
		        list.add(label);
		        MainApplication.makePopup(list);
			        
				MainApplication.sendNextGame();
				
			   
		        System.out.println("Transfer completed");

			}
		}
		
		
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
	
	public void updatePlayerLabels(){
	
		int index = playerList.getSelectionModel().getSelectedIndex();
		Team selectedTeam= teams.get(teamsList.getSelectionModel().getSelectedIndex());
		Player selectedPlayer= selectedTeam.getPlayers().get(index);
		nameLabel.setText(selectedPlayer.getFirstname()+" "+selectedPlayer.getSurname());
		attLabel.setText(Integer.toString((int)(selectedPlayer.getOffensiveScore())));
		defLabel.setText(Integer.toString((int)(selectedPlayer.getDefensiveScore())));
		stamLabel.setText(Integer.toString((int)(selectedPlayer.getStaminaScore())));
		if(selectedPlayer.calculatePrice()<Game.getInstance().getUserTeam().getBalance())
			bidField.setText(Integer.toString(selectedPlayer.calculatePrice()));
		else
			bidField.setText(Integer.toString(Game.getInstance().getUserTeam().getBalance()));
		bidSlider.setValue((double)Integer.parseInt(bidField.getText()));

		
	}

	@Override
	public void onGamePlayed() {
		updateTeamsList();
		updatePlayerList();
		updateSlider();
		
	}

}
