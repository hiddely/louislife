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
	
	@Test
	public void testEquals() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		ArrayList<Player> players1 = new ArrayList<Player>();
		players1.add(player1);
		Team team1 = new Team(1, "United States", 10000, players1);
		Team team2 = new Team(1, "United States", 10000, players1);
		
		assertTrue(team1.equals(team2));
	}
	
	@Test
	public void testGetTotOff() {
		byte t = 80;
		byte jn = 32;
		byte z = 60;
		byte v = 75;
		Player pl1 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl2 = new Player(2, "Piet", "Niet", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl3 = new Player(3, "Jan", "Hoog", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl4 = new Player(4, "Jan", "Laag", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl5 = new Player(5, "Jan", "Links", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl6 = new Player(6, "Jan", "Rechts", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl7 = new Player(7, "Jan", "Voor", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl8 = new Player(8, "Jan", "Achter", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl9 = new Player(9, "Jan", "Beneden", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl10 = new Player(10, "Jan", "Boven", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl11 = new Player(11, "Jan", "Midden", jn, PlayerType.GOALKEEPER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		
		ArrayList<Player> pll1 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		pll1.add(pl10);
		pll1.add(pl11);
		
		ArrayList<Player> pll2 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		
		Team tm1 = new Team(1, "Ajax", 50000, pll1);
		Team tm2 = new Team(1, "PSV", 50000, pll2);

		assertEquals(tm1.getTotOff(), 408);
		assertEquals(tm2.getTotOff(), -1);
	}
	
	@Test
	public void testGetTotDef() {
		byte t = 80;
		byte jn = 32;
		byte z = 60;
		byte v = 75;
		Player pl1 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl2 = new Player(2, "Piet", "Niet", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl3 = new Player(3, "Jan", "Hoog", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl4 = new Player(4, "Jan", "Laag", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl5 = new Player(5, "Jan", "Links", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl6 = new Player(6, "Jan", "Rechts", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl7 = new Player(7, "Jan", "Voor", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl8 = new Player(8, "Jan", "Achter", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl9 = new Player(9, "Jan", "Beneden", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl10 = new Player(10, "Jan", "Boven", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl11 = new Player(11, "Jan", "Midden", jn, PlayerType.GOALKEEPER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		
		ArrayList<Player> pll1 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		pll1.add(pl10);
		pll1.add(pl11);
		
		ArrayList<Player> pll2 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		
		Team tm1 = new Team(1, "Ajax", 50000, pll1);
		Team tm2 = new Team(1, "PSV", 50000, pll2);

		assertEquals(tm1.getTotDef(), 568);
		assertEquals(tm2.getTotDef(), -1);
	}
	
	@Test
	public void testGetTotStamina(){
		byte t = 80;
		byte jn = 32;
		byte z = 60;
		byte v = 75;
		Player pl1 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl2 = new Player(2, "Piet", "Niet", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl3 = new Player(3, "Jan", "Hoog", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl4 = new Player(4, "Jan", "Laag", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl5 = new Player(5, "Jan", "Links", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl6 = new Player(6, "Jan", "Rechts", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl7 = new Player(7, "Jan", "Voor", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl8 = new Player(8, "Jan", "Achter", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl9 = new Player(9, "Jan", "Beneden", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl10 = new Player(10, "Jan", "Boven", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl11 = new Player(11, "Jan", "Midden", jn, PlayerType.GOALKEEPER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl12 = new Player(11, "Jan", "NietSpeel", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl13 = new Player(11, "Jan", "Bank", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, z, t, v, 0, 500);

		ArrayList<Player> pll1 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		pll1.add(pl10);
		pll1.add(pl11);
		pll1.add(pl12);
		pll1.add(pl13);
		
		ArrayList<Player> pll2 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		
		Team tm1 = new Team(1, "Ajax", 50000, pll1);
		Team tm2 = new Team(1, "PSV", 50000, pll2);
		
		assertEquals(tm1.getTotStamina(), 825);
		assertEquals(tm2.getTotStamina(), -1);
	}
	
	@Test
	public void testGetScoreList(){
		byte t = 80;
		byte jn = 32;
		byte z = 60;
		byte v = 75;
		Player pl1 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl2 = new Player(2, "Piet", "Niet", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl3 = new Player(3, "Jan", "Hoog", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		Player pl4 = new Player(4, "Jan", "Laag", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl5 = new Player(5, "Jan", "Links", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl6 = new Player(6, "Jan", "Rechts", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 0, 500);
		Player pl7 = new Player(7, "Jan", "Voor", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl8 = new Player(8, "Jan", "Achter", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl9 = new Player(9, "Jan", "Beneden", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl10 = new Player(10, "Jan", "Boven", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl11 = new Player(11, "Jan", "Midden", jn, PlayerType.GOALKEEPER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl12 = new Player(11, "Jan", "NietSpeel", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, z, t, v, 0, 500);
		Player pl13 = new Player(11, "Jan", "Bank", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, z, t, v, 0, 500);

		ArrayList<Player> pll1 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		pll1.add(pl10);
		pll1.add(pl11);
		pll1.add(pl12);
		pll1.add(pl13);
		
		ArrayList<Player> pll2 = new ArrayList<Player>();
		pll1.add(pl1);
		pll1.add(pl2);
		pll1.add(pl3);
		pll1.add(pl4);
		pll1.add(pl5);
		pll1.add(pl6);
		pll1.add(pl7);
		pll1.add(pl8);
		pll1.add(pl9);
		
		Team tm1 = new Team(1, "Ajax", 50000, pll1);
		Team tm2 = new Team(1, "PSV", 50000, pll2);
		
		ArrayList<Player> res1 = new ArrayList<Player>();
		res1.add(pl1);
		res1.add(pl1);
		res1.add(pl2);
		res1.add(pl2);
		res1.add(pl3);
		res1.add(pl3);
		res1.add(pl4);
		res1.add(pl5);
		res1.add(pl6);
		
		assertEquals(tm1.getScoreList(), res1);
		assertEquals(tm2.getScoreList(), null);
		
		
	}

}
