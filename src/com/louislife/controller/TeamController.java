package com.louislife.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Player;

public class TeamController implements Initializable,
ControlledScreen, GamePlayListener {
	
	

	ScreensController controller;
	
	/** XML Properties **/
	@FXML private ListView<String> teamList;
	@FXML private Label teamLabel;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MainApplication.addListener(this);


		teamLabel.setText(Game.getInstance().getUserTeam().getName());

		updateTeamList();
		
		teamList.setOnDragDetected(new EventHandler<MouseEvent>() {
		    @Override
			public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        Dragboard db = teamList.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putString(teamList.getSelectionModel().getSelectedItem());
		        db.setContent(content);
		        db.setDragView(new Image(new File("images/user.png").toURI().toString(),42,42,true,true));

		        event.consume();
		    }
		
		    
		});
		
		teamList.setOnDragDone(new EventHandler<DragEvent>() {
		    @Override
			public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        /* if the data was successfully moved, clear it */
		    	//update list
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	updateTeamList();
		        }
		        event.consume();
		    }
		});


	}
	public void updateTeamList(){
		teamList.setItems(null);
    	
    	ArrayList<Player> players = Game.getInstance().getUserTeam().getPlayers();
		String[] playerListDisplay = new String[players.size()-11];
		
		for (int i = 11; i < players.size(); i++) {
			Player p = players.get(i);
			playerListDisplay[i-11] = p.getJerseyNumber()+1 + " - "  + p.getSurname()+", "+p.getFirstname() + " " +"\n Att: "+p.getOffensiveScore()+" Mid: "+p.getStaminaScore()+" Def: "+p.getDefensiveScore() ;
		
		}
		// En zet ze dynamisch in de UI
		ObservableList<String> items =FXCollections.observableArrayList(playerListDisplay);
		teamList.setItems(items);
		
	}
	@Override
	public void onGamePlayed() {
		updateTeamList();
	}
}
