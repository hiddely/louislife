package com.louislife.model;

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
	
}
