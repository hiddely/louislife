package com.louislife.model;

import java.util.ArrayList;
import java.util.Collections;

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
	private int currentDay;
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

	public int getId() {
		return id;
	}
	
	/**
	 *  Can be removed? (Unnecessary because we can find players in ArrayList<Player> Team.Players)
	 * @param pl
	 * @return
	 */
	public Team getPlayerTeam(Player pl){
		int day = 0;
		int newTeamId = 0;
		for(int i = 0; i < this.transfers.size(); i++){
			if(pl.equals(transfers.get(i).getPlayer()) && transfers.get(i).getDay() > day){
				newTeamId = transfers.get(i).getTo();
			}
		}
		if(day != 0){
			return leagues.get(0).findTeam(newTeamId);
		}else{
			return leagues.get(0).findTeam(pl.getTeamId());
		}
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", currentDay="
				+ currentDay + ", currentTeam=" + currentTeam + ", leagues="
				+ leagues + ", transfers=" + transfers + ", matches=" + matches
				+ "]";
	}
	
	/**
	 * Creates the match schedule for the current league.
	 * 
	 * The match schedule is created by shuffling the League.Teams ArrayList and
	 * then matching all combinations against each other according to the 
	 * Premier League system (double round-robin)
	 */
	public void createMatchSchedule() {
		League curLeague = this.leagues.get(0); // current league hardcoded 'cause we only have one for now.
		ArrayList<Team> shuffledList = curLeague.getTeams();
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
		
		
		for (int i = 0; i < shuffledList.size(); i++) {
			
			for (int j = 0; j < shuffledList.size(); j++) {
				if (i != j) {
					Game.getInstance().addMatch(new Match(idCounter, j, shuffledList.get(i).getId(), shuffledList.get(j).getId()));
					idCounter++;
				}
			}
		}
		
	}
	
	
	
}