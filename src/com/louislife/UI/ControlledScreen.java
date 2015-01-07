package com.louislife.UI;

/**
 * Interface to facilitate switching between scenes in all controllers
 * @author Matthijs
 *
 */
public interface ControlledScreen {
	
	/**
	 * pass the screenparent (always the main instance of screencontroller) to this class so the class can switch between scenes
	 */
	public void setScreenParent(ScreensController screenParent);

}
