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
	
	/**
	 * Returns the amount of matches that should be played in one week (one in-game round)
	 * @return int.
	 * 
	 * @author Wouter
	 */
	public int weeklyMatches() {
		return getTeams().size() * ( getTeams().size() - 1 ) / ( ( getTeams().size() - 1 ) * 2 );
	}
	
}
