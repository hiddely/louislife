package com.louislife.UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TeamManagementScreen extends Application{

	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage tmStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/com/louislife/UI/TeamManagement.fxml"));
		Scene scene = new Scene(root);
		tmStage.setScene(scene);
		tmStage.setTitle("Louis Team management");
		tmStage.show();
		
	}

}
