package com.louislife.UI;

import com.louislife.controller.GamePlayListener;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The main application class, that preloads the screens and runs the game.
 * @author Matthijs
 *
 */
public class MainApplication extends Application {

	private static List<GamePlayListener> listeners = new ArrayList<GamePlayListener>();

	/** Schermen **/
	public static final String MAIN_MENU = "main_menu";
	public static final String MAIN_MENU_FXML = "GameMenuPlaceholder.fxml";
	public static final String MAIN_MENU_NEW_GAME = "main_menu_new_game";
	public static final String MAIN_MENU_NEW_GAME_FXML = "NewGame.fxml";
	public static final String OVERVIEW = "game_overview";
	public static final String OVERVIEW_FXML = "GameOverview.fxml";
	public static final String MATCH = "matches";
	public static final String MATCH_FXML = "Matches.fxml";
	public static final String TEAM = "team_view";
	public static final String TEAM_FXML = "Team.fxml";
	
	public static ScreensController mainContainer = new ScreensController(); 
	public  static Stage primaryStage = new Stage();
	
	public MainApplication() {
	}
	
	public static void main(String[] args){

		launch(args);

	}
	//Makes and shows popup.
	public static void makePopup(Collection<Node> content){
		final Popup popup = new Popup();
		VBox pane= new VBox();
		pane.getChildren().addAll(content);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10), null)));
        pane.setAlignment(Pos.CENTER);
		popup.setX(500);
		popup.setY(500);
        popup.centerOnScreen();
        popup.getContent().add(pane);
        
        popup.show(MainApplication.primaryStage);
        popup.autoHideProperty().set(true);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MainApplication.primaryStage=primaryStage;
		// Load all screens here
		MainApplication.mainContainer.loadScreen(MainApplication.MAIN_MENU, MainApplication.MAIN_MENU_FXML);
		MainApplication.mainContainer.loadScreen(MainApplication.MAIN_MENU_NEW_GAME, MainApplication.MAIN_MENU_NEW_GAME_FXML);

		MainApplication.mainContainer.setScreen(MainApplication.MAIN_MENU); // Voor nu		
		
		primaryStage.setTitle("Louis Life");
		
		Group root = new Group();
		root.getChildren().addAll(MainApplication.mainContainer);
		Scene scene= new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setFullScreen(true);
		primaryStage.show();

		String bip = "sounds/louisquotes.mp3";
		Media hit = new Media(new File(bip).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		
	}

	public static void addListener(GamePlayListener g) {
		listeners.add(g);
	}

	public static void sendNextGame() {
		for (GamePlayListener hl : listeners)
			hl.onGamePlayed();
	}

}
