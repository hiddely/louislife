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
		Match match8 = new Match(1, 0, 1);
		Match match9 = new Match(1, 0, 1);
		Match match10 = new Match(0, 1);
		Match match11 = new Match(0, 1);
		Event event1 = new Event(1, EventType.GOAL, 1);
		Event event2 = new Event(2, EventType.GOAL, 2);
		Event event3 = new Event(3, EventType.GOAL, 3);
		
		match4.addEventAway(event1);
		match4.addEventHome(event2);
		match5.addEventAway(event1);
		match5.addEventHome(event2);
		match6.addEventAway(event1);
		match6.addEventHome(event3);
		match7.addEventHome(event2);
		match7.addEventAway(event3);
		
		assertEquals(match1, match2);
		assertNotEquals(match1, match3);
		assertEquals(match4, match5);
		assertNotEquals(match4, match6);
		assertNotEquals(match4, match7);
		assertNotEquals(match1, event1);
		assertEquals(match8, match9);
		assertEquals(match10, match11);
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
		Match m3 = new Match(1, 0, 3, 2);
		game.addMatch(m1);
		game.addMatch(m2);
		game.addMatch(m3);
		
		m1.play(55403);
		m2.play(348463654);
		m3.play(48681);
		
		Match res1 = new Match(0, 0, 0, 1);
		Event e1 = new Event(1, EventType.YELLOWCARD, 3);
		Event e2 = new Event(1, EventType.INJURY, 34);
		Event e3 = new Event(2, EventType.GOAL, 64);
		Event e4 = new Event(5, EventType.GOAL, 76);
		Event e5 = new Event(6, EventType.INJURY, 76);
		
		Event e6 = new Event(23, EventType.GOAL, 15);
		Event e7 = new Event(21, EventType.INJURY, 27);
		Event e8 = new Event(23, EventType.GOAL, 39);
		Event e9 = new Event(22, EventType.GOAL, 94);

		
		Match res2 = new Match(1, 0, 2, 3);
		Event e10 = new Event(2, EventType.GOAL, 15);
		Event e11 = new Event(5, EventType.GOAL, 27);
		Event e12 = new Event(3, EventType.GOAL, 41);
		Event e13 = new Event(6, EventType.GOAL, 43);
		Event e14 = new Event(3, EventType.GOAL, 63);
		Event e15 = new Event(1, EventType.GOAL, 67);
		Event e16 = new Event(6, EventType.GOAL, 84);
		
		Match res3 = new Match(1, 0, 3, 2);
		Event e17 = new Event(2, EventType.YELLOWCARD, 76);
		
		Event e18 = new Event(3, EventType.GOAL, 19);
		Event e19 = new Event(3, EventType.GOAL, 31);
		Event e20 = new Event(3, EventType.GOAL, 39);
		Event e21 = new Event(1, EventType.YELLOWCARD, 47);
		Event e22 = new Event(2, EventType.GOAL, 65);
		Event e23 = new Event(2, EventType.GOAL, 78);
		Event e24 = new Event(1, EventType.GOAL, 84);


		res1.addEventHome(e1);
		res1.addEventHome(e2);
		res1.addEventHome(e3);
		res1.addEventHome(e4);
		res1.addEventHome(e5);
		res1.addEventAway(e6);
		res1.addEventAway(e7);
		res1.addEventAway(e8);
		res1.addEventAway(e9);
		
		res2.addEventHome(e10);
		res2.addEventHome(e11);
		res2.addEventHome(e12);
		res2.addEventHome(e13);
		res2.addEventHome(e14);
		res2.addEventHome(e15);
		res2.addEventHome(e16);
		
		res3.addEventHome(e17);
		res3.addEventAway(e18);
		res3.addEventAway(e19);
		res3.addEventAway(e20);
		res3.addEventAway(e21);
		res3.addEventAway(e22);
		res3.addEventAway(e23);
		res3.addEventAway(e24);
		
		assertEquals(m1, res1);
		assertEquals(m2, res2);
		assertEquals(m3, res3);
		
	}
	
	@Test
	public void testToString() {
		Match match1 = new Match(1, 1, 1, 2);
		assertEquals("Match [id=1, day=1, events_home=[], events_away=[], team_home=1, team_away=2]", match1.toString());
	}
	
	@Test
	public void testGetTH(){
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
		
		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 820, pll2);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		Match m1 = new Match(0, 0, 0, 1);
		game.addMatch(m1);
		
		assertEquals(m1.getTH(), t1);
	}
	
	@Test
	public void testGetTA(){
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
		
		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 820, pll2);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		Match m1 = new Match(0, 0, 0, 1);
		game.addMatch(m1);
		
		assertEquals(m1.getTA(), t2);
	}

	@Test
	public void testSetDay(){
		Match m1 = new Match(0, 0, 0, 1);
		Match m2 = new Match(0, 50, 0 ,1);
		m1.setDay(50);
		assertEquals(m1, m2);
	}
	
	@Test
	public void testGetScore_home(){
		Match m1 = new Match(0, 0, 0, 1);
		Event e1 = new Event(1, EventType.GOAL, 1);
		Event e2 = new Event(2, EventType.GOAL, 2);
		Event e3 = new Event(3, EventType.GOAL, 3);
		Event e4 = new Event(4, EventType.YELLOWCARD, 5);
		
		m1.addEventHome(e1);
		m1.addEventHome(e2);
		m1.addEventAway(e3);
		m1.addEventHome(e4);
		
		assertEquals(2, m1.getScore_home());
	}
	
	@Test
	public void testGetScore_away(){
		Match m1 = new Match(0, 0, 0, 1);
		Event e1 = new Event(1, EventType.GOAL, 1);
		Event e2 = new Event(2, EventType.GOAL, 2);
		Event e3 = new Event(3, EventType.GOAL, 3);
		Event e4 = new Event(4, EventType.YELLOWCARD, 5);
		
		m1.addEventHome(e1);
		m1.addEventHome(e2);
		m1.addEventAway(e3);
		m1.addEventAway(e4);
		
		assertEquals(1, m1.getScore_away());
	}

	@Test
	public void testGetWinningTeam(){
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
		
		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 820, pll2);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		Match m1 = new Match(0, 0, 0, 1);
		Match m2 = new Match(1, 1, 0, 1);
		Match m3 = new Match(1, 1, 0, 1);
		Event e1 = new Event(1, EventType.GOAL, 1);
		Event e2 = new Event(2, EventType.GOAL, 2);
		Event e3 = new Event(3, EventType.GOAL, 3);
		m1.addEventHome(e1);
		m1.addEventHome(e2);
		m1.addEventAway(e3);
		m2.addEventAway(e1);
		m2.addEventAway(e2);
		m2.addEventAway(e3);
		m3.addEventAway(e1);
		m3.addEventHome(e2);
		game.addMatch(m1);
		
		assertEquals(m1.getWinningTeam(), t1);
		assertEquals(m2.getWinningTeam(), t2);
		assertEquals(m3.getWinningTeam(), null);
		
	}
	
	@Test
	public void testGetLosingTeam(){
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
		
		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 820, pll2);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		Match m1 = new Match(0, 0, 0, 1);
		Match m2 = new Match(1, 1, 0, 1);
		Match m3 = new Match(1, 1, 0, 1);
		Event e1 = new Event(1, EventType.GOAL, 1);
		Event e2 = new Event(2, EventType.GOAL, 2);
		Event e3 = new Event(3, EventType.GOAL, 3);
		m1.addEventHome(e1);
		m1.addEventHome(e2);
		m1.addEventAway(e3);
		m2.addEventAway(e1);
		m2.addEventAway(e2);
		m2.addEventAway(e3);
		m3.addEventAway(e1);
		m3.addEventHome(e2);
		game.addMatch(m1);
		
		assertEquals(m1.getLosingTeam(), t2);
		assertEquals(m2.getLosingTeam(), t1);
		assertEquals(m3.getLosingTeam(), null);
		
	}
	
	@Test
	public void testIsPlayed(){
		Game game = new Game(0, "Testgame", 0 , 0);
		game.setCurrentDay(24);
		Match m1 = new Match(0, 2, 0, 1);
		Match m2 = new Match(1, 28, 0, 1);
		
		assertTrue(m1.isPlayed());
		assertFalse(m2.isPlayed());
		
	}
	
	@Test 
	public void testGetScore() throws TeamNotFoundException {
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
		
		Team t1 = new Team(0, "AJAX", 800, pll1);
		Team t2 = new Team(1, "PSV", 820, pll2);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		game.setCurrentDay(24);
		
		Match m1 = new Match(0, 2, 0, 1);
		Match m2 = new Match(2, 2, 0, 1);
		Match m3 = new Match(3, 28, 0, 1);
		Event e1 = new Event(1, EventType.GOAL, 25);
		Event e2 = new Event(21, EventType.GOAL, 34);
		
		m1.addEventHome(e1);
		m2.addEventAway(e2);
		m2.addEventHome(e1);
		
		assertEquals(3, m1.getScore(t1));
		assertEquals(0, m1.getScore(t2));
		assertEquals(1, m2.getScore(t1));
		assertEquals(1, m2.getScore(t2));
		assertEquals(0, m3.getScore(t1));
		
	}
	
	@Test
	public void testCalculateHomeCredit(){
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
		
		Team t1 = new Team(0, "AJAX", 1000000, pll1);
		Team t2 = new Team(1, "PSV", 1000000, pll2);
		
		League l1 = new League(0, "EREDIVISIE", "NEDERLAND");
		l1.addTeam(t1);
		l1.addTeam(t2);
		Game game = new Game(0, "Testgame", 0 , 0);
		game.addLeague(l1);
		game.setCurrentDay(24);
		
		Match m1 = new Match(0, 0, 0, 1);
		Match m2 = new Match(1, 2, 1, 0);
		Match m3 = new Match(2, 4, 1, 0);
		
		Event e1 = new Event(1, EventType.GOAL, 23);
		Event e2 = new Event(21, EventType.YELLOWCARD, 34);
		Event e3 = new Event(21, EventType.REDCARD, 35);
		Event e4 = new Event(21, EventType.GOAL, 67);
		
		m1.addEventHome(e1);
		m1.addEventAway(e2);
		m1.addEventAway(e3);
		m2.addEventHome(e1);
		m2.addEventAway(e4);
		m3.addEventAway(e1);
		m3.addEventHome(e2);
		m3.addEventHome(e3);
		
		m1.calculateAwayCredit();
		m1.calculateHomeCredit();
		
		assertEquals(1600000, t1.getBalance());
		assertEquals(400000, t2.getBalance());
		
		t1.setBalance(1000000);
		t2.setBalance(1000000);
		m2.calculateAwayCredit();
		m2.calculateHomeCredit();
		
		assertEquals(1350000, t1.getBalance());
		assertEquals(1350000, t2.getBalance());
		
		t1.setBalance(1000000);
		t2.setBalance(1000000);
		m3.calculateAwayCredit();
		m3.calculateHomeCredit();
		
		assertEquals(400000, t2.getBalance());
		assertEquals(1600000, t1.getBalance());
	}
	
}
