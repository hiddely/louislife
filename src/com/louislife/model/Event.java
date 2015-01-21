package com.louislife.model;

/**
 * Events that happened during match. Always child of team
 * @author hidde
 *
 */
public class Event {
	
private int player;
private EventType type;
private int minute;

	public Event(int player, EventType type, int minute) {
		this.player = player;
		this.type = type;
		this.minute = minute;
	}

	public int getPlayer() {
		return player;
	}

	public EventType getType() {
		return type;
	}

	public int getMinute() {
		return minute;
	}

	@Override
	public String toString() {
		return "Event [player=" + player + ", type=" + type + ", minute="
				+ minute + "]";
	}
	
}
