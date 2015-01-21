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
		Match match3 = new Match(1, 2, 1, 2);
		Match match4 = new Match(2, 3, 1, 4);
		Match match5 = new Match(2, 3, 1, 4);
		Match match6 = new Match(2, 3, 1, 4);
		Match match7 = new Match(2, 3, 1, 4);
		Event event1 = new Event(1, EventType.GOAL, 1);
		Event event2 = new Event(2, EventType.GOAL, 2);
		Event event3 = new Event(3, EventType.GOAL, 3);
		
		match4.addEventAway(event1);
		match4.addEventHome(event2);
		match5.addEventAway(event1);
		match5.addEventHome(event2);
		match6.addEventAway(event1);
		match6.addEventHome(event3);
		match7.addEventAway(event1);
		match7.addEventHome(event2);
		match7.addEventAway(event3);
		
		assertTrue(match1.equals(match2));
		assertFalse(match1.equals(match3));
		assertTrue(match4.equals(match5));
		assertFalse(match4.equals(match6));
		assertFalse(match4.equals(match7));
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
	public void testPlay(){
		byte t = 80;
		byte jn = 32;
		byte z = 60;
		byte v = 75;
		byte min = 1;
		byte max = 99;
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
		
		Player pl21 = new Player(21, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 1, 500);
		Player pl22 = new Player(22, "Piet", "Niet", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 1, 500);
		Player pl23 = new Player(23, "Jan", "Hoog", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, t, z, v, 1, 500);
		Player pl24 = new Player(24, "Jan", "Laag", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 1, 500);
		Player pl25 = new Player(25, "Jan", "Links", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 1, 500);
		Player pl26 = new Player(26, "Jan", "Rechts", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, v, v, v, 1, 500);
		Player pl27 = new Player(27, "Jan", "Voor", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 1, 500);
		Player pl28 = new Player(28, "Jan", "Achter", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 1, 500);
		Player pl29 = new Player(29, "Jan", "Beneden", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 1, 500);
		Player pl30 = new Player(30, "Jan", "Boven", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, z, t, v, 1, 500);
		Player pl31 = new Player(31, "Jan", "Midden", jn, PlayerType.GOALKEEPER, PlayerStatus.NORMAL, z, t, v, 1, 500);
		ArrayList<Player> pll2 = new ArrayList<Player>();
		pll2.add(pl21);
		pll2.add(pl22);
		pll2.add(pl23);
		pll2.add(pl24);
		pll2.add(pl25);
		pll2.add(pl26);
		pll2.add(pl27);
		pll2.add(pl28);
		pll2.add(pl29);
		pll2.add(pl30);
		pll2.add(pl31);
		
		Player pl32 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl33 = new Player(2, "Piet", "Niet", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl34 = new Player(3, "Jan", "Hoog", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl35 = new Player(4, "Jan", "Laag", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl36 = new Player(5, "Jan", "Links", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl37 = new Player(6, "Jan", "Rechts", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl38 = new Player(7, "Jan", "Voor", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl39 = new Player(8, "Jan", "Achter", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl40 = new Player(9, "Jan", "Beneden", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl41 = new Player(10, "Jan", "Boven", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		Player pl42 = new Player(11, "Jan", "Midden", jn, PlayerType.GOALKEEPER, PlayerStatus.NORMAL, max, max, v, 2, 500);
		ArrayList<Player> pll3 = new ArrayList<Player>();
		pll3.add(pl32);
		pll3.add(pl33);
		pll3.add(pl34);
		pll3.add(pl35);
		pll3.add(pl36);
		pll3.add(pl37);
		pll3.add(pl38);
		pll3.add(pl39);
		pll3.add(pl40);
		pll3.add(pl41);
		pll3.add(pl42);
		
		Player pl43 = new Player(1, "Jan", "Pan", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl44 = new Player(2, "Piet", "Niet", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl45 = new Player(3, "Jan", "Hoog", jn, PlayerType.STRIKER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl46 = new Player(4, "Jan", "Laag", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl47 = new Player(5, "Jan", "Links", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl48 = new Player(6, "Jan", "Rechts", jn, PlayerType.MIDFIELDER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl49 = new Player(7, "Jan", "Voor", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl50 = new Player(8, "Jan", "Achter", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl51 = new Player(9, "Jan", "Beneden", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl52 = new Player(10, "Jan", "Boven", jn, PlayerType.DEFENDER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		Player pl53 = new Player(11, "Jan", "Midden", jn, PlayerType.GOALKEEPER, PlayerStatus.NORMAL, min, min, v, 2, 500);
		ArrayList<Player> pll4 = new ArrayList<Player>();
		pll4.add(pl43);
		pll4.add(pl44);
		pll4.add(pl45);
		pll4.add(pl46);
		pll4.add(pl47);
		pll4.add(pl48);
		pll4.add(pl49);
		pll4.add(pl50);
		pll4.add(pl51);
		pll4.add(pl52);
		pll4.add(pl53);

		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 820, pll2);
		Team t3 = new Team(2, "Max", 500, pll3);
		Team t4 = new Team(3, "Min", 500, pll4);
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		l1.addTeam(t3);
		l1.addTeam(t4);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		Match m1 = new Match(0, 0, 0, 1);
		Match m2 = new Match(1, 0, 2, 3);
		game.addMatch(m1);
		game.addMatch(m2);
		
		m1.play(600);
		m2.play(714);
		
		Match res1 = new Match(0, 0, 0, 1);
		Event e1 = new Event(5, EventType.GOAL, 83);
		Event e2 = new Event(21, EventType.GOAL, 45);
		res1.addEventHome(e1);
		res1.addEventHome(e2);
		
		assertEquals(m1, res1);
		
	}
	
	@Test
	public void testToString() {
		Match match1 = new Match(1, 1, 1, 2);
		assertEquals("Match [id=1, day=1, events_home=[], events_away=[], team_home=1, team_away=2]", match1.toString());
	}


}
