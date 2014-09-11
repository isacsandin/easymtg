package com.magicplayers.easymtg.lazylist;

public class LazyListItem {
	private String texto;
	private String imageUrl;
	private String type;
	private String manaCost;

	public LazyListItem() {
	}

	public LazyListItem(String texto, String imageUrl, String type, String manaCost) {
		this.texto = texto;
		this.imageUrl = imageUrl;
		this.setType(type);
		this.setManaCost(manaCost);
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}
}