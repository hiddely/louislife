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

	private int currentTeam;
	private ArrayList<League> leagues;
	private ArrayList<Transfer> transfers;
	private ArrayList<Match> matches;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param currentDay
	 * @param currentTeam
	 */
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

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}
	
	/**
	 * 
	 * @return Team
	 */
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
				"I have achieved more with Ajax in six years than Barcelona has in one hundred years",
				"Congratulations on signing the best coach in the world",
				"I am who I am: confident, arrogant, dominant, honest, hard-working and innovative",
				};
	}
	
	/**
	 * Creates the match schedule for the current league. The first match happens on day 0.
	 * 
	 * The match schedule is created by shuffling a copy of the League.Teams ArrayList and
	 * then matching all combinations against each other according to the 
	 * Premier League system (double round-robin)
	 */
	public void createMatchSchedule() {
		League curLeague = this.leagues.get(0); // current league hardcoded because we only have one.
		ArrayList<Team> shuffledList = new ArrayList<Team>();
		shuffledList.addAll(curLeague.getTeams());
		Collections.shuffle(shuffledList);
		int idCounter = 0;

		int day = 1;
		int n = shuffledList.size()/2; // Number of teams must be even!!
		for (int round = 0; round < (shuffledList.size()-1)*2; round++) {
			for (int i = 0; i < n; i++) {
				if (round % 2 == 0) {
					System.out.println("Round "+round+", match: "+shuffledList.get(i).getName()+" vs. "+shuffledList.get(shuffledList.size()-i-1).getName());
					Game.getInstance().addMatch(new Match(idCounter, day, shuffledList.get(i).getId(), shuffledList.get(shuffledList.size()-i-1).getId()) );
				} else {
					System.out.println("ARound "+round+", match: "+shuffledList.get(shuffledList.size()-i-1).getName()+" vs. "+shuffledList.get(i).getName());
					Game.getInstance().addMatch(new Match(idCounter, day, shuffledList.get(shuffledList.size()-i-1).getId(), shuffledList.get(i).getId()) );
				}
				idCounter++;
			}
			shuffledList = rotateNext(shuffledList); // Rotate to next
			day += 7;
		}
	}

	public ArrayList<Team> rotateNext(ArrayList<Team> teams) {
		Team fixed = teams.get(0);
		teams.remove(0);
		Collections.rotate(teams, 1);
		teams.add(0, fixed);
		return teams;
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

