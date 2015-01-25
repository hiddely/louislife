package com.louislife.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.louislife.model.Match;
import com.louislife.model.Player;
import com.louislife.model.Team;
import com.louislife.util.XMLParser;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.MainApplication;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;

import javafx.stage.Popup;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Controller of the Dashboard, the main navigation screen within gameoverview
 * @author Matthijs
 *
 */

public class DashboardController extends ExplainableController implements Initializable, ControlledScreen, GamePlayListener {

	private static TabPane navigationPane;
	ScreensController controller;

	/** XML Properties **/
	@FXML private Label teamLabel;
	@FXML private Label nextLabel;
	@FXML private Label quoteLabel;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	/**
	 * method is called as intance is loaded
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainApplication.addListener(this);

		updateDashboard();
		if(!super.getExplained())
			explain();
		
	}

	public void updateDashboard() {
		// Laad de load games
		teamLabel.setText(Game.getInstance().getUserTeam().getName());
		
		// Get random Louis Quote
		quoteLabel.setText(Game.getQuotes()[new Random().nextInt(Game.getQuotes().length)]);

		// Get next match for team
		int day = Game.getInstance().getCurrentDay();
		for(int i = 0; i < Game.getInstance().getMatches().size(); i++){
			if(Game.getInstance().getMatches().get(i).getDay() >= day && Game.getInstance().getMatches().get(i).getDay() < day+7){
				Match m = Game.getInstance().getMatches().get(i);
				if (m.getTH().equals(Game.getInstance().getUserTeam())) {
					nextLabel.setText("Home: " + m.getTA().getName());
				} else if (m.getTA().equals(Game.getInstance().getUserTeam())) {
					nextLabel.setText("Away: " + m.getTH().getName());
				}
			}
		}
	}

	/**
	 * Method that sets the navigationPane of the parent gameoverview, so we can access and manipulate it.
	 * @param tabs tabPane to set
	 */
	public static void setNavigationPane( TabPane tabs){
		navigationPane=tabs;
	}

	@FXML protected void onClickNextGame(Event event) {

		System.out.println("Next game");


		// To end game: check if day is more than rounds
		int day = Game.getInstance().getCurrentDay();
		if (day > (Game.getInstance().getLeagues().get(0).getTeams().size()-1)*2*7) {
			// End game pop up

		}

		Team user = Game.getInstance().getUserTeam();
		Match userMatch = null;
		for(int i = 0; i < Game.getInstance().getMatches().size(); i++){
			if(Game.getInstance().getMatches().get(i).getDay() >= day && Game.getInstance().getMatches().get(i).getDay() < day+7){
				Game.getInstance().getMatches().get(i).play(System.currentTimeMillis());

				// Award prize money
				Game.getInstance().getMatches().get(i).calculateHomeCredit();
				Game.getInstance().getMatches().get(i).calculateAwayCredit();

				System.out.println("Played: " + Game.getInstance().getMatches().get(i).toString());

				Match m = Game.getInstance().getMatches().get(i);
				if (user.equals(m.getTH()) || user.equals(m.getTA()))
					userMatch = m;
			}
		}
		Game.getInstance().nextWeek();

		if (new Random().nextInt(1) == 0) {
			// Random transfer request
			Team reqteam = Game.getInstance().getUserTeam();
			while (reqteam == Game.getInstance().getUserTeam()) {
				reqteam = Game.getInstance().getLeagues().get(0).getTeams().get(new Random().nextInt(Game.getInstance().getLeagues().get(0).getTeams().size()));
			}
			final Team fteam = reqteam;
			// Reqteam is now a random team and not the users team
			// Select random player to buy
			Player p = user.getPlayers().get(new Random().nextInt(user.getPlayers().size()));

			Label la= new Label();
			la.setText("Louis, a transfer request has been made!");
			la.setFont(Font.font("Avenir medium", FontWeight.BOLD, 20));
			la.setTextFill(Color.WHITE);
			int price = p.calculatePrice();
			Label label2= new Label();
			label2.setText("It's about "+p.getFirstname()+" "+p.getSurname()+",\n they would like to buy him for € "+p.calculatePrice());
			label2.setFont(Font.font("Avenir medium", FontWeight.BOLD, 16));
			label2.setTextFill(Color.WHITE);

			HBox buttonsPane = new HBox();
			Button accept = new Button("Accept");
		
			Button decline = new Button("Decline");
			buttonsPane.getChildren().add(accept);
			buttonsPane.getChildren().add(decline);

			ArrayList<Node> list= new ArrayList<Node>();
			list.add(la);
			list.add(label2);
			list.add(buttonsPane);

			final Popup popup = new Popup();
			VBox pane= new VBox();
			pane.setPadding(new Insets(10, 20, 10, 20));
			pane.getChildren().addAll(list);
	        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10), null)));
	        pane.setAlignment(Pos.CENTER);
			popup.setX(750);
			popup.setY(350);
	        popup.centerOnScreen();
	        popup.getContent().add(pane);
	        
	    	accept.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Buy player
					Game.getInstance().getUserTeam().setBalance(Game.getInstance().getUserTeam().getBalance()+price);
					fteam.setBalance(fteam.getBalance() + price);
					fteam.addPlayer(p);
					Game.getInstance().getUserTeam().getPlayers().remove(p);
					popup.hide();
					MainApplication.sendNextGame();
				}
			});
	    	
	    	decline.setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					popup.hide();
					
				}
	    		
	    		
	    	});
	        
	        popup.show(MainApplication.primaryStage);		}
		
		MainApplication.sendNextGame();

		if (userMatch != null) { // Just safety check
			Match m = userMatch;
			String title = "";
			String score = userMatch.getScore_home()+" - "+userMatch.getScore_away();
			String desc = userMatch.getTH().getName() + " vs. " + userMatch.getTA().getName();
			String image = "louislost.jpeg";
			if (user.equals(userMatch.getWinningTeam())) {
				title = "Louis, you have won!";
			} else if (user.equals(userMatch.getLosingTeam())) {
				title = "Louis, you have lost.";
			} else {
				title = "Louis, it was a tie.";
			}

			Label la= new Label();
			la.setText(title);
			la.setFont(Font.font("Avenir medium", FontWeight.BOLD, 20));
			la.setTextFill(Color.WHITE);
			Label label2= new Label();
			label2.setText(score);
			label2.setFont(Font.font("Avenir medium", FontWeight.BOLD, 16));
			label2.setTextFill(Color.WHITE);
			Label ldesc= new Label();
			ldesc.setText(desc);
			ldesc.setFont(Font.font("Avenir medium", FontWeight.BOLD, 16));
			ldesc.setTextFill(Color.WHITE);
			ArrayList<Node> list= new ArrayList<Node>();
			list.add(la);
			list.add(label2);
			list.add(ldesc);

			Label laab= new Label();
			laab.setText("Home:");
			laab.setFont(Font.font("Avenir medium", FontWeight.BOLD, 18));
			laab.setTextFill(Color.WHITE);
			list.add(laab);

			for (int i = 0; i < m.getEvents_home().size(); i++) {
				com.louislife.model.Event e = m.getEvents_home().get(i);
				String label = e.getMinute() + ": " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getFirstname() + " " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getSurname() + " ";
				String icon = "";
				switch (e.getType()) {
					case GOAL:
						//Player p = Game.getInstance().getLeagues().get(0).m.getEvents_home().get(i).getPlayer();
						label += "scored a goal";
						icon = "icon_football.png";
						break;
					case YELLOWCARD:
						label += "received a yellow card";
						icon = "icon_yellowcard.png";
						break;
					case REDCARD:
						label += "received a red card";
						icon = "icon_redcard.png";
						break;
					case INJURY:
						label += "was injured";
						icon = "";
						break;
				}
				Label l = new Label(label);
				l.setTextFill(Color.WHITE);
				l.setFont(new Font("Avenir Medium", 16.0));
				l.setLayoutX(40.0);
				Image ii = new Image(new File("images/"+icon).toURI().toString(), 20, 20, false, false);
				final ImageView iconview = new ImageView();
				iconview.setLayoutX(5.0);
				iconview.setLayoutY(3.0);
				iconview.setImage(ii);
				Pane p = new Pane();
				p.getChildren().add(l);
				p.getChildren().add(iconview);
				p.setLayoutY(i * 30.0);
				list.add(p);
			}

			Label laa= new Label();
			laa.setText("Away:");
			laa.setFont(Font.font("Avenir medium", FontWeight.BOLD, 18));
			laa.setTextFill(Color.WHITE);
			list.add(laa);

			for (int i = 0; i < m.getEvents_away().size(); i++) {
				com.louislife.model.Event e = m.getEvents_away().get(i);
				String label = e.getMinute() + ": " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getFirstname() + " " + Game.getInstance().getLeagues().get(0).findPlayer(e.getPlayer()).getSurname() + " ";
				String icon = "";
				switch (e.getType()) {
					case GOAL:
						//Player p = Game.getInstance().getLeagues().get(0).m.getEvents_home().get(i).getPlayer();
						label += "scored a goal";
						icon = "icon_football.png";
						break;
					case YELLOWCARD:
						label += "received a yellow card";
						icon = "icon_yellowcard.png";
						break;
					case REDCARD:
						label += "received a red card";
						icon = "icon_redcard.png";
						break;
					case INJURY:
						label += "was injured";
						icon = "";
						break;
				}
				Label l = new Label(label);
				l.setTextFill(Color.WHITE);
				l.setFont(new Font("Avenir Medium", 16.0));
				l.setLayoutX(40.0);
				Image ii = new Image(new File("images/"+icon).toURI().toString(), 20, 20, false, false);
				final ImageView iconview = new ImageView();
				iconview.setLayoutX(5.0);
				iconview.setLayoutY(3.0);
				iconview.setImage(ii);
				Pane p = new Pane();
				p.getChildren().add(l);
				p.getChildren().add(iconview);
				p.setLayoutY(i * 30.0);
				p.setLayoutX(300);
				list.add(p);
			}
			MainApplication.makePopup(list, 300, 330);

		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				// Save game on separate thread
				XMLParser parser = null;
				try {
					parser = new XMLParser(Game.getInstance().getXmlName());
				} catch (ParserConfigurationException e1) {
					e1.printStackTrace();
				}
				parser.writeGame(Game.getInstance());
			}
		}).start();
	}
	
	@FXML protected void onClickTeam(Event e) {
		navigationPane.getSelectionModel().select(1);

	}
	
	@FXML protected void onClickLeaderboard(Event e) {
		navigationPane.getSelectionModel().select(2);
	}
	
	@FXML protected void onClickMarket(Event e) {
		navigationPane.getSelectionModel().select(3);
	}

	@FXML protected void onClickMatches(Event e) {
		navigationPane.getSelectionModel().select(4);
	}

	@FXML protected void onClickMainMenu(Event e){
		MainApplication.mainContainer.setScreen(MainApplication.MAIN_MENU);
	}

	@Override
	public void onGamePlayed() {
		updateDashboard();
	}

	@Override
	public void explain() {
		Label label= new Label();
        label.setText("Welcome Louis!");
        Font myFont = Font.font(null, FontWeight.BOLD, 20);
        label.setFont(myFont);
        label.setTextFill(Color.WHITE);
        Label label2= new Label();
        label2.setText("Manage your team, and become this seasons champion!");
        Font myFont2 = Font.font(null, FontWeight.BOLD, 12);
        label2.setFont(myFont2);
        label2.setTextFill(Color.WHITE);
        ArrayList<Node> list= new ArrayList<Node>();
        list.add(label);
        list.add(label2);
        MainApplication.makePopup(list);
        super.setExplained(true);

	}
}
