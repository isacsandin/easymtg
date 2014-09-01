package com.magicplayers.easymtg.lazylist;

public class ItemListView {
	private String texto;
	private String imageUrl;

	public ItemListView() {
	}

	public ItemListView(String texto, String imageUrl) {
		this.texto = texto;
		this.imageUrl = imageUrl;
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
}