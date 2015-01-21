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
<<<<<<< HEAD
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
=======
	 * A match is played on the day that the overview screen shows before pressing the next round button.
	 * Thus, nextWeek() happens after creating the match object.
	 * @param id - int. Match ID.
	 * @param day - int. Day the match is played on.
	 * @param team_home - int. Team ID that played home.
	 * @param team_away - int. Team ID that played away.
>>>>>>> branch 'master' of https://github.com/hidde1000/louislife
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
<<<<<<< HEAD
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
=======
	 * Creates a new match without specifying the match day. The day should later be filled in with Match.setDay
	 * @param id -int. match ID.
	 * @param team_home - int. Team ID that played home.
	 * @param team_away - int. Team ID that played away.
>>>>>>> branch 'master' of https://github.com/hidde1000/louislife
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
		
		int homeChances = home.getTotStamina() / 120;
		int awayChances = away.getTotStamina() / 110;
		int homeGoalChance = 50 + ((home.getTotOff() - away.getTotDef()) / 2 * away.getTotDef());
		if(homeGoalChance < 5){
			homeGoalChance = 5;
		}else if(homeGoalChance > 90){
			homeGoalChance = 90;
		}
		int awayGoalChance = 50 + ((away.getTotOff() - home.getTotDef()) / 2 * home.getTotDef());
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
			if(random > homeGoalChance){
				int eventTime = homeTime - 45/homeChances + r.nextInt(90/homeChances);
				Player pl = slh.get(r.nextInt(slh.size()));
				Event e = new Event(pl.getId(), EventType.GOAL, eventTime);
				events_home.add(e);
			}
		}
		
		for(int i = 0; i < awayChances; i++){
			awayTime += 90/awayChances;
			int random = r.nextInt(100);
			if(random > awayGoalChance){
				Player pl = slh.get(r.nextInt(sla.size()));
				Event e = new Event(pl.getId(), EventType.GOAL, awayTime);
				events_home.add(e);
			}
		}
		
	}
	
	/**
	 * Calculates the money gained by the home team in this match.
	 * @return
	 */
	public int calculateHomeCredit() {	
		int credit = 0;
		for (int i = 0; i < events_home.size(); i++) {
			Event curEvent = events_home.get(i);
			
			if (curEvent.getType() == EventType.GOAL) {
				credit += 100000;
			}
			
			else if (curEvent.getType() == EventType.YELLOWCARD) {
				credit -= 100000;
			}
			
			else if (curEvent.getType() == EventType.REDCARD) {
				credit -= 500000;
			}
		}
		
		if () {
			credit += 500000;
		}
		else if () {
			credit += 250000;
		}
		
		return credit;
	}
	
	@Override
	public String toString() {
		return "Match [id=" + id + ", day=" + day + ", events_home="
				+ events_home + ", events_away=" + events_away + ", team_home="
				+ team_home + ", team_away=" + team_away + "]";
	}
	

}
