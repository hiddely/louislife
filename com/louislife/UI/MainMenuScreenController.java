package com.louislife.UI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class MainMenuScreenController implements Initializable,
		ControlledScreen {
	
	ScreensController controller;

	public MainMenuScreenController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
