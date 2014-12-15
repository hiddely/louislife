package com.louislife.UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.GridPane;

import com.louislife.model.Game;
import com.louislife.util.XMLParser;

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

		ArrayList<String> games = XMLParser.getGames();
		
		System.out.println("GamesFromINIT: "+games.toString());
		
		loadGameList = new ListView<String>();
		ObservableList<String> items =FXCollections.observableArrayList (games);
		loadGameList.setItems(items);
                      
		menuPane.add(loadGameList, 0, 0);
	}
	
	@FXML protected void actionButtonNew(ActionEvent event) {
        System.out.println("Pressed New");
    }
	
	@FXML protected void actionButtonLoad(ActionEvent event) {
        System.out.println("Pressed load: "+loadGameList.getSelectionModel().getSelectedItem());
        
        //loadGameList.getSelectionModel().getSelectedItem()
        try {
			XMLParser parser = new XMLParser(loadGameList.getSelectionModel().getSelectedItem() + ".xml");
			parser.parseGame();
			
			// The instance is now set
			System.out.println("Game loaded: "+Game.getInstance().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
