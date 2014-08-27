package com.magicplayers.easymtg.model;

import android.content.Context;

public class DatabaseManager {

	static private DatabaseManager instance;

	static public void init(Context ctx,String databasePath) {
		if (null == instance) {
			instance = new DatabaseManager(ctx,databasePath);
		}
	}

	static public DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseHelper helper;

	private DatabaseManager(Context ctx,String databasePath) {
		helper = new DatabaseHelper(ctx,databasePath);
	}

	public DatabaseHelper getHelper() {
		return helper;
	}

}
