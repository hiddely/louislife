package com.louislife.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.louislife.UI.ControlledScreen;
import com.louislife.UI.ScreensController;
import com.louislife.model.Game;
import com.louislife.model.Team;
import com.louislife.util.GameLoadException;
import com.louislife.util.XMLParser;

/**
 * Controller to create a new game. Input user name and select team!
 * @author hidde
 *
 */
public class NewGameController implements Initializable, ControlledScreen {

	ScreensController controller;
	
	private int selected_teamId; // Het team dat de gebruiker heeft geselecteerd om mee te spelen.

	/** XML Properties **/
	@FXML private GridPane parentGrid;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		controller= screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final int ITEMS_ROW = 6; // Aantal items in de kolom
		// Laad de mogelijke teams
		try {
			XMLParser parser = new XMLParser("example.xml");
			Game g = parser.parseGame();
			ArrayList<Team> teams = g.getLeagues().get(0).getTeams();
			
			GridPane gp = new GridPane();
			
			for (int i = 0; i < Math.round(teams.size()/ITEMS_ROW)+1; i++) { // De rijen
				for (int j = 0; j < ITEMS_ROW; j++) {
					if (teams.size() <= ITEMS_ROW*i + j)
						break;
					Team t = teams.get(ITEMS_ROW*i + j);
					Pane p = new Pane(); // Make clickable

					Label name = new Label(t.getName());
					p.getChildren().add(name);
					
					gp.add(p, j, i);
				}
			}
			
			parentGrid.add(gp, 0, 1);
			
		} catch (ParserConfigurationException | SAXException | IOException | GameLoadException e) {
			e.printStackTrace();
		}
		

	}
	
	@FXML protected void onClickStart(Event e) {
		System.out.println("Start");
	}
	
	@FXML protected void onClickBack(Event e) {
		System.out.println("Back");
	}
}
