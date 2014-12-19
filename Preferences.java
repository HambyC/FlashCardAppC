package com.myfirst.flashcardapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class Preferences extends Activity implements OnCheckedChangeListener {

	private SharedPreferences sp;
	RadioButton rb;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preference_activity);
		rb = (RadioButton) findViewById(R.id.radioButton1);
		rb.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.radioButton1:
			sp = getSharedPreferences("checked", 0);
			editor = sp.edit();
			editor.putBoolean("check", isChecked);
			editor.putInt("double up", 100);
			editor.commit();
			break;

		}
		

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sp = getSharedPreferences("checked", 0);
		rb.setChecked(sp.getBoolean("check", false));
	}

}
