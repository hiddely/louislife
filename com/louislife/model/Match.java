package com.louislife.model;

/**
 * Class representing a played match between to teams
 * @author hidde
 *
 */
public class Match {
	
	private Team teamHome;
	private Team teamAway;
	
	private int scoreHome;
	private int scoreAway;
	
	/**
	 * Get the result of the match
	 * @return enum MatchResult
	 */
	public MatchResult getResult() {
		if (scoreHome == scoreAway)
			return MatchResult.TIE;
		else if (scoreHome < scoreAway)
			return MatchResult.AWAY;
		else if (scoreHome > scoreAway)
			return MatchResult.HOME;
		return null;
	}
	
	/**
	 * Get the winning team of the match
	 * @return
	 */
	public Team getWinningTeam() {
		if (getResult() == MatchResult.HOME)
			return teamHome;
		else if (getResult() == MatchResult.AWAY)
			return teamAway;
		return null; // At a tie
	}
	
	/**
	 * Get the losing team of the match
	 * @return the losing team
	 */
	public Team getLosingTeam() {
		if (getResult() == MatchResult.HOME)
			return teamAway;
		else if (getResult() == MatchResult.AWAY)
			return teamHome;
		return null; // At a tie
	}
	
	/**
	 * Make new match object using team ids
	 * @param teamHome team_id
	 * @param teamAway team_id
	 * @param scoreHome
	 * @param scoreAway
	 */
	// TODO
	// WARNING not finished
	public Match(int teamHome, Team teamAway, int scoreHome, int scoreAway) {
		//this.teamHome = // Team.find() ?
		//this.teamAway
		this.scoreHome = scoreHome;
		this.scoreAway = scoreAway;
	}
	
	public Match(Team teamHome, Team teamAway, int scoreHome, int scoreAway) {
		super();
		this.teamHome = teamHome;
		this.teamAway = teamAway;
		this.scoreHome = scoreHome;
		this.scoreAway = scoreAway;
	}
	public Team getTeamHome() {
		return teamHome;
	}
	public Team getTeamAway() {
		return teamAway;
	}
	public int getScoreHome() {
		return scoreHome;
	}
	public int getScoreAway() {
		return scoreAway;
	}
	
	
	
}