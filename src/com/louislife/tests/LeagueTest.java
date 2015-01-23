package com.louislife.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import com.louislife.model.*;

import org.junit.Test;

public class LeagueTest {

	@Test
	public void testLeague() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		Team team2 = new Team(2, "Nederland", 1000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		ArrayList<Team> teams2 = new ArrayList<Team>();
		teams2.add(team2);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		League league2 = new League(1, "Jupiler League", "Belgie", teams1);
		League league3 = new League(1, "Jupiler League", "Belgie", teams2);
		Match match1 = new Match(0, 0, 0 ,1);
		
		assertEquals(league1, league2);
		assertNotEquals(league1, match1);
		assertNotEquals(league1, league3);
	}

	@Test
	public void testGetId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		
		assertEquals(1, league1.getId());
	}

	@Test
	public void testSetId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.setId(2);
		
		assertEquals(2, league1.getId());
	}

	@Test
	public void testGetName() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		
		assertEquals("Jupiler League", league1.getName());
	}

	@Test
	public void testSetName() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.setName("Heineken League");
		
		assertEquals("Heineken League", league1.getName());
	}

	@Test
	public void testGetCountry() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		
		assertEquals("Belgie", league1.getCountry());
	}

	@Test
	public void testSetCountry() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.setCountry("Nederland");
		
		assertEquals("Nederland", league1.getCountry());
	}

	@Test
	public void testGetTeams() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.setCountry("Nederland");
		
		assertEquals(teams1, league1.getTeams());
	}

	@Test
	public void testSetTeams() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		Team team2 = new Team(2, "United Staids", 20000, players1);		
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		ArrayList<Team> teams2 = new ArrayList<Team>();
		teams1.add(team1);
		teams2.add(team1);
		teams2.add(team2);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.setTeams(teams2);
		
		assertEquals(teams2, league1.getTeams());
	}

	@Test
	public void testAddTeam() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		Team team2 = new Team(2, "United Staids", 20000, players1);		
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		ArrayList<Team> teams2 = new ArrayList<Team>();
		teams1.add(team1);
		teams2.add(team1);
		teams2.add(team2);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.addTeam(team2);
		
		assertEquals(teams2, league1.getTeams());
	}

	@Test
	public void testToString() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.setCountry("Nederland");
		
		assertEquals("League [id=1, name=Jupiler League, country=Nederland, teams=[Team [id=1, name=United States, players=[Player [id=1, firstname=Elgar, surname=Weijtmans, jerseyNumber=10, type=STRIKER, offensiveRating=10, defensiveRating=10, staminaRating=10, teamId=1, price=100]]]]]", league1.toString());
	}

	@Test
	public void testFindTeam() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		
		ArrayList<Team> teams1 = new ArrayList<Team>();
		teams1.add(team1);
		
		League league1 = new League(1, "Jupiler League", "Belgie", teams1);
		league1.setCountry("Nederland");
		
		assertEquals(team1, league1.findTeam(1));
	}

}
