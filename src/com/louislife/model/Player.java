package com.louislife.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents football player
 * 
 * @author hidde
 *
 */
public class Player {

	private static int transferIdentifier = 0;
	private int id;
	private String firstname;
	private String surname;
	private byte number;
	private PlayerType type;
	private PlayerStatus status;
	private byte offensiveRating;
	private byte defensiveRating;
	private byte stamina;
	private int teamId;
	private int price;

	/**
	 * Creates a Player-Object
	 * 
	 * @param id
	 * @param firstname
	 * @param surname
	 * @param jerseyNumber
	 * @param type
	 * @param offensiveScore
	 * @param defensiveScore
	 * @param staminaScore
	 * @param teamId
	 * @param status
	 * @param price
	 */
	public Player(int id, String firstname, String surname, byte jerseyNumber,
			PlayerType type, PlayerStatus status, byte offensiveScore,
			byte defensiveScore, byte staminaScore, int teamId, int price) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.number =  jerseyNumber;
		this.type = type;
		this.status = status;
		this.offensiveRating = offensiveScore;
		this.defensiveRating = defensiveScore;
		this.stamina = staminaScore;
		this.teamId = teamId;
		this.price = price;
	}
	
	public int calculatePrice(){
		double res= (offensiveRating+defensiveRating+stamina); //average value
			if (offensiveRating>60) //account for excellation
				res=res+offensiveRating*0.8;
			if (defensiveRating>60)
				res=res+defensiveRating*0.8;
			if (stamina>60)
				res=res+stamina*0.8;

			res=res/200;
			
		try{
		res=res*5000000+Game.getInstance().getUserTeam().getBalance()/4*res;
		}
		catch(Exception e){
			res=res*5000000;
		}

		return (int)res;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public short getJerseyNumber() {
		return number;
	}

	public void setJerseyNumber(byte jerseyNumber) {
		this.number = jerseyNumber;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	public byte getOffensiveScore() {
		return offensiveRating;
	}

	public void setOffensiveScore(byte offensiveScore) {
		this.offensiveRating = offensiveScore;
	}

	public byte getDefensiveScore() {
		return defensiveRating;
	}

	public void setDefensiveScore(byte defensiveScore) {
		this.defensiveRating = defensiveScore;
	}

	public byte getStaminaScore() {
		return stamina;
	}

	public void setStaminaScore(byte staminaScore) {
		this.stamina = staminaScore;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Creates Transfer, adds it to the list of transfer and sets new balances
	 *
	 * @param leagueIndex
	 *            We have one League so leagueIndex = 0
	 * @param teamFromId
	 * @param teamToId
	 * @param playerId
	 * 
	 * @author Shane
	 */
	public void transferPlayer(int leagueIndex, int teamFromId,
			int teamToId) {
		Team teamFrom = Game.getInstance().getLeagues().get(leagueIndex)
				.findTeam(teamFromId);
		Team teamTo = Game.getInstance().getLeagues().get(leagueIndex)
				.findTeam(teamToId);
		int playerId = this.id;

		if (teamTo.getBalance() >= this.price) {
			Transfer transfer = new Transfer(transferIdentifier, teamFromId,
					teamToId, playerId, this.price, Game.getInstance()
							.getCurrentDay());
			Game.getInstance().addTransfer(transfer);
			transferIdentifier++;
			for (int i = 0; i < teamFrom.getPlayers().size(); i++) {
				if (teamFrom.getPlayers().get(i).getId() == this.id) {
					teamFrom.getPlayers().remove(i);
				}
			}
			;
			teamTo.getPlayers().add(this);
			teamFrom.setBalance(teamFrom.getBalance() + this.price);
			teamTo.setBalance(teamTo.getBalance() - this.price);
			this.setTeamId(teamToId);
		}
	}

	/**
	 * Sells a player to a random team which has enough balance
	 * 
	 * @param leagueIndex
	 * @param playerId
	 * 
	 * @author Shane
	 */
	public void sellPlayer(int leagueIndex, long seed) {
		int userTeamId = Game.getInstance().getUserTeam().getId();
		ArrayList<Team> tm = new ArrayList<Team>();
		for(int i = 0; i < Game.getInstance().getLeagues().get(leagueIndex).getTeams().size(); i++){
			if(Game.getInstance().getLeagues().get(leagueIndex).getTeams().get(i).getBalance() > this.price*2){
				tm.add(Game.getInstance().getLeagues().get(leagueIndex).getTeams().get(i));
			}
		}
		Random r = new Random(seed);
		int randomTeam = tm.get(r.nextInt(tm.size())).getId();
		this.transferPlayer(leagueIndex, userTeamId, randomTeam);
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstname=" + firstname + ", surname="
				+ surname + ", jerseyNumber=" + number + ", type=" + type
				+ ", offensiveRating=" + offensiveRating + ", defensiveRating="
				+ defensiveRating + ", staminaRating=" + stamina + ", teamId="
				+ teamId + ", price=" + price + "]";
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Player){
			Player pl = (Player) o;
			if (this.id == pl.id && this.firstname.equals(pl.getFirstname())
					&& this.surname.equals(pl.getSurname())
					&& this.number == pl.getJerseyNumber()
					&& this.type == pl.getType()
					&& this.offensiveRating == pl.getOffensiveScore()
					&& this.defensiveRating == pl.getDefensiveScore()
					&& this.stamina == pl.getStaminaScore()
					&& this.teamId == pl.teamId && this.price == pl.getPrice()) {
				return true;
			}
		}
		return false;
	}

}
