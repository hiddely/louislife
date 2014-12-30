package com.louislife.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;

public class GameOverviewController implements Initializable,
ControlledScreen {
	
	@FXML
	private TabPane navigationPane;
	
	ScreensController controller;

	/** XML Properties **/
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Laad de load games
		DashboardController.setNavigationPane(navigationPane);

	}
}