package com.magicplayers.easymtg.model;

import com.j256.ormlite.field.DatabaseField;

public class CardEdition {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true)
	private Card card;
	@DatabaseField(foreign = true)
	private Edition edition;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Edition getEdition() {
		return edition;
	}
	public void setEdition(Edition edition) {
		this.edition = edition;
	}
	
	
}
