package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
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
	private Pane p1;
	@FXML
	private Pane p2;
	@FXML
	private Pane p3;
	@FXML
	private Pane p4;
	@FXML
	private Pane p5;
	@FXML
	private Pane p6;
	@FXML
	private Pane p7;
	@FXML
	private Pane p8;
	@FXML
	private Pane p9;
	@FXML
	private Pane p10;
	@FXML
	private Pane p11;

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
			Pane pane= getPane(i);

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
			        	String [] newPlayerNumber= db.getString().split("");
			        	int jerseyNumber= Integer.getInteger(newPlayerNumber[0]);
			        	String[] paneNumberString=pane.getId().split("p");
			        	int paneNumber=Integer.getInteger(paneNumberString[0]);
			        	
			        	//switch players and update
			        	Player newPlayer=currentTeam.getPlayerWithJerseyNumber(jerseyNumber);
			        	Player oldPlayer=currentTeam.getPlayers().get(paneNumber-1);
			        	currentTeam.getPlayers().set(paneNumber-1, newPlayer) ;
			        	currentTeam.addPlayer(oldPlayer);
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
	 * Fills the screen with the current players' team and places labels. Also sets player type depending on position. The player type is determined from the y coordinate of the player.
	 * @param team team to be used
	 */

	public void setTeam(Team team) {
		currentTeam=team;
		
		int playerNumber = 1;

		for (Player player : team.getPlayers()) {
			Label nameLabel = new Label(player.getSurname());
			nameLabel.setTranslateY(-20);

			Label jerseyLabel = new Label(Integer.toString(player
					.getJerseyNumber() + 1));
			Font myFont = Font.font(null, FontWeight.BOLD, 30);
			jerseyLabel.setFont(myFont);

			jerseyLabel.widthProperty().addListener(
					new ChangeListener<Number>() {
						@Override
						public void changed(
								ObservableValue<? extends Number> o,
								Number oldVal, Number newVal) {
							jerseyLabel.setTranslateX(jerseyLabel
									.getTranslateX() - (double) newVal / 2);

						}
					});

			nameLabel.widthProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> o,
						Number oldVal, Number newVal) {
					nameLabel.setTranslateX(jerseyLabel.getTranslateX()
							- (double) newVal / 2);

				}
			});

			Pane pane = getPane(playerNumber);

			if (pane != null) {
				pane.getChildren().add(nameLabel);
				pane.getChildren().add(jerseyLabel);

				pane.widthProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> o,
							Number oldVal, Number newVal) {
						jerseyLabel.setTranslateX((double) newVal / 2);
						nameLabel.setTranslateX((double) newVal / 2);

					}
				});

				if (playerNumber == 1) {
					player.setType(PlayerType.GOALKEEPER);
					Label statLabel = new Label("Keeper:"
							+ player.getDefensiveScore());
					statLabel.setTranslateY(40);
					statLabel.setTranslateX(15);
					pane.getChildren().add(statLabel);

				}

				else if (pane.getLayoutY() > 300) {
					player.setType(PlayerType.DEFENDER);
					Label statLabel = new Label("Def: "
							+ player.getDefensiveScore());
					statLabel.setTranslateY(40);
					statLabel.setTranslateX(15);
					pane.getChildren().add(statLabel);
				}

				else if (pane.getLayoutY() > 100) {
					player.setType(PlayerType.MIDFIELDER);

					Label statLabel = new Label("Stam: "
							+ player.getStaminaScore());
					statLabel.setTranslateY(40);
					statLabel.setTranslateX(15);
					pane.getChildren().add(statLabel);
				}

				else if (pane.getLayoutY() > 50) {
					player.setType(PlayerType.STRIKER);
					Label statLabel = new Label("Att: "
							+ player.getOffensiveScore());
					statLabel.setTranslateY(40);
					statLabel.setTranslateX(15);
					pane.getChildren().add(statLabel);
				}
			}
			playerNumber++;
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
	private Pane getPane(int i) {
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