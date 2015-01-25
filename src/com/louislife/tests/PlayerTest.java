package com.louislife.tests;
import java.util.ArrayList;

import com.louislife.model.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void testPlayer() {
		//New player1
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		//New player2
		byte b2 = 10;
		Player player2 = new Player(1, "Elgar", "Weijtmans", b2, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b2, b2, 1, 100);
		Match m1 = new Match(0, 0, 0, 1);
	
		assertEquals(player1, player2);
		assertNotEquals(m1, player1);
	}

	@Test
	public void testGetId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getId(), 1);
	}

	@Test
	public void testSetId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setId(2);
		
		assertEquals(player1.getId(), 2);
	}

	@Test
	public void testGetFirstname() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getFirstname(),"Elgar");
	}

	@Test
	public void testSetFirstname() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setFirstname("Henk");
		
		assertEquals(player1.getFirstname(),"Henk");
	}

	@Test
	public void testGetSurname() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getSurname(),"Weijtmans");
	}

	@Test
	public void testSetSurname() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setSurname("Davids");
		
		assertEquals(player1.getSurname(),"Davids");
	}

	@Test
	public void testGetJerseyNumber() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getJerseyNumber(),10);
	}

	@Test
	public void testSetJerseyNumber() {
		byte b1 = 10;
		byte b2 = 12;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setJerseyNumber(b2);
		
		assertEquals(player1.getJerseyNumber(),12);
	}

	@Test
	public void testGetType() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getType(),PlayerType.STRIKER);
	}

	@Test
	public void testSetType() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setType(PlayerType.DEFENDER);
		
		assertEquals(player1.getType(),PlayerType.DEFENDER);
	}

	@Test
	public void testGetStatus() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getStatus(),PlayerStatus.NORMAL);
	}

	@Test
	public void testSetStatus() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setStatus(PlayerStatus.REDCARD);
		
		assertEquals(player1.getStatus(),PlayerStatus.REDCARD);
	}

	@Test
	public void testGetOffensiveScore() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getOffensiveScore(), 10);
	}

	@Test
	public void testSetOffensiveScore() {
		byte b1 = 10;
		byte b2 = 11;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setOffensiveScore(b2);
		
		assertEquals(player1.getOffensiveScore(), 11);
	}

	@Test
	public void testGetDefensiveScore() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getDefensiveScore(), 10);
	}

	@Test
	public void testSetDefensiveScore() {
		byte b1 = 10;
		byte b2 = 11;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setDefensiveScore(b2);
		
		assertEquals(player1.getDefensiveScore(), 11);
	}

	@Test
	public void testGetStaminaScore() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getStaminaScore(), 10);
	}

	@Test
	public void testSetStaminaScore() {
		byte b1 = 10;
		byte b2 = 11;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setStaminaScore(b2);
		
		assertEquals(player1.getStaminaScore(), 11);
	}

	@Test
	public void testGetTeamId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getTeamId(), 1);
	}

	@Test
	public void testSetTeamId() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setTeamId(2);
		
		assertEquals(player1.getTeamId(), 2);
	}

	@Test
	public void testGetPrice() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.getPrice(), 100);
	}

	@Test
	public void testSetPrice() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		player1.setPrice(200);
		
		assertEquals(player1.getPrice(), 200);
	}

	@Test
	public void testToString() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		
		assertEquals(player1.toString(), "Player [id=1, firstname=Elgar, surname=Weijtmans, jerseyNumber=10, type=STRIKER, offensiveRating=10, defensiveRating=10, staminaRating=10, teamId=1, price=100]");
	}

	@Test
	public void testEqualsPlayer() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		byte b2 = 10;
		Player player2 = new Player(1, "Elgar", "Weijtmans", b2, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b2, b2, 1, 100);

		assertTrue(player1.equals(player2));
	}

	@Test
	public void testTransferPlayer(){
		byte t = 80;
		byte jn = 32;
		byte z = 60;
		byte v = 75;
		
		Player pl1 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		ArrayList<Player> pll1 = new ArrayList<Player>();
		pll1.add(pl1);
		
		Player pl21 = new Player(21, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 1, 500);
		ArrayList<Player> pll2 = new ArrayList<Player>();
		pll2.add(pl21);
		
		ArrayList<Player> pll3 = new ArrayList<Player>();
		pll3.add(pl21);
		
		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 400, pll2);
		Team t3 = new Team(2, "Feijenoord", 850, pll3);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		l1.addTeam(t3);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		
		ArrayList<Player> res1 = new ArrayList<Player>();
		res1.add(pl21);
		
		ArrayList<Player> res2 = new ArrayList<Player>();
		res2.add(pl21);
		res2.add(pl1);
		
		ArrayList<Player> res3 = new ArrayList<Player>();
		
		pl1.transferPlayer(0, 0, 1);
		assertEquals(res1,t2.getPlayers());
		assertEquals(400, t2.getBalance());
		assertEquals(800, t1.getBalance());
		
		pl1.transferPlayer(0, 0, 2);
		assertEquals(res2, t3.getPlayers());
		assertEquals(res3, t1.getPlayers());
		assertEquals(1300, t1.getBalance());
		assertEquals(350, t3.getBalance());
	}
	
	@Test
	public void testSellPlayer(){
		byte t = 80;
		byte jn = 32;
		byte z = 60;
		byte v = 75;
		
		Player pl1 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 0, 500);
		ArrayList<Player> pll1 = new ArrayList<Player>();
		pll1.add(pl1);
		
		Player pl21 = new Player(21, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 1, 500);
		ArrayList<Player> pll2 = new ArrayList<Player>();
		pll2.add(pl21);
		
		ArrayList<Player> pll3 = new ArrayList<Player>();
		pll3.add(pl21);
		
		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 400, pll2);
		Team t3 = new Team(2, "Feijenoord", 1200, pll3);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		l1.addTeam(t3);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		
		ArrayList<Player> res1 = new ArrayList<Player>();
		res1.add(pl21);
		
		ArrayList<Player> res2 = new ArrayList<Player>();
		res2.add(pl21);
		res2.add(pl1);
		
		ArrayList<Player> res3 = new ArrayList<Player>();
		
		pl1.sellPlayer(0, 500);
		assertEquals(res1,t2.getPlayers());
		assertEquals(400, t2.getBalance());
		assertEquals(res2, t3.getPlayers());
		assertEquals(res3, t1.getPlayers());
		assertEquals(1300, t1.getBalance());
		assertEquals(700, t3.getBalance());
	}
}
