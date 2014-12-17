package com.louislife.UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

	/** Schermen **/
	public static final String MAIN_MENU = "main_menu";
	public static final String MAIN_MENU_FXML = "GameMenuPlaceholder.fxml";
	public static final String OVERVIEW = "game_overview";
	public static final String OVERVIEW_FXML = "GameOverview.fxml";

	
	ScreensController mainContainer = new ScreensController(); 
	
	public MainApplication() {
	}
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Load all screens here
		mainContainer.loadScreen(MainApplication.MAIN_MENU, MainApplication.MAIN_MENU_FXML);
		mainContainer.loadScreen(MainApplication.OVERVIEW, MainApplication.OVERVIEW_FXML);
		mainContainer.setScreen(MainApplication.OVERVIEW); // Voor nu
		
		primaryStage.setTitle("Louis life");
		
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene= new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setFullScreen(true);
		primaryStage.show();
		
	}

}
