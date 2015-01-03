package com.louislife.tests;
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
		
		//AssertEquals failed
		assertTrue(player1.equals(player2));
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
		fail("Not yet implemented");
	}

	@Test
	public void testSetFirstname() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSurname() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSurname() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJerseyNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetJerseyNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOffensiveScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetOffensiveScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDefensiveScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDefensiveScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStaminaScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStaminaScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTeamId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTeamId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrice() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPrice() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsPlayer() {
		fail("Not yet implemented");
	}

}
