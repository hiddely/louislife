package com.louislife.model;

import java.util.ArrayList;

/**
 * Represents a league: A collection of teams in an area
 * @author hidde
 *
 */
public class League {

	private int id;
	private String name;
	private String country;
	private ArrayList<Team> teams;
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param country
	 * @param teams
	 */
	public League(int id, String name, String country, ArrayList<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.teams = teams;
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param country
	 */
	public League(int id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.teams = new ArrayList<Team>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ArrayList<Team> getTeams() {
		return teams;
	}
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	public void addTeam(Team t) {
		this.teams.add(t);
	}
	
	@Override
	public String toString() {
		return "League [id=" + id + ", name=" + name + ", country=" + country
				+ ", teams=" + teams + "]";
	}
	
	public Team findTeam(int id) {
		for (Team t : this.teams) {
			if (t.getId() == id)
				return t;
		}
		return null;
	}

	public Player findPlayer(int id) {
		for (Team t : this.teams) {
			for (Player p : t.getPlayers()) {
				if (p.getId() == id) {
					return p;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the amount of matches that one team has to play in the league.
	 * This also equals to the amount of rounds that a game has (the player plays one match per round/week).
	 * @return int - Amount of rounds
	 * 
	 * @author Wouter
	 */
	public int teamMatches() {
		return ( this.getTeams().size() - 1 ) * 2;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof League){
			League that = (League) o;
			if(this.id == that.id
				&& this.country.equals(that.country)
				&& this.name.equals(that.name)
				&& this.teams.size() == that.teams.size()){
				for(int i = 0; i < this.teams.size(); i++){
					if(this.teams.get(i).equals(that.teams.get(i)) == false){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
}
