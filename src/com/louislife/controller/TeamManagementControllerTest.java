package com.louislife.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.louislife.model.*;

public class TeamManagementControllerTest {

	@Test
	public void testTransferPlayer() {
		byte henkjn = 35;
		byte henkoff = 88;
		byte henkdef = 34;
		byte henkstam = 98;
		Player pl1 = new Player(15, "Henk", "Langvoet", henkjn, PlayerType.STRIKER, PlayerStatus.NORMAL, henkoff, henkdef, henkstam, 0, 980000);
		byte pietjn = 35;
		byte pietoff = 88;
		byte pietdef = 34;
		byte pietstam = 98;
		Player pl2 = new Player(18, "Piet", "Dikvoet", pietjn, PlayerType.STRIKER, PlayerStatus.YELLOWCARD, pietoff, pietdef, pietstam, 0, 650000);
		ArrayList<Player> PlayerList1 = new ArrayList<Player>();
		PlayerList1.add(pl1);
		ArrayList<Player> PlayerList2 = new ArrayList<Player>();
		PlayerList2.add(pl2);
		Team tm1 = new Team(1, "Ajax", 12000000, PlayerList1);
		Team tm2 = new Team(23, "PSV", 8000000, PlayerList2);
		ArrayList<Team> TeamList = new ArrayList<Team>();
		TeamList.add(tm1);
		TeamList.add(tm2);
		League league = new League(1, "TestLeague", "Nederland", TeamList);
		Game game = new Game(1, "TMCTestGame", 0, 0);
		game.addLeague(league);
		
		//Transfers Piet Dikvoet from PSV to Ajax
		TeamManagementController.transferPlayer(0, 23, 1, 18);
		
		assertEquals(tm2.getPlayers().contains(pl2), false);
		assertEquals(tm1.getPlayers().contains(pl2), true);
		assertEquals(tm1.getPlayers().size(), 2);
		assertEquals(tm1.getPlayers().contains(pl1), true);
		assertEquals(tm1.getBalance(), 12000000 - 650000);
		assertEquals(tm2.getBalance(), 8000000 + 650000);
	}
	
	@Test
	public void testSelPlayer() {
		byte henkjn = 35;
		byte henkoff = 88;
		byte henkdef = 34;
		byte henkstam = 98;
		Player pl1 = new Player(15, "Henk", "Langvoet", henkjn, PlayerType.STRIKER, PlayerStatus.NORMAL, henkoff, henkdef, henkstam, 0, 980000);
		ArrayList<Player> PlayerList1 = new ArrayList<Player>();
		PlayerList1.add(pl1);
		ArrayList<Player> PlayerList2 = new ArrayList<Player>();
		ArrayList<Player> PlayerList3 = new ArrayList<Player>();
		Team tm1 = new Team(0, "Ajax", 12000000, PlayerList1);
		Team tm2 = new Team(1, "PSV", 11500000, PlayerList2);
		Team tm3 = new Team(2, "NEC", 850000, PlayerList3);
		Team tm4 = new Team(3, "PEC", 50000, PlayerList3);
		Team tm5 = new Team(4, "SEC", 90000, PlayerList3);
		ArrayList<Team> TeamList = new ArrayList<Team>();
		TeamList.add(tm1);
		TeamList.add(tm2);
		TeamList.add(tm3);
		TeamList.add(tm4);
		TeamList.add(tm5);
		League league = new League(1, "TestLeague", "Nederland", TeamList);
		Game game = new Game(1, "TMCTestGame", 0, 0);
		game.addLeague(league);

		TeamManagementController.selPlayer(0, 15, 500);
		assertEquals(tm3.getPlayers().contains(pl1), false);
		assertEquals(tm2.getPlayers().contains(pl1), true);
		assertEquals(tm1.getPlayers().contains(pl1), false);
		assertEquals(tm1.getPlayers().size(), 0);
		assertEquals(tm1.getBalance(), 12000000 + 980000);
		assertEquals(tm2.getBalance(), 11500000 - 980000);
	}

}
