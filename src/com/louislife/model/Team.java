package com.louislife.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Random;

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
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param balance
	 * @param players
	 */
	public Team(int id, String name, int balance, ArrayList<Player> players) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.players = players;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Team(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.players = new ArrayList<Player>();
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

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player p) {
		for (Player player: players){
			if (player.getJerseyNumber()==p.getJerseyNumber()){
				p.setJerseyNumber((byte)(players.size()));
			}
			
			
		}
		this.players.add(p);
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Adds the specified amount to the team's balance.
	 * 
	 * @param amount - int. The amount that is to be added to the balance.
	 * 
	 * @author Wouter
	 */
	public void addToBalance(int amount) {
		this.balance += amount;
	}

	/**
	 * Removes the specified amount from the team's balance.
	 * 
	 * @param amount - int. The amount that is to be removed from the balance.
	 * 
	 * @author Wouter
	 */
	public void removeFromBalance(int amount) {
		this.balance -= amount;
	}

	public Player findPlayer(int id) {
		for (Player p : this.players) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}

	/**
	 * 
	 * @return Returns the total Stamina of the team.
	 * 
	 * @author Shane
	 */
	public int getTotStamina(){
		if(players.size() < 11){
			return -1;
		}
		int res = 0;
		for(int i = 0; i < 11; i++){
			res += players.get(i).getStaminaScore();
		}
		return res;
	}
	
	/**
	 * 
	 * @return Returns the total Offensive score of the team.
	 * 
	 * @author Shane
	 */
	public int getTotOff(){
		if(players.size() < 11){
			return -1;
		}
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
	
	/**
	 * 
	 * @return Returns the total Defensive score of the team.
	 * 
	 * @author Shane
	 */
	public int getTotDef(){
		if(players.size() < 11){
			return -1;
		}
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
	
	/**
	 * @return Returns an ArrayList of players who could have scored a goal, every STRIKER is put in twice because he has twice the chance of scoring
	 * 
	 * @author Shane
	 */
	public ArrayList<Player> getScoreList(){
		if(players.size() < 11){
			return null;
		}
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
	
	public boolean acceptsBid(Player player, int bid){
		Random rand = new Random();
		if (bid>player.calculatePrice()*((rand.nextFloat()/4)+0.8))
			return true;
		
		return false;
		
		
	}
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", players=" + players
				+ "]";
	}
	


	@Override
	public boolean equals(Object o) {
		if(o instanceof Team){
			Team that = (Team) o;
			if(this.balance == that.balance
				&& this.id == that.id
				&& this.name.equals(that.name)
				&& this.players.size() == that.players.size()){
				for(int i = 0; i < this.players.size(); i++){
					if(this.players.get(i).equals(that.players.get(i)) == false){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	/* JavaFX tableview properties */
	public StringProperty rankProperty() {
		return new SimpleStringProperty(Game.getInstance().getRank(this, 0)+"");
	}
	public StringProperty nameProperty() {
		return new SimpleStringProperty(this.getName());
	}
	public StringProperty playedProperty() {
		return new SimpleStringProperty(Game.getInstance().getPlayedForTeam(this)+"");
	}
	public StringProperty winProperty() {
		return new SimpleStringProperty(Game.getInstance().getWinsForTeam(this)+"");
	}
	public StringProperty tieProperty() {
		return new SimpleStringProperty(Game.getInstance().getTieForTeam(this)+"");
	}
	public StringProperty lossProperty() {
		return new SimpleStringProperty(Game.getInstance().getLossForTeam(this)+"");
	}
	public StringProperty pointsProperty() {
		return new SimpleStringProperty(Game.getInstance().getPointsForTeam(this.getId(), 0)+"");
	}

}
