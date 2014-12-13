package com.louislife.UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

	public static final String MAIN_MENU = "main_menu";
	public static final String MAIN_MENU_FXML = "GameMenuPlaceholder.fxml";
	ScreensController mainContainer = new ScreensController(); 
	
	public MainApplication() {
	}
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//load all screens here
		mainContainer.loadScreen(MainApplication.MAIN_MENU, MainApplication.MAIN_MENU_FXML);
		mainContainer.setScreen(MainApplication.MAIN_MENU);
		
		
		Group root= new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene= new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		

		
	}

}
