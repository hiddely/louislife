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
	public League(int id, String name, String country, ArrayList<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.teams = teams;
	}
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
	 * Returns the amount of matches that should be played in one week (one in-game round)
	 * @return int.
	 * 
	 * @author Wouter
	 */
	public int weeklyMatches() {
		return getTeams().size() * ( getTeams().size() - 1 ) / ( ( getTeams().size() - 1 ) * 2 );
	}
	
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
