package com.louislife.controller;

import java.util.Observable;

import javafx.event.ActionEvent;

import com.louislife.model.*;

public class TeamManagementController extends Observable {

	private int transferIdentifier = 0;

	
	/**
	 * Creates Transfer, adds it to the list of transfer and sets new balances
	 *
	 * @param leagueIndex
	 *            We have one League so leagueIndex = 0
	 * @param teamFromId
	 * @param teamToId
	 * @param playerId
	 */
	public void buyPlayer(int leagueIndex, int teamFromId, int teamToId,
			int playerId) {
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
			for (int i = 0; i < teamFrom.getPlayers().size(); i++){
				if (teamFrom.getPlayers().get(i).equals(pl)){
					teamFrom.getPlayers().remove(i);
				}
			};
			teamTo.getPlayers().add(pl);
			teamFrom.setBalance(teamFrom.getBalance() + pl.getPrice());
			teamTo.setBalance(teamTo.getBalance() - pl.getPrice());
		}
	}
	
	public void BuyPlayerAction(ActionEvent BuyPlayerBtn){
		System.out.println("player bought");
	}
}
