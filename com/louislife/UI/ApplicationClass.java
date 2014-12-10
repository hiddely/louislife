package com.louislife.UI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.scene.*;

public class ApplicationClass extends Application {

	public ApplicationClass() {
		// TODO Auto-generated constructor stub
	}
	
	 public static void main(String[] args) {
	        launch(args);
	    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	    
    
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("LouisLife");
       
        //scene created after fullscreensize is set.
       
        Scene scene = new Scene(root, primaryStage.getHeight(), primaryStage.getWidth());

        primaryStage.setScene(scene);
        primaryStage.show();

	}

}
