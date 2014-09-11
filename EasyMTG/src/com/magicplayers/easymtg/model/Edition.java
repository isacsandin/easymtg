package com.magicplayers.easymtg.model;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Edition {

	public final static String ID_FIELD_NAME = "id";
	
	@DatabaseField(generatedId = true,columnName = ID_FIELD_NAME)
	@SerializedName("id")
	private int id;
	
	@DatabaseField
	@SerializedName("name")
	private String name;
	
	@DatabaseField
	@SerializedName("code")
	private String code;
	
	@DatabaseField
	@SerializedName("gatherCode")
	private String gatherCode;
	
	@DatabaseField
	@SerializedName("oldCode")
	private String oldCode;
	
	@DatabaseField
	@SerializedName("releaseDate")
	private Date releaseDate;
	
	@DatabaseField
	@SerializedName("border")
	private String border;
	
	@DatabaseField
	@SerializedName("type")
	private String type;
	
	@DatabaseField
	@SerializedName("block")
	private String block;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGatherCode() {
		return gatherCode;
	}

	public void setGatherCode(String gatherCode) {
		this.gatherCode = gatherCode;
	}

	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(this);
	}

}
