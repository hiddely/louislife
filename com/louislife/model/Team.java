package com.louislife.model;

import java.util.ArrayList;

/**
 * Represents a Team object with players
 * @author hidde
 *
 */
public class Team {
	
	private int id;
	private String name;
	private ArrayList<Player> players;
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
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public Team(int id, String name, ArrayList<Player> players) {
		super();
		this.id = id;
		this.name = name;
		this.players = players;
	}
	
	

}
