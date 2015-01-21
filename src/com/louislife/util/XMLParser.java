package com.louislife.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.louislife.model.Event;
import com.louislife.model.EventType;
import com.louislife.model.Game;
import com.louislife.model.League;
import com.louislife.model.Match;
import com.louislife.model.Player;
import com.louislife.model.PlayerStatus;
import com.louislife.model.PlayerType;
import com.louislife.model.Team;
import com.louislife.model.Transfer;

public class XMLParser {
	
	private DocumentBuilder builder;
	private String filename;
	
	private static final String SAVE_FOLDER = "savegames/";
	
	public XMLParser(String filename) throws ParserConfigurationException {
		this.filename = filename;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
		factory.setIgnoringElementContentWhitespace(true);
		builder = factory.newDocumentBuilder(); // Initialize builder
		
	}
	
	/**
	 * Parses game file with filename and initializes the singleton instance of Game
	 * @return the Game object, also accessable by Game.getInstance()
	 * @throws SAXException
	 * @throws IOException
	 * @throws GameLoadException 
	 */
	public Game parseGame() throws SAXException, IOException, GameLoadException {		
		String loadFile = SAVE_FOLDER + filename;
		System.out.println("LOAD: "+loadFile);
		InputStream input = new FileInputStream(loadFile);
		Document document = builder.parse(input);
		document.normalize();
		
		Game game = null;
		
	    Element gameNode = document.getDocumentElement();

		if (gameNode != null) { // Check for game settings data
			
			int id = Integer.parseInt(getAttribute(gameNode.getAttributes(), "id"));
			String name = getAttribute(gameNode.getAttributes(), "name");
			int currentDay = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentday"));
			int currentTeam = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentteam"));
			
			game = new Game(id, name, currentDay, currentTeam);
			
			
			// Get match data
			
			NodeList matchData = gameNode.getElementsByTagName("match");
			for (int i = 0; i < matchData.getLength(); i++){
				Node matchNode = matchData.item(i);
				if (matchNode.getNodeType() == Node.ELEMENT_NODE) {
					Element match = (Element)matchNode;

					int mid = Integer.parseInt(getAttribute(match.getAttributes(), "id"));
					int day = Integer.parseInt(getAttribute(match.getAttributes(), "day"));
					
					Match matchObj = new Match(mid, day);
					
					// Get home team data
					NodeList homeData = match.getElementsByTagName("team_home");
					int idhome = Integer.parseInt(getAttribute(homeData.item(0).getAttributes(), "id"));
					
					matchObj.setTeam_home(idhome);
					
					NodeList home_events = ((Element)homeData.item(0)).getElementsByTagName("event");
					for (int a = 0; a < home_events.getLength(); a++) {
						Element event = (Element)home_events.item(a);
						int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));
						int type = Integer.parseInt(getChildValue(event, "type"));
						int minute = Integer.parseInt(getChildValue(event, "minute"));
						
						Event e = new Event(player, EventType.values()[type], minute);
						
						matchObj.addEventHome(e);
					}
					
					
					NodeList awayData = match.getElementsByTagName("team_away");
					int idaway = Integer.parseInt(getAttribute(awayData.item(0).getAttributes(), "id"));

					matchObj.setTeam_away(idaway);
						
					NodeList away_events = ((Element)awayData.item(0)).getElementsByTagName("event");
					for (int a = 0; a < away_events.getLength(); a++) {
						Element event = (Element)away_events.item(a);
						int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));
						int type = Integer.parseInt(getChildValue(event, "type"));
						int minute = Integer.parseInt(getChildValue(event, "minute"));
						
						Event e = new Event(player, EventType.values()[type], minute);
						
						matchObj.addEventAway(e);
					}
					
					game.addMatch(matchObj);
					
				}
			}
			
			// Get transfer data			
			NodeList transferData = gameNode.getElementsByTagName("transfer");
			for (int i = 0; i < transferData.getLength(); i++) {
				Node transferNode = transferData.item(i);
				if (transferNode.getNodeType() == Node.ELEMENT_NODE) {
					Element transfer = (Element)transferNode;
					
					int tid = Integer.parseInt(getAttribute(transfer.getAttributes(), "id"));
	
					int from = Integer.parseInt(getChildValue(transfer, "from"));
					int to = Integer.parseInt(getChildValue(transfer, "to"));
					int player = Integer.parseInt(getChildValue(transfer, "player"));
					int price = Integer.parseInt(getChildValue(transfer, "price"));
					int day = Integer.parseInt(getChildValue(transfer, "day"));
					
					game.addTransfer(new Transfer(tid, from, to, player, price, day));
				}
			}
			
			// Get leagues
			NodeList lData = document.getElementsByTagName("league");
			if (lData != null && lData.getLength() > 0) { // Check for game settings data
				for (int i = 0; i < lData.getLength(); i++) {
					Node league = lData.item(i);
					
					int lid = Integer.parseInt(getAttribute(league.getAttributes(), "id"));
					String lname = getAttribute(league.getAttributes(), "name");
					String country = getAttribute(league.getAttributes(), "country");
					
					League leagueObject = new League(lid, lname, country);
					
					// Get teams
					for (int a = 0; a < league.getChildNodes().getLength(); a++) {
						if (league.getChildNodes().item(a).getNodeType() != Node.ELEMENT_NODE)
							continue;
						
						Element teamNode = (Element)league.getChildNodes().item(a);
						
						int tid = Integer.parseInt(getAttribute(teamNode.getAttributes(), "id"));
						String tname = getAttribute(teamNode.getAttributes(), "name");
						
						Team team = new Team(tid, tname);
						
						// Get players
						NodeList playerData = teamNode.getElementsByTagName("player");
						for (int b = 0; b < playerData.getLength(); b++) {
							// Single player
							Element player = (Element)playerData.item(b);
							
							int pid = Integer.parseInt(getAttribute(player.getAttributes(), "id"));
							String pname = getChildValue(player, "name");
							String surname = getChildValue(player, "surname");
							byte number = Byte.parseByte(getChildValue(player, "number"));
							
							int player_type = Integer.parseInt(getChildValue(player, "type"));
							//int player_state = Integer.parseInt(getChildValue(player, "status"));
							int player_state = 0;
							byte rating_offensive = Byte.parseByte(getChildValue(player, "offensiveRating"));
							byte rating_def = Byte.parseByte(getChildValue(player, "defensiveRating"));
							byte stamina = Byte.parseByte(getChildValue(player, "stamina"));
							
							int team_id = tid;
							int price = Integer.parseInt(getChildValue(player, "price"));
							
							PlayerType playerType = PlayerType.values()[player_type]; // Convert to enum counterparts
							PlayerStatus pstatus = PlayerStatus.values()[player_state];
							
							team.addPlayer(new Player(pid, pname, surname, number, playerType, pstatus, rating_offensive, rating_def, stamina, team_id, price));
						}
						
						leagueObject.addTeam(team);
					}
					
					game.addLeague(leagueObject);
					
				}
			}
		} else {
			throw new GameLoadException("XML: No game data element found.");
		}
				
		return game;
	}
	
	public boolean writeGame(Game g) {
		try {
			 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("game");
			doc.appendChild(rootElement);
			
			rootElement.setAttribute("id", g.getId()+"");
			rootElement.setAttribute("name", g.getName()+"");
			rootElement.setAttribute("currentday", g.getCurrentDay()+"");
			rootElement.setAttribute("currentteam", g.getUserTeam().getId()+"");
			
			for (League l : g.getLeagues()) {
				Element eLeague = doc.createElement("league");
				eLeague.setAttribute("id", l.getId()+"");
				eLeague.setAttribute("name", l.getName());
				eLeague.setAttribute("country", l.getCountry());
				rootElement.appendChild(eLeague);
				
				for (Team t : l.getTeams()) {
					Element eTeam = doc.createElement("team");
					eTeam.setAttribute("id", t.getId()+"");
					eTeam.setAttribute("name", t.getName());
					
					for (Player p : t.getPlayers()) {
						Element ePlayer = doc.createElement("player");
						ePlayer.setAttribute("id", p.getId()+"");
						
						Element eName = doc.createElement("name");
						eName.appendChild(doc.createTextNode(p.getFirstname()));
						ePlayer.appendChild(eName);
						
						Element eLastName = doc.createElement("surname");
						eLastName.appendChild(doc.createTextNode(p.getSurname()));
						ePlayer.appendChild(eLastName);
						
						Element eNumber = doc.createElement("number");
						eNumber.appendChild(doc.createTextNode(p.getJerseyNumber()+""));
						ePlayer.appendChild(eNumber);
						
						Element eType = doc.createElement("type");
						eType.appendChild(doc.createTextNode(p.getType().ordinal()+""));
						ePlayer.appendChild(eType);
						
						Element eStatus = doc.createElement("status");
						eStatus.appendChild(doc.createTextNode(p.getStatus().ordinal()+""));
						ePlayer.appendChild(eStatus);
						
						Element offensive = doc.createElement("offensiveRating");
						offensive.appendChild(doc.createTextNode(p.getOffensiveScore()+""));
						ePlayer.appendChild(offensive);
						
						Element defensive = doc.createElement("defensiveRating");
						defensive.appendChild(doc.createTextNode(p.getDefensiveScore()+""));
						ePlayer.appendChild(defensive);
						
						Element stamina = doc.createElement("stamina");
						stamina.appendChild(doc.createTextNode(p.getStaminaScore()+""));
						ePlayer.appendChild(stamina);
						
						Element price = doc.createElement("price");
						price.appendChild(doc.createTextNode(p.getPrice()+""));
						ePlayer.appendChild(price);
						
						eTeam.appendChild(ePlayer);
					}
					
					eLeague.appendChild(eTeam);
				}
			}
			
			for (Match m : g.getMatches()) {
				Element eM = doc.createElement("match");
				eM.setAttribute("id", m.getId()+"");
				eM.setAttribute("day", m.getDay()+"");
				Element tH = doc.createElement("team_home");
				tH.setAttribute("id", m.getTeam_home()+"");
				for (Event e : m.getEvents_home()) {
					Element eE = doc.createElement("event");
					eE.setAttribute("player", e.getPlayer()+"");
					Element eEType = doc.createElement("type");
					eEType.appendChild(doc.createTextNode(e.getType()+""));
					Element eEMinute = doc.createElement("minute");
					eEMinute.appendChild(doc.createTextNode(e.getMinute()+""));
					eE.appendChild(eEType);
					eE.appendChild(eEMinute);
					
					tH.appendChild(eE);
				}
				
				Element tA = doc.createElement("team_away");
				tA.setAttribute("id", m.getTeam_away()+"");
				for (Event e : m.getEvents_away()) {
					Element eE = doc.createElement("event");
					eE.setAttribute("player", e.getPlayer()+"");
					Element eEType = doc.createElement("type");
					eEType.appendChild(doc.createTextNode(e.getType()+""));
					Element eEMinute = doc.createElement("minute");
					eEMinute.appendChild(doc.createTextNode(e.getMinute()+""));
					eE.appendChild(eEType);
					eE.appendChild(eEMinute);
					
					tA.appendChild(eE);
				}
				
				eM.appendChild(tH);
				eM.appendChild(tA);
				
				rootElement.appendChild(eM);
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(SAVE_FOLDER + filename));
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			System.out.println("File saved!");
	 
			return true;
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		return false;
	}
	
	/**
	 * Maakt een nieuw game aan met name
	 * @param name
	 * @return true on success, false on failure
	 */
	public boolean createGame(String name) {
		File f = new File(name);
		if (f.isDirectory()) {
			return false;
		}
		if (!f.exists()) {
			File src = new File(SAVE_FOLDER + "example.xml");
			File target = new File(SAVE_FOLDER + name);

			try {
				Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
				this.filename = name;

				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return false; // Never reached
	}
	
	/**
	 * Geef de current savegames bij naam
	 * @return Lijst van games
	 */
	public static ArrayList<String> getGames() {
		ArrayList<String> out = new ArrayList<String>();
		
		File folder = new File(SAVE_FOLDER);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".xml") && !listOfFiles[i].getName().equalsIgnoreCase("example.xml")) { // We willen geen example.xml, is de reference xml
				out.add(listOfFiles[i].getName().replaceFirst(".xml", ""));
			}
		}
		
		return out;
	}
	
	public String getChildValue(Node p, String name) {
		for (int i = 0; i < p.getChildNodes().getLength(); i++) {
			if (p.getChildNodes().item(i).getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (p.getChildNodes().item(i).getNodeName().equals(name)) {
				return p.getChildNodes().item(i).getTextContent().trim();
			}
		}
		return null;
	}
	
	public String getAttribute(NamedNodeMap attrs, String name) {
		return attrs.getNamedItem(name).getNodeValue();
	}
	
	
}