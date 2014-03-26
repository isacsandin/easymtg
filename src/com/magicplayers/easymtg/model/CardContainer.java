package com.magicplayers.easymtg.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CardContainer {
	@SerializedName("cards")
	public List<Card> cards;
}
