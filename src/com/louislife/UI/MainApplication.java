package com.louislife.UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main application class, that preloads the screens and runs the game.
 * @author Matthijs
 *
 */
public class MainApplication extends Application {

	/** Schermen **/
	public static final String MAIN_MENU = "main_menu";
	public static final String MAIN_MENU_FXML = "GameMenuPlaceholder.fxml";
	public static final String MAIN_MENU_NEW_GAME = "main_menu_new_game";
	public static final String MAIN_MENU_NEW_GAME_FXML = "NewGame.fxml";
	public static final String OVERVIEW = "game_overview";
	public static final String OVERVIEW_FXML = "GameOverview.fxml";
	public static final String TEAM = "game_team";
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
		MainApplication.mainContainer.loadScreen(MainApplication.OVERVIEW, MainApplication.OVERVIEW_FXML);
		MainApplication.mainContainer.setScreen(MainApplication.MAIN_MENU); // Voor nu


		mainContainer.setScreen(MainApplication.OVERVIEW); // Voor nu
		
		primaryStage.setTitle("Louis life");
		
		Group root = new Group();
		root.getChildren().addAll(MainApplication.mainContainer);
		Scene scene= new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setFullScreen(true);
		primaryStage.show();
		
	}

}
