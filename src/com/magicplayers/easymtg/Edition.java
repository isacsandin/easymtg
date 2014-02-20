package com.magicplayers.easymtg;

import java.util.Date;
import java.util.Vector;

public class Edition {
	
	private String name;
	private String code;
	private Date realeaseDate;
	private String type;
	private String block;
	private Vector<Card> cards;
	
	public Edition(){
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getRealeaseDate() {
		return realeaseDate;
	}

	public void setRealeaseDate(Date realeaseDate) {
		this.realeaseDate = realeaseDate;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public Vector<Card> getCards() {
		return cards;
	}

	public void setCards(Card card) {
		this.cards.add(card);
	}
}
