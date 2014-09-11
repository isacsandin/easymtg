package com.magicplayers.easymtg.model;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Rule {

	public final static String ID_FIELD_NAME = "id";
	
	@DatabaseField(generatedId = true,columnName = ID_FIELD_NAME)
	@SerializedName("id")
	private int id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	@SerializedName("card")
	private Card card;
	
	@DatabaseField
	@SerializedName("date")
	private Date date;

	@DatabaseField
	@SerializedName("text")
	private String text;

	public Rule(Date date, String text) {
		this.setDate(date);
		this.setText(text);
	}

	public Rule() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(this);
	}
}
