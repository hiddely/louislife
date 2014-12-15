package com.louislife.UI;

/**
 * @author Matthijs Rijlaarsdam
 * Class adapted from: https://blogs.oracle.com/acaicedo/entry/managing_multiple_screens_in_javafx1
 */

import java.util.HashMap;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreensController extends StackPane {

	private HashMap<String, Node> screens = new HashMap<>();

	public ScreensController() {
		// TODO Auto-generated constructor stub
	}

	public ScreensController(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Add the pair (id,screen) to Hashmap screens
	 * 
	 * @param id of the screen
	 * @param screen screen node
	 */
	public void addScreen(String id, Node screen) {
		screens.put(id, screen);
	}

	/**
	 * Loads the fxml resource, gets top Node for screen, get controller of the
	 * screen. Adds screen to hash map. Doesn't add to scene graph yet.
	 * 
	 * @param id
	 *            screen id to be used
	 * @param resource
	 *            screen to be loaded
	 * @return true if loaded, otherwise false.
	 */

	public boolean loadScreen(String id, String resource) {
		try {
			System.out.println("Attempting to load: "+resource);
			FXMLLoader myLoader = new FXMLLoader(ScreensController.class.getResource(resource));
			Parent loadScreen =  (Parent)myLoader.load();
			//ControlledScreen screenController = ((ControlledScreen) myLoader.getController());
			//screenController.setScreenParent(this);
			addScreen(id, loadScreen);

			return true;
		} catch (Exception e) {
			System.out.println("Loading FXML file error: " + e.getMessage());
			return false;

		}

	}
	/**
	 * Displays already loaded screen with fade
	 * @param screenId to be used
	 * @return true if loaded, otherwise false
	 */

	public boolean setScreen(final String screenId) {

		if (screens.get(screenId) != null) { // screen loaded
			final DoubleProperty opacity = opacityProperty();

			// Is there is more than one screen
			if (!getChildren().isEmpty()) {
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(
						1000),

				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {
						// remove displayed screen
						getChildren().remove(0);
						// add new screen
						getChildren().add(0, screens.get(screenId));
						Timeline fadeIn = new Timeline(new KeyFrame(
								Duration.ZERO, new KeyValue(opacity, 0.0)),
								new KeyFrame(new Duration(800), new KeyValue(
										opacity, 1.0)));
						fadeIn.play();
					}

				
					
				}, new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				// no one else been displayed, then just show
				setOpacity(0.0);
				getChildren().add(screens.get(screenId));
				Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(
						2500), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			return true;
		} else {
			System.out.println("screen hasn't been loaded!\n");
			return false;
		}
	}
	/**
	 * Unloads screen from hasmap and returns true if done
	 * @param name
	 * @return true if done
	 */
	 public boolean unloadScreen(String name) { 
	     if(screens.remove(name) == null) { 
	       System.out.println("Screen didn't exist"); 
	       return false; 
	     } else { 
	       return true; 
	     } 
	   } 

}
