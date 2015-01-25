package com.louislife.controller;

import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.louislife.UI.MainApplication;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import javafx.util.Duration;

public class GameOverviewController implements Initializable,
ControlledScreen, GamePlayListener {
	
	@FXML
	private TabPane navigationPane;
	
	ScreensController controller;

	/** XML Properties **/
	@FXML private Label balanceString;
	@FXML private Label dateLabel;
	@FXML private Label rankLabel;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		DashboardController.setNavigationPane(navigationPane);

		MainApplication.addListener(this);

		this.onGamePlayed(); // Invoke for first time
	}
	
	public void updateBalance() {
		
		String balance = Integer.toString(Game.getInstance().getUserTeam().getBalance());
		balanceString.setText(balance);
	}
	
	public void updateDate() {
		
		int days = Game.getInstance().getCurrentDay();
		//Date currentDate = new Date(1409522400000L + (days * 24 * 60 * 60 * 1000)); // 01/09/2014 00:00:00 + days
		Calendar c = new GregorianCalendar(2014, 9, 1);
		c.add(Calendar.DAY_OF_MONTH, days);
		Date currentDate = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, ''yy");

		dateLabel.setText(sdf.format(currentDate));

	}

	public void updateRank() {
		rankLabel.setText(Game.getInstance().getRank(Game.getInstance().getUserTeam(), 0)+"th");
	}

	@Override
	public void onGamePlayed() {
		updateBalance();
		updateDate();
		updateRank();
	}
}
