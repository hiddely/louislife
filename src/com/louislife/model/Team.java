package com.louislife.model;

import java.util.ArrayList;

/**
 * Represents a Team object with players
 * 
 * @author hidde
 *
 */
public class Team {

	private int id;
	private String name;
	private ArrayList<Player> players;
	private int balance;

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

	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Team(int id, String name, int balance, ArrayList<Player> players) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.players = players;
	}

	public Team(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.players = new ArrayList<Player>();
	}

	public Player findPlayer(int id) {
		for (Player p : this.players) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}
	
	public int getTotScore(){
		int res = 0;
		for(int i = 0; i < 11; i++){
			if(players.get(i).getType() == PlayerType.STRIKER){
				res += players.get(i).getOffensiveScore(); 
			}else if(players.get(i).getType() == PlayerType.DEFENDER || players.get(i).getType() == PlayerType.GOALKEEPER){
				res += players.get(i).getDefensiveScore();
			}else if(players.get(i).getType() == PlayerType.MIDFIELDER){
				int sum = players.get(i).getDefensiveScore() + players.get(i).getOffensiveScore();
				res += sum/2;
			}
		}
		return res;
	}

	public int getTotStamina(){
		int res = 0;
		for(int i = 0; i < 11; i++){
			res += players.get(i).getStaminaScore();
		}
		return res;
	}
	
	public int getTotOff(){
		int res = 0;
		for(int i = 0; i < 11; i++){
			if(players.get(i).getType() == PlayerType.STRIKER){
				res += players.get(i).getOffensiveScore();
			}else if(players.get(i).getType() == PlayerType.MIDFIELDER){
				res += players.get(i).getOffensiveScore()*0.75;
			}
		}
		return res;
	}
	
	public int getTotDef(){
		int res = 0;
		for(int i = 0; i < 11; i++){
			if(players.get(i).getType() == PlayerType.DEFENDER || players.get(i).getType() == PlayerType.GOALKEEPER){
				res += players.get(i).getDefensiveScore();
			}else if(players.get(i).getType() == PlayerType.MIDFIELDER){
				res += players.get(i).getDefensiveScore() * 0.75;
			}
		}
		return res;
	}
	
	public ArrayList<Player> getScoreList(){
		ArrayList<Player> res = new ArrayList<Player>();
		for(int i = 0; i < 11; i++){
			if(players.get(i).getType() == PlayerType.STRIKER){
				res.add(players.get(i));
				res.add(players.get(i));
			}else if(players.get(i).getType() == PlayerType.MIDFIELDER){
				res.add(players.get(i));
			}
		}
		return res;
	}
	
	public Player getPlayerWithJerseyNumber(int number){
		for (Player player:players){
			if (player.getJerseyNumber()==number)
					return player;
		}
		return null;
		
	}
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", players=" + players
				+ "]";
	}
	
	/**
	 * Checks equality of Teams by comparing Team.id
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Team) {
			Team that = (Team) o;
			
			return this.getId() == that.getId();
		}
		return false;
	}

}
