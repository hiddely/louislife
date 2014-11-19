package com.louislife.model;

import java.util.ArrayList;

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
	
	
	
}
