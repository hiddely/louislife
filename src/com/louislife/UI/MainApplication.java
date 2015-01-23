package com.louislife.UI;

import com.louislife.controller.GamePlayListener;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
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
	
	public MainApplication() {
	}
	
	public static void main(String[] args){

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Load all screens here
		MainApplication.mainContainer.loadScreen(MainApplication.MAIN_MENU, MainApplication.MAIN_MENU_FXML);
		MainApplication.mainContainer.loadScreen(MainApplication.MAIN_MENU_NEW_GAME, MainApplication.MAIN_MENU_NEW_GAME_FXML);




		MainApplication.mainContainer.setScreen(MainApplication.MAIN_MENU); // Voor nu		
		
		primaryStage.setTitle("Louis life");
		
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
