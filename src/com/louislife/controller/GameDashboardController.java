package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;

public class GameDashboardController implements Initializable, ControlledScreen {

	ScreensController controller;

	/** XML Properties **/
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Laad de load games

	}
	
}
