package com.louislife.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents match to be played or that is already played
 * 
 * @author hidde
 *
 */
public class Match {

	private int id;
	private int day;

	private ArrayList<Event> events_home;
	private ArrayList<Event> events_away;

	private int team_home;
	private int team_away;

	/**
	 * Creates a new match.
	 * 
	 * A match is played on the day that the overview screen shows before
	 * pressing the next round button. Thus, nextWeek() happens after creating
	 * the match object.
	 * 
	 * @param id
	 *            - int. Match ID
	 * @param day
	 *            - int. Day the match is played on
	 * @param team_home
	 *            - int. Team ID that played home
	 * @param team_away
	 *            - int. Team ID that played away
	 */
	public Match(int id, int day, int team_home, int team_away) {
		this.id = id;
		this.day = day;

		this.team_home = team_home;
		this.team_away = team_away;

		events_home = new ArrayList<Event>();
		events_away = new ArrayList<Event>();
		
		this.events_home = new ArrayList<Event>();
		this.events_away = new ArrayList<Event>();
	}

	/**
	 * Creates a new match without specifying the participating teams.
	 * 
	 * A match is played on the day that the overview screen shows before
	 * pressing the next round button. Thus, nextWeek() happens after creating
	 * the match object.
	 * 
	 * @param id
	 *            - int. Match ID
	 * @param day
	 *            - int. Day the match is played on.
	 */

	public Match(int id, int team_home, int team_away) {
		this.id = id;
		
		this.team_home = team_home;
		this.team_away = team_away;
				
		this.events_home = new ArrayList<Event>();
		this.events_away = new ArrayList<Event>();
	}
	
	public Match(int id, int day) {
		this.id = id;
		this.day = day;

		events_home = new ArrayList<Event>();
		events_away = new ArrayList<Event>();
		
		this.events_home = new ArrayList<Event>();
		this.events_away = new ArrayList<Event>();
	}

	public void addEventHome(Event e) {
		events_home.add(e);
	}

	public void addEventAway(Event e) {
		events_away.add(e);
	}

	public int getId() {
		return id;
	}

	public int getDay() {
		return day;
	}

	public ArrayList<Event> getEvents_home() {
		return events_home;
	}

	public ArrayList<Event> getEvents_away() {
		return events_away;
	}

	public int getTeam_home() {
		return team_home;
	}

	public int getTeam_away() {
		return team_away;
	}

	public void setDay(int d) {
		this.day = d;
		
	}

	public void setTeam_home(int team_home) {
		this.team_home = team_home;
	}

	public void setTeam_away(int team_away) {
		this.team_away = team_away;
	}

	public void play(long seed) {
		Team home = Game.getInstance().getLeagues().get(0).findTeam(team_home);
		Team away = Game.getInstance().getLeagues().get(0).findTeam(team_away);
		Random r = new Random(seed);
		
		int homeChances = home.getTotStamina() / 110;
		int awayChances = away.getTotStamina() / 120;
		double homeGoalChance = (50 + ((home.getTotOff() - away.getTotDef()) * 100 / away.getTotDef()));
		double awayGoalChance = (50 + ((away.getTotOff() - home.getTotDef()) *100 / home.getTotDef()));
		if(homeGoalChance < 5){
			homeGoalChance = 5;
		}else if(homeGoalChance > 90){
			homeGoalChance = 90;
		}
		if(awayGoalChance < 5){
			awayGoalChance = 5;
		}else if(awayGoalChance > 90){
			awayGoalChance = 90;
		}
		int homeTime = 0;
		int awayTime = 0;
		ArrayList<Player> slh = home.getScoreList();
		ArrayList<Player> sla = away.getScoreList();
		
		for(int i = 0; i < homeChances; i++){
			homeTime += 90/homeChances;
			int random = r.nextInt(100);
			if(random < homeGoalChance){
				int eventTime = homeTime - 45/homeChances + r.nextInt(90/homeChances);
				Player pl = slh.get(r.nextInt(slh.size()));
				Event e = new Event(pl.getId(), EventType.GOAL, eventTime);
				events_home.add(e);
			}
		}
		
		for(int i = 0; i < awayChances; i++){
			awayTime += 90/awayChances;
			int random = r.nextInt(100);
			if(random < awayGoalChance){
				Player pl = sla.get(r.nextInt(sla.size()));
				Event e = new Event(pl.getId(), EventType.GOAL, awayTime);
				events_home.add(e);
			}
		}
		
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", day=" + day + ", events_home="
				+ events_home + ", events_away=" + events_away + ", team_home="
				+ team_home + ", team_away=" + team_away + "]";
	}
	

	public boolean equals(Object o) {
		
		if(o instanceof Match){
			Match that = (Match) o;
			if (this.id == that.id && this.day == that.day && this.team_home == that.team_home && this.team_away == that.team_away) {
				if(this.events_home.size() == that.events_home.size() && this.events_away.size() == that.events_away.size()){
					for(int i = 0; i < this.events_home.size(); i++){
						if(this.events_home.get(i).equals(that.events_home.get(i)) == false){
							return false;
						}
					}
					for(int i = 0; i < this.events_away.size(); i++){
						if(this.events_away.get(i).equals(that.events_away.get(i)) == false){
							return false;
						}
					}
					return true;
				}
			}

		}
				return false;
	}
	
	public static void main(String[] args){
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
		System.out.println(m1.toString());
		System.out.println(m2.toString());
	}

}
