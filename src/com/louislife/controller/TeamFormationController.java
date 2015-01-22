package com.louislife.controller;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Player;
import com.louislife.model.Team;
import com.louislife.model.PlayerType;

public class TeamFormationController implements Initializable, ControlledScreen {

	ScreensController controller;
	private Team currentTeam;

	/** XML Properties **/
	@FXML
	private Pane container;

	@FXML
	private StackPane p1;
	@FXML
	private StackPane p2;
	@FXML
	private StackPane p3;
	@FXML
	private StackPane p4;
	@FXML
	private StackPane p5;
	@FXML
	private StackPane p6;
	@FXML
	private StackPane p7;
	@FXML
	private StackPane p8;
	@FXML
	private StackPane p9;
	@FXML
	private StackPane p10;
	@FXML
	private StackPane p11;

	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller = screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTeam(Game.getInstance().getUserTeam());
		setupDragDrop();

	}
	
	/**
	 * set up drag drop operations
	 */
	public void setupDragDrop(){
		for (int i=1;i<12;i++){
			StackPane pane= getPane(i);

			pane.setOnDragOver(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* data is dragged over the target */
			        /* accept it only if it is not dragged from the same node 
			         * and if it has a string data */
			        if (event.getGestureSource() != pane &&
			                event.getDragboard().hasString()) {
			            /* allow for moving */
			            event.acceptTransferModes(TransferMode.MOVE);
			        }
			        
			        event.consume();
			    }
			});
			
			pane.setOnDragEntered(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			    /* the drag-and-drop gesture entered the target */
			    /* show to the user that it is an actual gesture target */
			         if (event.getGestureSource() != pane &&
			                 event.getDragboard().hasString()) {
			             pane.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
			         }
			                
			         event.consume();
			    }
			});
			
			pane.setOnDragExited(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* mouse moved away, remove the graphical cues */
		             pane.setStyle("");

			        event.consume();
			    }
			});
			
			pane.setOnDragDropped(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* data dropped */
			        /* if there is a string data on dragboard, read it and use it */
			        Dragboard db = event.getDragboard();
			        boolean success = false;
			        if (db.hasString()) {

			        	//get jerseyNumber of player and number of pane
			        	String [] newPlayerNumber= db.getString().split(" ");
			        	int jerseyNumber= Integer.parseInt(newPlayerNumber[0])-1;
			        	Player player= currentTeam.getPlayerWithJerseyNumber(jerseyNumber);
			        	int newIndex=currentTeam.getPlayers().indexOf(player);
			        	


			        	
			        	String[] paneNumberString=pane.getId().split("p");
			        	int paneNumber=Integer.parseInt(paneNumberString[1]);
			        	
			        	
			        	
			       
			        	//switch players and update
			        	Collections.swap(currentTeam.getPlayers(), newIndex, paneNumber-1);
			        	
			        	
			        	
			        	pane.getChildren().removeAll(pane.getChildren());
			        	setUpLabels(pane,player);
			        	
			        	formationChanged();
			        	
			        	success = true;
			        }
			        /* let the source know whether the player was successfully 
			         * transferred and used */
			        event.setDropCompleted(success);
			        
			        event.consume();
			     }
			});
			
			
			
			
		}
		
		
	}
	/**
	 * Returns the type of position a pane is depending on its y coordinate. Default return is goalkeeper.
	 * @param pane Pane to be valued
	 * @return Playertype of player on pane
	 */
	
	public PlayerType returnPlayerType(Pane pane){
		if (pane.getLayoutY() > 300)
			return PlayerType.DEFENDER;
		else if (pane.getLayoutY()>100)
			return PlayerType.MIDFIELDER;
		else if (pane.getLayoutY()>50)
			return PlayerType.STRIKER;
		return PlayerType.GOALKEEPER;
	}
	
	/**setsUp and creates Labels for pane
	 * 
	 * @param pane
	 * @param player
	 */
	
	public void setUpLabels (Pane pane, Player player){
		Label nameLabel = new Label(player.getSurname());


		Label jerseyLabel = new Label(Integer.toString(player
				.getJerseyNumber() +1));
		Font myFont = Font.font(null, FontWeight.BOLD, 30);
		jerseyLabel.setFont(myFont);
		
		

		
		if (pane != null) {
			pane.getChildren().add(nameLabel);
			pane.getChildren().add(jerseyLabel);

			
		}

		
		String[] paneNumberString=pane.getId().split("p");
    	int paneNumber=Integer.parseInt(paneNumberString[1]);
    	
		Label statLabel = new Label();

    	
		if (paneNumber == 1) {
			player.setType(PlayerType.GOALKEEPER);
			statLabel.setText("Keeper:"
					+ player.getDefensiveScore());
			
			pane.getChildren().add(statLabel);

		}

		else {
			player.setType(returnPlayerType(pane));
			if (player.getType()==PlayerType.DEFENDER)
				statLabel.setText("Def: "+player.getDefensiveScore() );
			else if (player.getType()==PlayerType.MIDFIELDER)
				statLabel.setText("Stam: "+player.getStaminaScore() );
			else 
				statLabel.setText("Att: "+player.getOffensiveScore() );
			
			pane.getChildren().add(statLabel);
			
		}
		StackPane.setAlignment(nameLabel, Pos.TOP_CENTER);
		nameLabel.setTranslateY(-15.);
		StackPane.setAlignment(statLabel, Pos.BOTTOM_CENTER);
		statLabel.setTranslateY(15.);
		StackPane.setAlignment(jerseyLabel, Pos.CENTER);
		
	}
	
	/**
	 * Fills the screen with the current players' team and places labels. Also sets player type depending on position. The player type is determined from the y coordinate of the player.
	 * @param team team to be used
	 */

	public void setTeam(Team team) {
		currentTeam=team;
		
		
		for (int i=1;i<12;i++) {
			Pane pane=getPane(i);
			Player player=currentTeam.getPlayers().get(i-1);
			
			setUpLabels(pane,player);
			
		}
	}
	
	/**
	 * updates the users' formation.
	 */
	private void formationChanged(){

		Game.getInstance().getUserTeam().setPlayers(currentTeam.getPlayers());
	}

	/**
	 * returns one of the 11 player panes
	 * @param i pane to be returned
	 * @return player pane
	 */
	private StackPane getPane(int i) {
		switch (i) {
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