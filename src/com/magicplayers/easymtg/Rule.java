package com.magicplayers.easymtg;

import java.util.Date;

public class Rule {
	private Date date;
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
