package com.magicplayers.easymtg.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class CardGson {
	
	@DatabaseField(generatedId=true)
	@SerializedName("id")
    private int id;
	
	@DatabaseField
	@SerializedName("names")
	private Vector<String> names;
	
	@DatabaseField
	@SerializedName("name")
	private String name;
	
	@DatabaseField
	@SerializedName("manaCost")
	private String manaCost;
	
	@DatabaseField
	@SerializedName("cmc")
	private int cmc;
	
	@DatabaseField
	@SerializedName("colors")
	private Vector<String> colors;
	
	@DatabaseField
	@SerializedName("type")
	private String type;
	
	@DatabaseField
	@SerializedName("supertypes")
	private Vector<String> supertypes;
	
	@DatabaseField
	@SerializedName("types")
	private Vector<String> types;
	
	@DatabaseField
	@SerializedName("subtypes")
	private Vector<String> subtypes;
	
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
	private Vector<Integer> variations;
	
	@DatabaseField
	@SerializedName("imageName")
	private String imageName;
	
	@DatabaseField
	@SerializedName("printings")
	private Vector<String> printings;
	
    @DatabaseField(foreign=true,foreignAutoRefresh=true)
    @SerializedName("rulings")
	private Collection<Rule> rulings;
	
	
	public CardGson(){
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public Vector<String> getNames() {
		return names;
	}

	public void setNames(String name) {
		this.names.add(name);
	}

	public int getCmc() {
		return cmc;
	}

	public void setCmc(int cmc) {
		this.cmc = cmc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Vector<String> getSupertypes() {
		return supertypes;
	}

	public void setSupertypes(String supertype) {
		this.supertypes.add(supertype);
	}

	public Vector<String> getTypes() {
		return types;
	}

	public void setTypes(String type) {
		this.types.add(type);
	}

	public Vector<String> getSubtypes() {
		return subtypes;
	}

	public void setSubtypes(String subtype) {
		this.subtypes.add(subtype);
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

	public Vector<Integer> getVariations() {
		return variations;
	}

	public void setVariations(int variation) {
		this.variations.add(variation);
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Vector<String> getColors() {
		return colors;
	}

	public void setColors(String color) {
		this.colors.add(color);
	}

	public Vector<String> getPrintings() {
		return printings;
	}

	public void setPrintings(String print) {
		this.printings.add(print);
	}
	
	public List<Rule> getRulings() {
		ArrayList<Rule> rulingsl = new ArrayList<Rule>();
		for(Rule r:rulings){
			rulingsl.add(r);
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
