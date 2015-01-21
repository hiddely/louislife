package com.louislife.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * A team transfer
 * @author hidde
 *
 */
public class Transfer {
	
	/**
	 * A transfer object should be created any time a player is transferred.
	 * This class serves as a history log of all transfers that happened.
	 * To get() a player's team or a team's player, the Team and Player classes sh
	 */
	
	private int id;
	private int from;
	private int to;
	private int player;
	private int price;
	private int day;
	private static int transferIdentifier;

	public Transfer(int id, int from, int to, int player, int price, int day) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.player = player;
		this.price = price;
		this.day = day;
	}
	
	public int getId(){
		return id;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getPlayer() {
		return player;
	}

	public int getPrice() {
		return price;
	}

	public int getDay() {
		return day;
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
	public static void transferPlayer(int leagueIndex, int teamFromId,
			int teamToId, int playerId) {
		Team teamFrom = Game.getInstance().getLeagues().get(leagueIndex)
				.findTeam(teamFromId);
		Team teamTo = Game.getInstance().getLeagues().get(leagueIndex)
				.findTeam(teamToId);
		Player pl = Game.getInstance().getLeagues().get(leagueIndex)
				.findTeam(teamFromId).findPlayer(playerId);

		if (teamTo.getBalance() >= pl.getPrice()) {
			Transfer transfer = new Transfer(transferIdentifier, teamFromId,
					teamToId, playerId, pl.getPrice(), Game.getInstance()
							.getCurrentDay());
			Game.getInstance().addTransfer(transfer);
			transferIdentifier++;
			for (int i = 0; i < teamFrom.getPlayers().size(); i++) {
				if (teamFrom.getPlayers().get(i).getId() == pl.getId()) {
					teamFrom.getPlayers().remove(i);
				}
			}
			;
			teamTo.getPlayers().add(pl);
			teamFrom.setBalance(teamFrom.getBalance() + pl.getPrice());
			teamTo.setBalance(teamTo.getBalance() - pl.getPrice());
			pl.setTeamId(teamToId);
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
	public static void selPlayer(int leagueIndex, int playerId, long seed) {
		int userTeamId = Game.getInstance().getUserTeam().getId();
		Player pl = Game.getInstance().getLeagues().get(leagueIndex)
				.findTeam(userTeamId).findPlayer(playerId);
		ArrayList<Team> tm = new ArrayList<Team>();
		for(int i = 0; i < Game.getInstance().getLeagues().get(leagueIndex).getTeams().size(); i++){
			if(Game.getInstance().getLeagues().get(leagueIndex).getTeams().get(i).getBalance() > pl.getPrice()*2){
				tm.add(Game.getInstance().getLeagues().get(leagueIndex).getTeams().get(i));
			}
		}
		Random r = new Random(seed);
		int randomTeam = tm.get(r.nextInt(tm.size())).getId();
		transferPlayer(leagueIndex, userTeamId, randomTeam, playerId);
	}
	
	@Override
	public String toString() {
		return "Transfer [id=" + id +", from=" + from + ", to=" + to + ", player=" + player
				+ ", price=" + price + ", day=" + day + "]";
	}
	

}
