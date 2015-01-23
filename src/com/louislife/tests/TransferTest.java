package com.louislife.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.louislife.model.*;

public class TransferTest {

	@Test
	public void testTransfer() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		Transfer transfer2 = new Transfer(1, 1, 2, 1, 100, 1);
		Match match1 = new Match(0, 0, 0, 1);
		
		assertEquals(transfer1, transfer2);
		assertNotEquals(transfer1, match1);
	}

	@Test
	public void testGetId() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		
		assertEquals(1, transfer1.getId());
	}

	@Test
	public void testGetFrom() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		
		assertEquals(1, transfer1.getFrom());
	}

	@Test
	public void testGetTo() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		
		assertEquals(2, transfer1.getTo());
	}

	@Test
	public void testGetPlayer() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		
		assertEquals(1, transfer1.getPlayer());
	}

	@Test
	public void testGetPrice() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		
		assertEquals(100, transfer1.getPrice());
	}

	@Test
	public void testGetDay() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		
		assertEquals(1, transfer1.getDay());
	}

	@Test
	public void testToString() {
		Transfer transfer1 = new Transfer(1, 1, 2, 1, 100, 1);
		
		assertEquals("Transfer [id=1, from=1, to=2, player=1, price=100, day=1]", transfer1.toString());
	}

}
