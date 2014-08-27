package com.magicplayers.easymtg.model;

import com.j256.ormlite.field.DatabaseField;

public class CardEdition {
	
	public final static String CARD_ID_FIELD_NAME = "card_id";
	public final static String EDITION_ID_FIELD_NAME = "edition_id";

	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField(foreign = true, columnName = CARD_ID_FIELD_NAME)
	Card card;

	@DatabaseField(foreign = true, columnName = EDITION_ID_FIELD_NAME)
	Edition edition;

	public CardEdition() {
	}

	public CardEdition(Card card, Edition edition) {
		this.card = card;
		this.edition = edition;
	}
}
