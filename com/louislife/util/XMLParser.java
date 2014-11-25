package com.louislife.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.louislife.model.Game;
import com.louislife.model.League;
import com.louislife.model.Player;
import com.louislife.model.PlayerStatus;
import com.louislife.model.PlayerType;
import com.louislife.model.Team;

public class XMLParser {
	
	private DocumentBuilder builder;
	private String filename;
	
	public XMLParser(String filename) throws ParserConfigurationException {
		this.filename = filename;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
		builder = factory.newDocumentBuilder(); // Initialize builder
		
	}
	
	public Game parseGame() throws SAXException, IOException, GameLoadException {		
		Document document = builder.parse(ClassLoader.getSystemResourceAsStream(filename));
		
		Game game = null;
		
		// Get game data
		NodeList gameNodeData = document.getElementsByTagName("game");
		if (gameNodeData != null && gameNodeData.getLength() > 0) { // Check for game settings data
			Node gameNode = document.getElementsByTagName("game").item(0); // There is only one
			
			int id = Integer.parseInt(getAttribute(gameNode.getAttributes(), "id"));
			String name = getAttribute(gameNode.getAttributes(), "name");
			int currentDay = Integer.parseInt(getChildValue(gameNode, "currentday"));
			
			game = new Game(id, name, currentDay);
		} else {
			throw new GameLoadException("XML: No game data element found.");
		}
		
		// Get leagues
		NodeList lData = document.getElementsByTagName("league");
		if (lData != null && lData.getLength() > 0) { // Check for game settings data
			for (int i = 0; i < lData.getLength(); i++) {
				Node league = lData.item(i);
				
				int id = Integer.parseInt(getAttribute(league.getAttributes(), "id"));
				String name = getAttribute(league.getAttributes(), "name");
				String country = getAttribute(league.getAttributes(), "country");
				
				League leagueObject = new League(id, name, country, null);
				
				// Get teams
				for (int a = 0; a < league.getChildNodes().getLength(); a++) {
					Node teamNode = league.getChildNodes().item(a);
					
					int tid = Integer.parseInt(getAttribute(teamNode.getAttributes(), "id"));
					String tname = getAttribute(teamNode.getAttributes(), "name");
					
					Team team = new Team(tid, tname, null);
					
					// Get players
					NodeList playerData = teamNode.getChildNodes();
					for (int b = 0; b < playerData.getLength(); b++) {
						// Single player
						Node player = playerData.item(b);
						
						int pid = Integer.parseInt(getAttribute(player.getAttributes(), "id"));
						String pname = getChildValue(player, "name");
						String surname = getChildValue(player, "surname");
						byte number = Byte.parseByte(getChildValue(player, "number"));
						
						int player_type = Integer.parseInt(getChildValue(player, "type"));
						short rating_offensive = Short.parseShort(getChildValue(player, "offensive_rating"));
						short rating_def = Short.parseShort(getChildValue(player, "defensive_rating"));
						short rating_stamina = Short.parseShort(getChildValue(player, "stamina_rating"));
						
						int player_status = Integer.parseInt(getChildValue(player, "status"));
						int team_id = Integer.parseInt(getChildValue(player, "team"));
						int price = Integer.parseInt(getChildValue(player, "price"));
						
						PlayerType playerType = PlayerType.values()[player_type]; // Convert to enum counterparts
						PlayerStatus playerStatus = PlayerStatus.values()[player_status];
						
						team.addPlayer(new Player(pid, pname, surname, number, playerType, rating_offensive, rating_def, rating_stamina, team_id, playerStatus, price));
					}
					
					leagueObject.addTeam(team);
				}
				
				game.addLeague(leagueObject);
				
			}
		}
				
		return game;
	}
	
	public String getChildValue(Node p, String name) {
		for (int i = 0; i < p.getChildNodes().getLength(); i++) {
			if (p.getChildNodes().item(i).getNodeName().equals(name)) {
				return p.getChildNodes().item(i).getNodeValue();
			}
		}
		return null;
	}
	
	public String getAttribute(NamedNodeMap attrs, String name) {
		return attrs.getNamedItem(name).getNodeValue();
	}
	
	
}
