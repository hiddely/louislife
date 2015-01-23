package com.louislife.tests;
import com.louislife.model.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void testPlayerExists() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
	
		assertNotNull(player1);
	}
	
	@Test
	public void testEquals() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		byte b2 = 10;
		Player player2 = new Player(1, "Elgar", "Weijtmans", b2, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b2, b2, 1, 100);
	
		assertEquals(player1,player2);
	}
	
	@Test
	public void testNotEquals() {
		byte b1 = 10;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 1, 100);
		byte b2 = 10;
		byte b3 = 34;
		Player player2 = new Player(1, "Elgar", "Weijtmans", b3, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b2, b2, 1, 100);
	
		assertNotEquals(player1,player2);
	}
	
	@Test
	public void testGetId() {
		byte b1 = 10;
		byte b2 = 24;
		byte b3 = 56;
		byte b4 = 30;
		Player player1 = new Player(1, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b3, b4, 1, 100);
		Player player2 = new Player(3, "Elgar", "Weijtmans", b3, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b3, b1, 1, 100);
		assertEquals(player1.getId(), 1);
		assertNotEquals(player2.getId(), 1);
	}
	
	@Test
	public void testSetId() {
		byte b1 = 10;
		byte b2 = 45;
		byte b3 = 22;
		Player player1 = new Player(3, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b3, b2, 1, 100);
		assertEquals(player1.getId(), 3);
		
		player1.setId(55);
		assertEquals(player1.getId(), 55);
	}

	@Test
	public void testGetFirstname() {
		byte b1 = 34;
		byte b2 = 85;
		Player player1 = new Player(3, "Elgar", "Weijtmans", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b1, b2, 1, 100);
		Player player2 = new Player(3, "Wouter", "Wumbo", b2, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b1, b1, 1, 100);
		assertEquals(player1.getFirstname(), "Elgar");
		assertNotEquals(player2.getFirstname(), "Henk");
	}
	
	@Test
	public void testsetFirstname() {
		byte b1 = 45;
		byte b2 = 33;
		Player player1 = new Player(3, "Wouter", "Smit", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b2, b2, b1, 1, 100);
		assertEquals(player1.getFirstname(), "Wouter");
		player1.setFirstname("Pieter");
		assertEquals(player1.getFirstname(), "Pieter");
	}

	@Test
	public void testGetSurname() {
		byte b1 = 34;
		Player player1 = new Player(35, "Piet", "Snot", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 30, 3000);
		Player player2 = new Player(35, "Piet", "Gappie", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 30, 3000);
		assertEquals(player1.getSurname(), "Snot");
		assertNotEquals(player2.getSurname(), "Snot");
	}

	@Test
	public void testSetSurname() {
		byte b1 = 32;
		Player player1 = new Player(34, "Hans", "Yan", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b1, b1, b1, 50, 302020);
		assertEquals(player1.getSurname(), "Yan");
		player1.setSurname("Yin");
		assertEquals(player1.getSurname(), "Yin");
	}
	
	@Test
	public void testGetJerseyNumber() {
		byte b1 = 35;
		byte b2 = 60;
		byte b3 = 2;
		Player player1 = new Player(1, "Piet", "Snot", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b3, b3, b3, 50, 302020);
		Player player2 = new Player(1, "Piet", "Snot", b2, PlayerType.STRIKER, PlayerStatus.NORMAL, b3, b3, b3, 50, 302020);
		assertEquals(player1.getJerseyNumber(), b1);
		assertNotEquals(player2.getJerseyNumber(), b1);
	}
	
	@Test
	public void testSetJerseyNumber() {
		byte b1 = 34;
		byte b2 = 10;
		byte b3 = 15;
		Player player1 = new Player(1, "Piet", "Snot", b1, PlayerType.STRIKER, PlayerStatus.NORMAL, b3, b3, b3, 50, 302020);
		assertEquals(player1.getJerseyNumber(), b1);
		player1.setJerseyNumber(b2);
		assertEquals(player1.getJerseyNumber(), b2);		
	}
/*
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
*/
}
