package com.louislife.model;

/**
 * Represents football player
 * 
 * @author hidde
 *
 */
public class Player {

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
		this.number = jerseyNumber;
		this.type = type;
		this.status = status;
		this.offensiveRating = offensiveScore;
		this.defensiveRating = defensiveScore;
		this.stamina = staminaScore;
		this.teamId = teamId;
		this.price = price;
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

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstname=" + firstname + ", surname="
				+ surname + ", jerseyNumber=" + number + ", type=" + type
				+ ", offensiveRating=" + offensiveRating + ", defensiveRating="
				+ defensiveRating + ", staminaRating=" + stamina + ", teamId="
				+ teamId + ", price=" + price + "]";
	}

	public boolean equals(Player pl) {
		if (this.id == pl.id && this.firstname.equals(pl.getFirstname())
				&& this.surname.equals(pl.getSurname())
				&& this.number == pl.getJerseyNumber()
				&& this.type == pl.getType()
				&& this.offensiveRating == pl.getDefensiveScore()
				&& this.defensiveRating == pl.getDefensiveScore()
				&& this.stamina == pl.getStaminaScore()
				&& this.teamId == pl.teamId && this.price == pl.getPrice()) {
			return true;
		}
		return false;
	}

}
