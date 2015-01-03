package com.louislife.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import com.louislife.model.*;

import org.junit.Test;

public class TeamTest {

	@Test
	public void testGetId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);

		assertEquals(1, team1.getId());
	}

	@Test
	public void testSetId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		team1.setId(2);
		
		assertEquals(2, team1.getId());
	}

	@Test
	public void testGetName() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		assertEquals("United States", team1.getName());
	}

	@Test
	public void testSetName() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		team1.setName("United Staids");
		
		assertEquals("United Staids", team1.getName());
	}

	@Test
	public void testGetPlayers() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		assertTrue(team1.getPlayers().equals(players1));
	}

	@Test
	public void testSetPlayers() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		Player player2 = new Player(2, "Edgar", "Davids", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		ArrayList<Player> players2 = new ArrayList<Player>();
		players1.add(player1);
		players2.add(player1);
		players2.add(player2);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		team1.setPlayers(players2);
		
		assertTrue(team1.getPlayers().equals(players2));
	}

	@Test
	public void testAddPlayer() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		Player player2 = new Player(2, "Edgar", "Davids", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		ArrayList<Player> players2 = new ArrayList<Player>();
		players1.add(player1);
		players2.add(player1);
		players2.add(player2);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		team1.addPlayer(player2);
		
		assertTrue(team1.getPlayers().equals(players2));
	}

	@Test
	public void testGetBalance() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		assertEquals(10000, team1.getBalance());
	}

	@Test
	public void testSetBalance() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		team1.setBalance(20000);
		
		assertEquals(20000, team1.getBalance());
	}

	@Test
	public void testFindPlayer() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		assertEquals(player1, team1.findPlayer(1));
	}

	@Test
	public void testToString() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		assertEquals("Team [id=1, name=United States, players=[Player [id=1, firstname=Elgar, surname=Weijtmans, jerseyNumber=10, type=STRIKER, offensiveRating=10, defensiveRating=10, staminaRating=10, teamId=1, price=100]]]", team1.toString());
	}

}
