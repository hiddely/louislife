package com.louislife.controller;

import java.util.Observable;
import java.util.Random;

import javafx.event.ActionEvent;

import com.louislife.model.*;

public class TeamManagementController extends Observable {

	private static int transferIdentifier = 0;

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
	public static void selPlayer(int leagueIndex, int playerId) {
		int userTeamId = Game.getInstance().getUserTeam().getId();
		Player pl = Game.getInstance().getLeagues().get(leagueIndex)
				.findTeam(userTeamId).findPlayer(playerId);
		int loop = 1;
		int looptimes = 0;
		int randomTeam = 0;
		while (loop == 1) {
			looptimes++;
			Random generator = new Random();
			randomTeam = generator.nextInt(Game.getInstance().getLeagues()
					.get(leagueIndex).getTeams().size());
			if (randomTeam != userTeamId) {
				if (pl.getPrice() <= Game.getInstance().getLeagues()
						.get(leagueIndex).getTeams().get(randomTeam)
						.getBalance()
						|| looptimes > 100) {
					loop = 0;
				}
			}
		}
		transferPlayer(leagueIndex, userTeamId, randomTeam, playerId);
	}

	/**
	 * Actions which follow after pushing the Buy Player Button
	 * 
	 * @param BuyPlayerBtn
	 */
	public void BuyPlayerAction(ActionEvent BuyPlayerBtn) {
		System.out.println("player bought");
	}
}
