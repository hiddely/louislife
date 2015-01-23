package com.louislife.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import com.louislife.model.*;

public class EventTest {

	@Test
	public void testEvent() {
		Event event1 = new Event(1, EventType.INJURY, 30);
		Event event2 = new Event(1, EventType.INJURY, 30);
		Match match1 = new Match(1,0,0,1);
		
		assertEquals(event1, event2);
		assertNotEquals(event1, match1);
	}

	@Test
	public void testGetPlayer() {
		Event event1 = new Event(1, EventType.INJURY, 30);
		
		assertEquals(1, event1.getPlayer());
	}

	@Test
	public void testGetType() {
		Event event1 = new Event(1, EventType.INJURY, 30);
		
		assertEquals(EventType.INJURY, event1.getType());
	}

	@Test
	public void testGetMinute() {
		Event event1 = new Event(1, EventType.INJURY, 30);
		
		assertEquals(30, event1.getMinute());
	}
	
	@Test
	public void testToString() {
		Event event1 = new Event(1, EventType.GOAL, 89);
		String res1 = "Event [player=1, type=GOAL, minute=89]";
		assertEquals(event1.toString(), res1);
	}

}
