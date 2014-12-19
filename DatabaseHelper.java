package com.myfirst.flashcardapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {
	 public static final String COLUMN_SCORE = "score";
	 public static final String COLUMN_NAME = "name";
     public static final String DATABASE_NAME="db";
     public static final String TABLE ="history";
	
     SQLiteDatabase db;

	public DatabaseHelper(Context context, String databaseName,
			CursorFactory factory, int version) {
		super(context, databaseName, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;

		db.execSQL("CREATE TABLE " + TABLE +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_SCORE+ " STRING, "+ COLUMN_NAME + " TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
