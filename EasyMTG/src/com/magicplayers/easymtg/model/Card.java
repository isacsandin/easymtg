package com.magicplayers.easymtg.model;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Card {

	public final static String ID_FIELD_NAME = "id";
	
	@DatabaseField(generatedId = true,columnName = ID_FIELD_NAME)
	@SerializedName("id")
	private int id;

	@DatabaseField
	@SerializedName("name")
	private String name;

	@DatabaseField
	@SerializedName("names")
	private String names;

	@DatabaseField
	@SerializedName("manaCost")
	private String manaCost;

	@DatabaseField
	@SerializedName("cmc")
	private String cmc;

	@DatabaseField
	@SerializedName("colors")
	private String colors;

	@DatabaseField
	@SerializedName("type")
	private String type;

	@DatabaseField
	@SerializedName("supertypes")
	private String supertypes;

	@DatabaseField
	@SerializedName("types")
	private String types;

	@DatabaseField
	@SerializedName("subtypes")
	private String subtypes;

	@DatabaseField
	@SerializedName("rarity")
	private String rarity;

	@DatabaseField
	@SerializedName("text")
	private String text;

	@DatabaseField
	@SerializedName("flavor")
	private String flavor;

	@DatabaseField
	@SerializedName("artist")
	private String artist;

	@DatabaseField
	@SerializedName("power")
	private String power;

	@DatabaseField
	@SerializedName("toughness")
	private String toughness;

	@DatabaseField
	@SerializedName("loyalty")
	private String loyalty;

	@DatabaseField
	@SerializedName("variations")
	private String variations;

	@DatabaseField
	@SerializedName("imageName")
	private String imageName;

	@DatabaseField
	@SerializedName("printings")
	private String printings;

	@ForeignCollectionField(eager = true)
	@SerializedName("rulings")
	private Collection<Rule> rulings;

	public Card() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String name) {
		this.names = name;
	}

	public String getCmc() {
		return cmc;
	}

	public void setCmc(String cmc) {
		this.cmc = cmc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSupertypes() {
		return supertypes;
	}

	public void setSupertypes(String supertype) {
		this.supertypes = supertype;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String type) {
		this.types = type;
	}

	public String getSubtypes() {
		return subtypes;
	}

	public void setSubtypes(String subtype) {
		this.subtypes = subtype;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getToughness() {
		return toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public String getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}

	public String getVariations() {
		return variations;
	}

	public void setVariations(String variation) {
		this.variations = variation;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String color) {
		this.colors = color;
	}

	public String getPrintings() {
		return printings;
	}

	public void setPrintings(String print) {
		this.printings = print;
	}

	public Collection<Rule> getRulings() {
		ArrayList<Rule> rulingsl = new ArrayList<Rule>();
		if (this.rulings != null) {
			for (Rule r : rulings) {
				rulingsl.add(r);
			}
		}
		return rulingsl;
	}

	public void setRulings(Collection<Rule> rulings) {
		this.rulings = rulings;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(this);
	}
}
