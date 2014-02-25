package com.magicplayers.easymtg.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Rule {
	
	@DatabaseField(generatedId=true)
    private int id;
	
	@DatabaseField
	private Date date;
	
	@DatabaseField
	private String text;
	
	public Rule(Date date, String text) {
		this.setDate(date);
		this.setText(text);
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
}
