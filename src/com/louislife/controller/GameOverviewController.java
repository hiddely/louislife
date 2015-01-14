package com.louislife.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import javafx.scene.control.TabPane;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;

public class GameOverviewController implements Initializable,
ControlledScreen {
	
	@FXML
	private TabPane navigationPane;
	
	ScreensController controller;

	/** XML Properties **/
	@FXML private Label balanceString;
	@FXML private Label dateLabel;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		updateBalance();
		updateDate();
		
	}
	
	public void updateBalance() {
		
		String balance = "TBD";
		balanceString.setText(balance);
		
	}
	
	public void updateDate() {
		
		int days = Game.getInstance().getCurrentDay();
		Date currentDate = new Date(1409522400000L + (days * 24 * 60 * 60 * 1000)); // 01/09/2014 00:00:00 + days
		
		dateLabel.setText(currentDate.toString());
		
		// Laad de load games
		//DashboardController.setNavigationPane(navigationPane);

	}
}
