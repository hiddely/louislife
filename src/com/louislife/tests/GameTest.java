package com.louislife.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.louislife.model.*;

public class GameTest {

	@Test
	public void testGame() {
		Game game1 = new Game(1,"Test",1,1);
		Game game2 = new Game(1,"Test",1,1);
		
		assertTrue(game1.equals(game2));
	}

	@Test
	public void testGetInstance() {
		Game game1 = new Game(1,"Test",1,1);
		assertEquals(game1, Game.getInstance());
	}

	@Test
	public void testNextDay() {
		Game game1 = new Game(1,"Test",1,1);
		game1.nextDay();
		assertEquals(2, game1.getCurrentDay());
	}

	@Test
	public void testNextWeek() {
		Game game1 = new Game(1,"Test",1,1);
		game1.nextWeek();
		assertEquals(8, game1.getCurrentDay());
	}

	@Test
	public void testGetUserTeam() {
		Game game1 = new Game(1,"Test",1,1);
		
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		
		game1.addLeague(league1);
		
		assertEquals(team1, game1.getUserTeam());
	}

	@Test
	public void testGetName() {
		Game game1 = new Game(1,"Test",1,1);
		assertEquals("Test", game1.getName());
	}

	@Test
	public void testSetName() {
		Game game1 = new Game(1,"Test",1,1);
		game1.setName("Elgar");
		assertEquals("Elgar", game1.getName());
	}

	@Test
	public void testGetCurrentDay() {
		Game game1 = new Game(1,"Test",1,1);
		
		assertEquals(1, game1.getCurrentDay());
	}

	@Test
	public void testSetCurrentDay() {
		Game game1 = new Game(1,"Test",1,1);
		game1.setCurrentDay(2);
		
		assertEquals(2, game1.getCurrentDay());
	}

	@Test
	public void testGetLeagues() {
		Game game1 = new Game(1,"Test",1,1);
		
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		ArrayList<League> leagues1 = new ArrayList<League>();
		leagues1.add(league1);
		
		game1.addLeague(league1);
		
		assertEquals(leagues1, game1.getLeagues());
	}

	@Test
	public void testGetMatches() {
		Game game1 = new Game(1,"Test",1,1);
		
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		Match match1 = new Match(1, 1, 1, 2);
		ArrayList<Match> matches1 = new ArrayList<Match>();
		matches1.add(match1);
		
		game1.addMatch(match1);
		
		assertEquals(matches1, game1.getMatches());
	}

	@Test
	public void testSetLeagues() {
		Game game1 = new Game(1,"Test",1,1);
		
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		ArrayList<League> leagues1 = new ArrayList<League>();
		leagues1.add(league1);
		League league2 = new League(2, "Jupiler Liga", "Frankrijk", teams1);
		ArrayList<League> leagues2 = new ArrayList<League>();
		leagues2.add(league1);
		leagues2.add(league2);
		
		game1.addLeague(league1);
		
		game1.setLeagues(leagues2);
		
		assertEquals(leagues2, game1.getLeagues());
	}

	@Test
	public void testAddLeague() {
		Game game1 = new Game(1,"Test",1,1);
		
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		ArrayList<League> leagues1 = new ArrayList<League>();
		leagues1.add(league1);
		
		game1.addLeague(league1);
		
		assertEquals(leagues1, game1.getLeagues());
	}

	@Test
	public void testAddTransfer() {
		Game game1 = new Game(1,"Test",1,1);
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		ArrayList<Transfer> transfers1 = new ArrayList<Transfer>();
		transfers1.add(transfer1);
		game1.addTransfer(transfer1);
		
		assertEquals(transfers1, game1.getTransfers());
	}

	@Test
	public void testAddMatch() {
		Game game1 = new Game(1,"Test",1,1);
		
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		Match match1 = new Match(1, 1, 1, 2);
		ArrayList<Match> matches1 = new ArrayList<Match>();
		matches1.add(match1);
		
		game1.addMatch(match1);
		
		assertEquals(matches1, game1.getMatches());	
	}

	@Test
	public void testGetId() {
		Game game1 = new Game(1,"Test",1,1);
		
		assertEquals(1, game1.getId());
	}

	@Test
	public void testGetPlayerTeam() {
		Game game1 = new Game(1,"Test",1,1);
		
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		ArrayList<League> leagues1 = new ArrayList<League>();
		leagues1.add(league1);
		
		game1.addLeague(league1);
		
		assertEquals(team1, game1.getPlayerTeam(player1));
	}

	@Test
	public void testToString() {
		Game game1 = new Game(1,"Test",1,1);
		
		assertEquals("Game [id=1, name=Test, currentDay=1, currentTeam=1, leagues=[], transfers=[], matches=[]]", game1.toString());
	}

}
