package com.louislife.model;

import java.util.ArrayList;

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
	 * @return
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

	public int getId() {
		return id;
	}
	
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
	
	public boolean equals(Game that) {
		if (this.id == that.id && this.name.equals(that.getName())
				&& this.currentDay == that.currentDay
				&& this.currentTeam == that.currentTeam
				&& this.leagues.equals(that.getLeagues())
				&& this.transfers.equals(that.getTransfers())
				&& this.matches.equals(that.getMatches())) {
			return true;
		}
		return false;
	}
	
}