package com.magicplayers.easymtg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class jSONReader {

	private JSONObject jObjEdition;
	private JSONObject jObjCard;
	
	private Edition edition = new Edition();
	private Card card = new Card();
	
	public jSONReader(JSONTokener token){
		
		try {
			this.jObjEdition = new JSONObject(token);
			
			this.edition.setName(this.jObjEdition.getString("name"));
			this.edition.setCode(this.jObjEdition.getString("code"));
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(this.jObjEdition.getString("releaseDate"));
			} catch (ParseException e) {
				date = new Date();
				e.printStackTrace();
			}
			this.edition.setRealeaseDate(date);
			this.edition.setType(this.jObjEdition.getString("type"));
			
			JSONArray jArrCards = this.jObjEdition.getJSONArray("cards");
			
			for (int c=0; c < jArrCards.length(); c++) {
				JSONObject jObjCard = (jArrCards.getJSONObject(c));
				
				JSONArray jArr = jObjCard.getJSONArray("names");
				for (int i=0; i < jArr.length(); i++) {
					this.card.setNames(jArr.getString(i));
				}
				
				this.card.setManaCost(jObjCard.getString("manaCost"));
				
				this.card.setCmc(jObjCard.getInt("cmc"));
				
				jArr = jObjCard.getJSONArray("colors");
				for (int i=0; i < jArr.length(); i++) {
					this.card.setColors(jArr.getString(i));
				}
				
				this.card.setType(jObjCard.getString("type"));
				
				jArr = jObjCard.getJSONArray("supertypes");
				for (int i=0; i < jArr.length(); i++) {
					this.card.setSubtypes(jArr.getString(i));
				}
				
				jArr = jObjCard.getJSONArray("types");
				for (int i=0; i < jArr.length(); i++) {
					this.card.setTypes(jArr.getString(i));
				}
				
				jArr = jObjCard.getJSONArray("subtypes");
				for (int i=0; i < jArr.length(); i++) {
					this.card.setSubtypes(jArr.getString(i));
				}
				
				this.card.setRarity(jObjCard.getString("rarity"));
				
				this.card.setText(jObjCard.getString("text"));
				
				this.card.setFlavor(jObjCard.getString("flavor"));
				
				this.card.setArtist(jObjCard.getString("artist"));
				
				this.card.setPower(jObjCard.getString("power"));
				
				this.card.setToughness(jObjCard.getString("toughness"));
				
				this.card.setLoyalty(jObjCard.getString("loyalty"));
				
				jArr = jObjCard.getJSONArray("variations");
				for (int i=0; i < jArr.length(); i++) {
					this.card.setVariations(jArr.getInt(i));
				}
				
				jArr = jObjCard.getJSONArray("rulings");
				for (int i=0; i < jArr.length(); i++) {
					JSONObject subJobj = (jArr.getJSONObject(i));
					date = null;
					try {
						date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(subJobj.getString("date"));
					} catch (ParseException e) {
						date = new Date();
						e.printStackTrace();
					}
					Rule rule = new Rule(date, subJobj.getString("text"));
					this.card.setRulings(rule);
				}
				
				this.card.setImageName(jObjCard.getString("imageName"));
				
				jArr = this.jObjCard.getJSONArray("printings");
				for (int i=0; i < jArr.length(); i++) {
					this.card.setPrintings(jArr.getString(i));
				}
				
				this.edition.setCards(this.card);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
