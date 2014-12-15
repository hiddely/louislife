package com.louislife.UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.GridPane;

import com.louislife.util.XMLParser;

public class MainMenuScreenController implements Initializable,
		ControlledScreen {
	
	ScreensController controller;
	
	/** XML Properties **/
	@FXML private GridPane menuPane;
	
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
		
		ListView<String> list = new ListView<String>();
		ObservableList<String> items =FXCollections.observableArrayList (games);
		list.setItems(items);
                      
		menuPane.add(list, 0, 0);
	}
	
	@FXML protected void actionButtonContinue(ActionEvent event) {
        System.out.print("Pressed Continue");
    }
	
	@FXML protected void actionButtonLoad(ActionEvent event) {
        System.out.print("Pressed load");
    }

}
