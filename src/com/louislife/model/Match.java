package com.louislife.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
=======
	 * A match is played on the day that the overview screen shows before pressing the next round button.
	 * Thus, nextWeek() happens after creating the match object.
	 * @param id - int. Match ID.
	 * @param day - int. Day the match is played on.
	 * @param team_home - int. Team ID that played home.
	 * @param team_away - int. Team ID that played away.
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

	/***
	 * A match is played on the day that the overview screen shows before
	 * pressing the next round button. Thus, nextWeek() happens after creating
	 * the match object.
	 *
	 * Creates a new match without specifying the match day. The day should later be filled in with Match.setDay
	 * @param id -int. match ID.
	 * @param team_home - int. Team ID that played home.
	 * @param team_away - int. Team ID that played away.
	 */

	public Match(int id, int team_home, int team_away) {
		this.id = id;
		this.day = -1;
		
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
	public Team getTH() { return Game.getInstance().getLeagues().get(0).findTeam(team_home); }

	public int getTeam_away() {
		return team_away;
	}
	public Team getTA() { return Game.getInstance().getLeagues().get(0).findTeam(team_away); }

	public void setDay(int d) {
		this.day = d;
		
	}

	public void setTeam_home(int team_home) {
		this.team_home = team_home;
	}

	public void setTeam_away(int team_away) {
		this.team_away = team_away;
	}

	public int getScore_home() {
		int score = 0;
		for (Event e : events_home) {
			if (e.getType() == EventType.GOAL) {
				score++;
			}
		}
		return score;
	}

	public int getScore_away() {
		int score = 0;
		for (Event e : events_away) {
			if (e.getType() == EventType.GOAL) {
				score++;
			}
		}
		return score;
	}

	public Team getWinningTeam() {
		if (getScore_home()-getScore_away() < 0) {
			return getTA();
		} else if (getScore_home()-getScore_away() > 0) {
			return getTH();
		}
		return null;
	}

	public Team getLosingTeam() {
		if (getScore_home()-getScore_away() < 0) {
			return getTH();
		} else if (getScore_home()-getScore_away() > 0) {
			return getTA();
		}
		return null;
	}

	/**
	 * Returns the amount of points for a team
	 * @param t can be either team_home or team_away
	 * @return -1 (unknown),
	 */
	public int getScore(Team t) throws TeamNotFoundException {
		if (this.isPlayed() == false) {
			return 0; // If match is not played yet.
		}
		if (t.equals(getWinningTeam())) { // Team won
			return 3; // Won
		} else if (t.equals(getLosingTeam())) {
			return 0; // Lost
		} else if (getWinningTeam() == null && getLosingTeam() == null && (getTH().equals(t) || getTA().equals(t))) {
			return 1; // Equal
		}
		throw new TeamNotFoundException("Team not losing or winning this match");
	}

	public boolean isPlayed() {
		return this.day < Game.getInstance().getCurrentDay();
	}

	public void play(long seed) {
		Team home = Game.getInstance().getLeagues().get(0).findTeam(team_home);
		Team away = Game.getInstance().getLeagues().get(0).findTeam(team_away);
		Random r = new Random(seed);
		
		int homeChances = home.getTotStamina() / 110;
		int awayChances = away.getTotStamina() / 120;
		double homeGoalChance = 0.4 * (40 + ((home.getTotOff() * 2 - away.getTotDef()) * 100 / away.getTotDef()));
		double awayGoalChance = 0.4 * (40 + ((away.getTotOff() * 2 - home.getTotDef()) *100 / home.getTotDef()));
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
				int eventTime = awayTime - 45/homeChances + r.nextInt(90/homeChances);
				Player pl = sla.get(r.nextInt(sla.size()));
				Event e = new Event(pl.getId(), EventType.GOAL, eventTime);
				events_away.add(e);
			}
		}

		// We do not have a stat for aggressiveness, or something in that sense, so we'll divide yellow and red cards randomly
		for (int i = 1; i < 90; i++) { // i represents minute
			if (r.nextInt(100) == 2) {
				// We have a yellow card to give
				if (r.nextInt(2) == 1) {
					// Home
					Player pl = slh.get(r.nextInt(slh.size()));
					events_home.add(new Event(pl.getId(), EventType.YELLOWCARD, i));
				} else {
					Player pl = sla.get(r.nextInt(sla.size()));
					events_away.add(new Event(pl.getId(), EventType.YELLOWCARD, i));
				}
			}
			if (r.nextInt(180) == 2) {
				// Injury
				if (r.nextInt(2) == 1) {
					// Home
					Player pl = slh.get(r.nextInt(slh.size()));
					events_home.add(new Event(pl.getId(), EventType.INJURY, i));
				} else {
					Player pl = sla.get(r.nextInt(sla.size()));
					events_away.add(new Event(pl.getId(), EventType.INJURY, i));
				}
			}
			if (r.nextInt(200) == 2) {
				// Injury
				if (r.nextInt(2) == 1) {
					// Home
					Player pl = slh.get(r.nextInt(slh.size()));
					events_home.add(new Event(pl.getId(), EventType.REDCARD, i));
				} else {
					Player pl = sla.get(r.nextInt(sla.size()));
					events_away.add(new Event(pl.getId(), EventType.REDCARD, i));
				}
			}
		}
		Collections.sort(events_home, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return Integer.valueOf(o1.getMinute()).compareTo(Integer.valueOf(o2.getMinute()));
			}
		});
		Collections.sort(events_away, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return Integer.valueOf(o1.getMinute()).compareTo(Integer.valueOf(o2.getMinute()));
			}
		});
		
	}
	
	/**
	 * Calculates the money gained by the home team in this match.
	 * 
	 * @return credit - int. The complete amount of money that should be credited to the home team, including all events and win/tie.
	 * 
	 * @author Wouter
	 */
	public int calculateHomeCredit() {	
		int credit = 0;
		
		// Credit based on events
		for (int i = 0; i < events_home.size(); i++) {
			Event curEvent = events_home.get(i);
			
			if (curEvent.getType() == EventType.GOAL) {
				getTH().addToBalance(100000);
			}
			
			else if (curEvent.getType() == EventType.YELLOWCARD) {
				getTH().removeFromBalance(100000);
			}
			
			else if (curEvent.getType() == EventType.REDCARD) {
				getTH().removeFromBalance(500000);
			}
		}
		
		// Credit based on win / tie / loss
		if (this.getTH().equals(this.getWinningTeam())) {
			getTH().addToBalance(500000);
		}
		else if (this.getWinningTeam() == null) {
			getTH().addToBalance(250000);
		}
		
		return credit;
	}
	
	/**
	 * Calculates the money gained by the away team in this match.
	 * 
	 * @return credit - int. The complete amount of money that should be credited to the home team, including all events and win/tie.
	 * 
	 * @author Wouter
	 */
	public int calculateAwayCredit() {	
	int credit = 0;
		
		// Credit based on events
		for (int i = 0; i < events_away.size(); i++) {
			Event curEvent = events_away.get(i);
			
			if (curEvent.getType() == EventType.GOAL) {
				getTA().addToBalance(100000);
			}
			
			else if (curEvent.getType() == EventType.YELLOWCARD) {
				getTA().removeFromBalance(100000);
			}
			
			else if (curEvent.getType() == EventType.REDCARD) {
				getTA().removeFromBalance(500000);
			}
		}
		
		// Credit based on win / tie / loss
		if (this.getTA().equals(this.getWinningTeam())) {
			getTA().addToBalance(500000);
		}
		else if (this.getWinningTeam() == null) {
			getTA().addToBalance(250000);
		}
		
		return credit;
	}
	
	public String toString() {
		return "Match [id=" + id + ", day=" + day + ", events_home="
				+ events_home + ", events_away=" + events_away + ", team_home="
				+ team_home + ", team_away=" + team_away + "]";
	}
	
	
	public boolean equals(Object o){
		if(o instanceof Match){
			Match that = (Match) o;
			if(this.id == that.id
				&& this.day == that.day
				&& this.team_away == that.team_away
				&& this.team_home == that.team_home
				&& this.events_away.size() == that.events_away.size()
				&& this.events_home.size() == that.events_home.size()
				){
					for(int i = 0; i < this.events_away.size(); i++){
						if(this.events_away.get(i).equals(that.events_away.get(i)) == false){
							return false;
						}
					}
					for(int n = 0; n < this.events_home.size(); n++){
						if(this.events_home.get(n).equals(that.events_home.get(n)) == false){
							return false;
						}
					}
					return true;
			}
		}
		return false;
	}
	
	
}
