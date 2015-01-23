package com.louislife.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Represents a Game object with leagues and played matches. This holds all the
 * game data necessary for one instance of the game to function.
 * This object is a Singleton.
 * 
 * @author hidde
 *
 */
public class Game {

	private static Game sGame;
	
	private int id;
	private String name;
	private String xmlName;
	private int currentDay;

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	private int currentTeam;
	private ArrayList<League> leagues;
	private ArrayList<Transfer> transfers;
	private ArrayList<Match> matches;

	public Game(int id, String name, int currentDay, int currentTeam) {
		super();
		this.id = id;
		this.name = name;
		this.currentDay = currentDay;
		this.currentTeam = currentTeam;
		
		leagues = new ArrayList<League>();
		transfers = new ArrayList<Transfer>();
		matches = new ArrayList<Match>();
		
		// Set static for singleton
		Game.sGame = this;
	}
	
	/**
	 * Used for singleton instance
	 */
	public Game() {
		
	}
	
	/**
	 * Get static singleton for Game
	 * @return - Returns the Game Singleton object
	 */
	public static Game getInstance() {
		if (sGame == null) {
			sGame = new Game(); // Empty game
		}
		return sGame;
	}
	
	
	
	/**
	 * Adds 1 day to the Day count. Currently not in use (use nextWeek to play a round).
	 */
	public void nextDay(){
		currentDay++;
	}
	
	/**
	 * Adds 7 days to the Day count (used to play a  round).
	 */
	public void nextWeek(){
		currentDay += 7;
	}
	
	public Team getUserTeam() {
		return leagues.get(0).findTeam(currentTeam);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	public ArrayList<League> getLeagues() {
		return leagues;
	}
	
	public ArrayList<Match> getMatches() {
		return matches;
	}
	
	public ArrayList<Transfer> getTransfers() {
		return transfers;
	}

	public void setLeagues(ArrayList<League> leagues) {
		this.leagues = leagues;
	}
	
	public void addLeague(League l) {
		this.leagues.add(l);
	}
	
	public void addTransfer(Transfer t) {
		this.transfers.add(t);
	}
	
	public void addMatch(Match m) {
		this.matches.add(m);
	}

	public void setCurrentTeam(int currentTeam) {
		this.currentTeam = currentTeam;
	}
	
	public int getCurrentTeam() {
		return this.currentTeam;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", currentDay="
				+ currentDay + ", currentTeam=" + currentTeam + ", leagues="
				+ leagues + ", transfers=" + transfers + ", matches=" + matches
				+ "]";
	}
	
	public boolean equals(Object o) {
		if(o instanceof Game){
			Game that = (Game) o;
			if (this.id == that.id && this.name.equals(that.getName())
					&& this.currentDay == that.currentDay
					&& this.currentTeam == that.currentTeam
					&& this.leagues.size() == that.leagues.size()
					&& this.transfers.size() == that.transfers.size()
					&& this.matches.size() == that.matches.size()) {
				for(int i = 0; i < this.leagues.size(); i++){
					if(this.leagues.get(i).equals(that.leagues.get(i)) == false){
						return false;
					}
				}
				for(int i = 0; i < this.transfers.size(); i++){
					if(this.transfers.get(i).equals(that.transfers.get(i)) == false){
						return false;
					}
				}
				for(int i = 0; i < this.matches.size(); i++){
					if(this.matches.get(i).equals(that.matches.get(i)) == false){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	
	public static String[] getQuotes() {
		return new String[] {
				"That is another koek",
				"Ben ik nou zo slim, of ben jij zo dom?",
				"It is the dead or the gladiolie",
				""
		};
	}
	
	/**
	 * Creates the match schedule for the current league. The first match happens on day 0.
	 * 
	 * The match schedule is created by shuffling the League.Teams ArrayList and
	 * then matching all combinations against each other according to the 
	 * Premier League system (double round-robin)
	 */
	public void createMatchSchedule() {
		League curLeague = this.leagues.get(0); // current league hardcoded 'cause we only have one for now.
		ArrayList<Team> shuffledList = new ArrayList<>();
		shuffledList.addAll(curLeague.getTeams());
		Collections.shuffle(shuffledList);
		
		int idCounter = 0;
		
		// Adds all matches that the User will play.
		for (int p = 0; p < shuffledList.size(); p++) {
			if (!Game.getInstance().getUserTeam().equals(shuffledList.get(p) ) ) {
				Game.getInstance().addMatch(new Match(idCounter, currentTeam, shuffledList.get(p).getId() ) );
				idCounter++;
				Game.getInstance().addMatch(new Match(idCounter, shuffledList.get(p).getId(), currentTeam ) );
				idCounter++;
			}
		}
		
		Collections.shuffle(matches);
		
		int day = 0;
		for (int i = 0; i < matches.size(); i++) {
			matches.get(i).setDay(day);
			day += 7;
		}
		
		
		// Adds all remaining matches to the match list
		shuffledList.remove(Game.getInstance().getUserTeam());
		day = 0;
		int weeklyCounter = 1;
		
		for (int i = 0; i < shuffledList.size(); i++) {
			
			for (int j = 0; j < shuffledList.size(); j++) {
				
				if (i != j) {
					Game.getInstance().addMatch(new Match(idCounter, day, shuffledList.get(i).getId(), shuffledList.get(j).getId()));
					idCounter++;
					
					// Go to next week
					if (weeklyCounter < curLeague.weeklyMatches() ) {
						day += 7;
						weeklyCounter = 1;
					}
				}
			}
		}
		
	}

	/**
	 * Returns sorted array of teams, sorted by points descending
	 * @param league The current league
	 * @return
	 */
	public ArrayList<Team> getRankList(int league) {
		ArrayList<Team> output = new ArrayList<Team>();
		League l = this.leagues.get(league);
		output.addAll(l.getTeams());
		Collections.sort(output, new Comparator<Team>() {
			@Override
			public int compare(Team o1, Team o2) {
				int t1 = getPointsForTeam(o1.getId(), league);
				int t2 = getPointsForTeam(o2.getId(), league);
				int com = Integer.valueOf(t2).compareTo(Integer.valueOf(t1));
				return com;
			}
		});
		return output;
	}

	public int getRank(Team team, int league) {
		int i = 1;
		for (Team t : getRankList(league)) {
			if (t.equals(team)) {
				return i;
			}
			i++;
		}
		return -1; // Team not found
	}

	/**
	 * Calculates all points for a team in the matches
	 * @param id
	 * @param league
	 * @return
	 */
	public int getPointsForTeam(int id, int league) {
		Team t = this.leagues.get(league).findTeam(id);
		int points = 0;
		for (Match m : matches) {
			try {
				points += m.getScore(t);
			} catch (TeamNotFoundException tnfe) {
				// Do nothing
			}
		}
		return points;
	}

	public int getPlayedForTeam(Team t) {
		int amt = 0;
		for (Match m : matches) {
			if (m.isPlayed() && (m.getTH().equals(t) || m.getTA().equals(t)))
				amt++;
		}
		return amt;
	}
	public int getWinsForTeam(Team t) {
		int amt = 0;
		for (Match m : matches) {
			if (t.equals(m.getWinningTeam()))
				amt++;
		}
		return amt;
	}
	public int getTieForTeam(Team t) {
		int amt = 0;
		for (Match m : matches) {
			if (m.isPlayed() && m.getWinningTeam() == null && m.getLosingTeam() == null && (m.getTH().equals(t) || m.getTA().equals(t)))
				amt++; // For a tie
		}
		return amt;
	}
	public int getLossForTeam(Team t) {
		int amt = 0;
		for (Match m : matches) {
			if (t.equals(m.getLosingTeam()))
				amt++;
		}
		return amt;
	}
}

