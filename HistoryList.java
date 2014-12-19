package com.myfirst.flashcardapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class HistoryList extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		 Cursor cursor;
		 DatabaseHelper myDb = new DatabaseHelper(this,
					"db", null, 1);
		 SQLiteCursor scoreCursor = (SQLiteCursor) 
					myDb.getReadableDatabase().rawQuery(
							"SELECT _ID, name, score FROM history  ORDER BY score",
							null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row,
				scoreCursor, new String[] { DatabaseHelper.COLUMN_NAME,
	                        DatabaseHelper.COLUMN_SCORE }, new int[] { R.id.name,
	                        R.id.score }, 0);

		getListView().setAdapter(adapter);
	}
	

}
