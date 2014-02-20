package com.magicplayers.easymtg;

import java.util.Vector;

public class Card {
	
	private Vector<String> names;
	private String manaCost;
	private int cmc;
	private Vector<String> colors;
	private String type;
	private Vector<String> supertypes;
	private Vector<String> types;
	private Vector<String> subtypes;
	private String rarity;
	private String text;
	private String flavor;
	private String artist;
	private String power;
	private String toughness;
	private String loyalty;
	private Vector<Integer> variations;
	private String imageName;
	private Vector<Rule> rulings;
	private Vector<String> printings;
	
	
	public Card(){
		
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

	public Vector<Rule> getRulings() {
		return rulings;
	}

	public void setRulings(Rule rule) {
		this.rulings.add(rule);
	}

	public Vector<String> getPrintings() {
		return printings;
	}

	public void setPrintings(String print) {
		this.printings.add(print);
	}
}
