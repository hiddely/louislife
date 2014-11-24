package com.louislife.model;

import java.util.ArrayList;

/**
 * Represents a Game object with leagues and played matches. This holds all the
 * game data necessary for one instance of the game to function.
 * 
 * @author hidde
 *
 */
public class Game {

	private int id;
	private String name;
	private ArrayList<League> leagues;
	private ArrayList<Match> matches;

	public int calcPoints(Team team) {
		int points = 0;
		for (int i = 0; i < matches.size(); i++) {
			if (team.equals(matches.get(i).getWinningTeam())) {
				points = points + 3;
			}
			if (team.equals(matches.get(i).getTeamAway())
					|| team.equals(matches.get(i).getTeamHome())
					&& matches.get(i).getResult() == MatchResult.TIE) {
				points++;
			}
		}
		return points;
	}
}
