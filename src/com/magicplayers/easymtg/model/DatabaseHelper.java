package com.magicplayers.easymtg.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
    private static final String DATABASE_NAME = "EasyMTG.sqlite";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    private Dao<Card, Integer> cardDao = null;
    private Dao<Edition, Integer> editionDao = null;
    private Dao<CardEdition, Integer> cardEditionDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Card.class);
            TableUtils.createTable(connectionSource, Edition.class);
            TableUtils.createTable(connectionSource, CardEdition.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>(); 
            switch(oldVersion) 
            {
              case 1: 
                  //allSql.add("alter table AdData add column `new_col` VARCHAR");
                  //allSql.add("alter table AdData add column `new_col2` VARCHAR");
            }
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
        
    }

    public Dao<Card, Integer> getCardDao() {
        if (null == cardDao) {
            try {
                cardDao = getDao(Card.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return cardDao;
    }

    public Dao<Edition, Integer> getEditionDao() {
        if (null == editionDao) {
            try {
                editionDao = getDao(Edition.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return editionDao;
    }
    
    public Dao<CardEdition, Integer> getCardEditionDao() {
        if (null == cardEditionDao) {
            try {
                cardEditionDao = getDao(Edition.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return cardEditionDao;
    }

}

