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
		Game game3 = new Game(2,"Test2",3,4);
		Game game4 = new Game(3,"Test3",1,1);
		Game game5 = new Game(3,"Test3",1,1);
		Game game6 = new Game(3,"Test3",1,1);
		Game game7 = new Game(3,"Test3",1,1);
		Game game8 = new Game(3,"Test3",1,1);
		Game game9 = new Game(3,"Test3",1,1);
		Game game10 = new Game(3,"Test3",1,1);
		Game game11 = new Game(3,"Test3",1,1);
		Game game12 = new Game(3,"Test3",1,1);
		Match match1 = new Match(0, 0, 0 ,1);
		Match match2 = new Match(1, 0, 2 ,3);
		
		League league1 = new League(1, "Eredivisie", "Nederland");
		League league2 = new League(2, "Premier League", "Nederland");
		Transfer transfer1 = new Transfer(0, 0, 1, 0 ,0, 0);
		Transfer transfer2 = new Transfer(1, 0, 3, 0 ,0, 5);
		game4.addLeague(league1);
		game5.addLeague(league2);
		game6.addLeague(league1);
		game7.addTransfer(transfer1);
		game8.addTransfer(transfer2);
		game9.addTransfer(transfer1);
		game10.addMatch(match1);
		game11.addMatch(match2);
		game12.addMatch(match1);
		
		
		assertEquals(game1, game2);
		assertNotEquals(game1, game3);
		assertNotEquals(game1, match1);
		assertNotEquals(game4, game5);
		assertEquals(game4, game6);
		assertNotEquals(game7, game8);
		assertEquals(game7, game9);
		assertNotEquals(game10, game11);
		assertEquals(game10, game12);
		
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
	public void testToString() {
		Game game1 = new Game(1,"Test",1,1);
		
		assertEquals("Game [id=1, name=Test, currentDay=1, currentTeam=1, leagues=[], transfers=[], matches=[]]", game1.toString());
	}
	
	@Test
	public void testGetXmlName(){
		Game game1 = new Game(1,"Test",1,1);
		game1.setXmlName("TestSave");
		assertEquals("TestSave", game1.getXmlName());
	}
	
	@Test
	public void testGetCurrentTeam(){
		Game game1 = new Game(1,"Test",1,23);
		assertEquals(23, game1.getCurrentTeam());
	}
	
	@Test
	public void testSetCurrentTeam(){
		Game game1 = new Game(1,"Test",1,1);
		game1.setCurrentTeam(50);
		assertEquals(50, game1.getCurrentTeam());
	}
	
	// User team is teamid 1
	@Test
	public void testCreateMatchScheduleOne() {
		Game game1 = new Game(1, "Test",1,1);
		game1.addLeague(new League(1, "Eredivisie", "Nederland"));
		League curLeague = game1.getLeagues().get(0);
		Team tm1 = new Team(1, "Ajax");
		Team tm2 = new Team(2, "FC Twente");
		Team tm3 = new Team(3, "Feyenoord");
		Team tm4 = new Team(4, "DenHaag");
		Team tm5 = new Team(5, "RotteDaem");
		Team tm6 = new Team(6, "Delftsch");
		curLeague.addTeam(tm1);
		curLeague.addTeam(tm2);
		curLeague.addTeam(tm3);
		curLeague.addTeam(tm4);
		curLeague.addTeam(tm5);
		curLeague.addTeam(tm6);
		
		game1.createMatchSchedule();
		
		assertEquals(game1.getMatches().size(), 30);
	}

	// User team is teamid 1
	@Test
	public void testCreateMatchScheduleThree() {
		Game game1 = new Game(2, "Test",1,3);
		game1.addLeague(new League(2, "Eredivisie", "Nederland"));
		League curLeague = game1.getLeagues().get(0);
		Team t1 = new Team(1, "Ajax");
		Team t2 = new Team(2, "FC Twente");
		Team t3 = new Team(3, "Feyenoord");
		Team t4 = new Team(4, "DenHaag");
		Team t5 = new Team(5, "RotteDaem");
		Team t6 = new Team(6, "Delftsch");
		Team t7 = new Team(7, "Greuningen");
		Team t8 = new Team(8, "Broabant");
		curLeague.addTeam(t1);
		curLeague.addTeam(t2);
		curLeague.addTeam(t3);
		curLeague.addTeam(t4);
		curLeague.addTeam(t5);
		curLeague.addTeam(t6);
		curLeague.addTeam(t7);
		curLeague.addTeam(t8);
		
		game1.createMatchSchedule();
		
		assertEquals(game1.getMatches().size(), 56);
	}

}
