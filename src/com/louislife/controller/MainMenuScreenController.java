package com.louislife.controller;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.util.XMLParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainMenuScreenController implements Initializable,
		ControlledScreen {
	
	ScreensController controller;
	
	/** XML Properties **/
	@FXML private GridPane menuPane;
	@FXML private ListView<String> loadGameList;
	
	public MainMenuScreenController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Laad de load games
		ArrayList<String> games = XMLParser.getGames();
		System.out.println("GamesFromINIT: "+games.toString());
		
		// En zet ze dynamisch in de UI
		ObservableList<String> items =FXCollections.observableArrayList (games);
		loadGameList.setItems(items);

	}
	
	@FXML protected void actionButtonNew(ActionEvent event) {
        System.out.println("Pressed New");
        
        controller.setScreen(MainApplication.MAIN_MENU_NEW_GAME);
    }
	
	@FXML protected void actionButtonLoad(ActionEvent event) {
        System.out.println("Pressed load: "+loadGameList.getSelectionModel().getSelectedItem());
        
        //loadGameList.getSelectionModel().getSelectedItem()
        try {
			XMLParser parser = new XMLParser(loadGameList.getSelectionModel().getSelectedItem() + ".xml");
			parser.parseGame();
			
			// The instance is now set
			System.out.println("Game loaded: "+Game.getInstance().toString());
			
			MainApplication.mainContainer.loadScreen(MainApplication.OVERVIEW, MainApplication.OVERVIEW_FXML);
			controller.setScreen(MainApplication.OVERVIEW);
		} catch (Exception e) {
			e.printStackTrace();
			
			// TODO: Show error message for parsing error
		}
    }

}
