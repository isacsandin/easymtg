package com.magicplayers.easymtg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Edition {
	
	@DatabaseField(generatedId=true)
    private int id;
	
    @DatabaseField
	private String name;
	
    @DatabaseField
    private String code;
	
    @DatabaseField
    private Date realeaseDate;
	
    @DatabaseField
    private String type;
	
    @DatabaseField
    private String block;
	
    @DatabaseField(foreign=true,foreignAutoRefresh=true)
    private ForeignCollection<Card> cards;
	
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

	public List<Card> getCards() {
		ArrayList<Card> cardsl = new ArrayList<Card>();
		for(Card c:cards){
			cardsl.add(c);
		}
		return cardsl;
	}

	public void setCards(ForeignCollection<Card> cards) {
		this.cards = cards;
	}
}
