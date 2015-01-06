package com.louislife.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import com.louislife.model.*;

import org.junit.Test;

public class MatchTest {

	@Test
	public void testMatch() {
		Match match1 = new Match(1, 1, 1, 2);
		Match match2 = new Match(1, 1, 1, 2);
		
		assertTrue(match1.equals(match2));
	}

	@Test
	public void testAddEventHome() {
		Match match1 = new Match(1, 1, 1, 2);
		Event event1 = new Event(1, EventType.INJURY, 30);
		ArrayList<Event> events_home = new ArrayList<Event>();
		events_home.add(event1);
		
		match1.addEventHome(event1);
		
		assertEquals(match1.getEvents_home(), events_home);
	}

	@Test
	public void testAddEventAway() {
		Match match1 = new Match(1, 1, 1, 2);
		Event event1 = new Event(1, EventType.INJURY, 30);
		ArrayList<Event> events_away = new ArrayList<Event>();
		events_away.add(event1);
		
		match1.addEventAway(event1);
		
		assertEquals(match1.getEvents_away(), events_away);
	}

	@Test
	public void testGetId() {
		Match match1 = new Match(1, 1, 1, 2);
		
		assertEquals(1, match1.getId());
	}

	@Test
	public void testGetDay() {
		Match match1 = new Match(1, 1, 1, 2);
		
		assertEquals(1, match1.getDay());
	}

	@Test
	public void testGetEvents_home() {
		Match match1 = new Match(1, 1, 1, 2);
		Event event1 = new Event(1, EventType.INJURY, 30);
		ArrayList<Event> events_home = new ArrayList<Event>();
		events_home.add(event1);
		
		match1.addEventHome(event1);
		
		assertEquals(match1.getEvents_home(), events_home);
	}

	@Test
	public void testGetEvents_away() {
		Match match1 = new Match(1, 1, 1, 2);
		Event event1 = new Event(1, EventType.INJURY, 30);
		ArrayList<Event> events_away = new ArrayList<Event>();
		events_away.add(event1);
		
		match1.addEventAway(event1);
		
		assertEquals(match1.getEvents_away(), events_away);
	}

	@Test
	public void testGetTeam_home() {
		Match match1 = new Match(1, 1, 1, 2);
		
		assertEquals(1, match1.getTeam_home());
	}

	@Test
	public void testGetTeam_away() {
		Match match1 = new Match(1, 1, 1, 2);
		
		assertEquals(2, match1.getTeam_away());
	}

	@Test
	public void testSetTeam_home() {
		Match match1 = new Match(1, 1, 1, 2);
		match1.setTeam_home(10);
		
		assertEquals(10, match1.getTeam_home());
	}

	@Test
	public void testSetTeam_away() {
		Match match1 = new Match(1, 1, 1, 2);
		match1.setTeam_away(20);
		
		assertEquals(20, match1.getTeam_away());
	}

	@Test
	public void testToString() {
		Match match1 = new Match(1, 1, 1, 2);
		assertEquals("Match [id=1, day=1, events_home=[], events_away=[], team_home=1, team_away=2]", match1.toString());
	}

}
