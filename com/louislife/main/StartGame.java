package com.louislife.main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.louislife.model.Game;
import com.louislife.util.XMLParser;

public class StartGame {

	public static void main(String[] args) throws SAXException, IOException, Exception {
		
		System.out.println("Hello Louis, even testen! even testen");

		XMLParser parser = new XMLParser("example.xml");
		
		Game game = parser.parseGame();
		
		System.out.println("Games: "+XMLParser.getGames().toString());
		
		//System.out.println("Game: "+game.toString());
		
		//System.out.println("Saving: "+parser.writeGame(game));
		
	}

}
