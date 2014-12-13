package com.louislife.model;

public class Transfer {
	
	private int id;
	
	private int from;
	private int to;
	private int player;
	private int price;
	private int day;

	public Transfer(int id, int from, int to, int player, int price, int day) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.player = player;
		this.price = price;
		this.day = day;
	}
	
	public int getId(){
		return id;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getPlayer() {
		return player;
	}

	public int getPrice() {
		return price;
	}

	public int getDay() {
		return day;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id +", from=" + from + ", to=" + to + ", player=" + player
				+ ", price=" + price + ", day=" + day + "]";
	}
	
	

}
