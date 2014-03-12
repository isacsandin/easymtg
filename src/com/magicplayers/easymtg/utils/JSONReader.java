package com.magicplayers.easymtg.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicplayers.easymtg.model.Card;
import com.magicplayers.easymtg.model.Edition;
import com.magicplayers.easymtg.model.Rule;

public class JSONReader {

	public static void importJSON(String token) {
		JSONObject jObjEdition;
		JSONObject jObjCard;

		Edition edition = new Edition();
		Card card = new Card();
		try {
			jObjEdition = new JSONObject(token);

			edition.setName(jObjEdition.getString("name"));
			edition.setCode(jObjEdition.getString("code"));
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
						.parse(jObjEdition.getString("releaseDate"));
			} catch (ParseException e) {
				date = new Date();
				e.printStackTrace();
			}
			edition.setRealeaseDate(date);
			edition.setType(jObjEdition.getString("type"));

			JSONArray jArrCards = jObjEdition.getJSONArray("cards");

			for (int c = 0; c < jArrCards.length(); c++) {
				jObjCard = (jArrCards.getJSONObject(c));

				JSONArray jArr = jObjCard.getJSONArray("names");
				for (int i = 0; i < jArr.length(); i++) {
					card.setNames(jArr.getString(i));
				}

				card.setManaCost(jObjCard.getString("manaCost"));

				card.setCmc(jObjCard.getInt("cmc"));

				jArr = jObjCard.getJSONArray("colors");
				for (int i = 0; i < jArr.length(); i++) {
					card.setColors(jArr.getString(i));
				}

				card.setType(jObjCard.getString("type"));

				jArr = jObjCard.getJSONArray("supertypes");
				for (int i = 0; i < jArr.length(); i++) {
					card.setSubtypes(jArr.getString(i));
				}

				jArr = jObjCard.getJSONArray("types");
				for (int i = 0; i < jArr.length(); i++) {
					card.setTypes(jArr.getString(i));
				}

				jArr = jObjCard.getJSONArray("subtypes");
				for (int i = 0; i < jArr.length(); i++) {
					card.setSubtypes(jArr.getString(i));
				}

				card.setRarity(jObjCard.getString("rarity"));

				card.setText(jObjCard.getString("text"));

				card.setFlavor(jObjCard.getString("flavor"));

				card.setArtist(jObjCard.getString("artist"));

				card.setPower(jObjCard.getString("power"));

				card.setToughness(jObjCard.getString("toughness"));

				card.setLoyalty(jObjCard.getString("loyalty"));

				jArr = jObjCard.getJSONArray("variations");
				for (int i = 0; i < jArr.length(); i++) {
					card.setVariations(jArr.getInt(i));
				}

				jArr = jObjCard.getJSONArray("rulings");
				for (int i = 0; i < jArr.length(); i++) {
					JSONObject subJobj = (jArr.getJSONObject(i));
					date = null;
					try {
						date = new SimpleDateFormat("yyyy-MM-dd",
								Locale.ENGLISH)
								.parse(subJobj.getString("date"));
					} catch (ParseException e) {
						date = new Date();
						e.printStackTrace();
					}
					Rule rule = new Rule(date, subJobj.getString("text"));
				}
				// card.setRulings(rule);

				card.setImageName(jObjCard.getString("imageName"));

				jArr = jObjCard.getJSONArray("printings");
				for (int i = 0; i < jArr.length(); i++) {
					card.setPrintings(jArr.getString(i));
				}

			}
			// edition.setCards(card);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(JSONReader.class.getResource("com/magicplayers/easymtg/resources/Allsets-x.json").getFile());
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");
		while (scanner.hasNextLine()) {
			fileContents.append(scanner.nextLine() + lineSeparator);
		}
		//importJSON(fileContents.toString());
		System.out.println(fileContents.toString());
		scanner.close();
	}
}
