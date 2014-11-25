package com.louislife.model;

public class Player {

	private int id;
	private String firstname;
	private String surname;
	private byte jerseyNumber;
	private PlayerType type;
	private short offensiveRating;
	private short defensiveRating;
	private short staminaRating;
	private int teamId;
	private PlayerStatus status;
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
			PlayerType type, short offensiveScore, short defensiveScore,
			short staminaScore, int teamId, PlayerStatus status, int price) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.jerseyNumber = jerseyNumber;
		this.type = type;
		this.offensiveRating = offensiveScore;
		this.defensiveRating = defensiveScore;
		this.staminaRating = staminaScore;
		this.teamId = teamId;
		this.status = status;
		this.price = price;
	}

	// Getters and Setters

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
		return jerseyNumber;
	}

	public void setJerseyNumber(byte jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

	public short getOffensiveScore() {
		return offensiveRating;
	}

	public void setOffensiveScore(short offensiveScore) {
		this.offensiveRating = offensiveScore;
	}

	public short getDefensiveScore() {
		return defensiveRating;
	}

	public void setDefensiveScore(short defensiveScore) {
		this.defensiveRating = defensiveScore;
	}

	public short getStaminaScore() {
		return staminaRating;
	}

	public void setStaminaScore(short staminaScore) {
		this.staminaRating = staminaScore;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// Getters and Setters end

}
