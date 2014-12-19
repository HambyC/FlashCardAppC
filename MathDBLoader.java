package com.myfirst.flashcardapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v4.content.CursorLoader;

public class MathDBLoader extends CursorLoader{
	private static final String DATABASE_NAME = "db";

	public MathDBLoader(Context context) {
		super(context);
	}

	@Override
	public Cursor loadInBackground() {
		DatabaseHelper db = new DatabaseHelper(this.getContext(),
			DATABASE_NAME, null, 1);
	SQLiteCursor constantsCursor = (SQLiteCursor) 
			db.getReadableDatabase().rawQuery(
					"SELECT _ID, title, value FROM constants ORDER BY title",
					null);
			

	return constantsCursor;

		// TODO Auto-generated method stub
	}

}
