package com.myfirst.flashcardapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Selector extends ListActivity implements OnClickListener {

    String[] items = {"Addition", "Subtraction", "Multiplication",
            "Division", "History", "Preferences"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        getListView().setAdapter(adapter);
        registerForContextMenu(getListView());


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("mathdecision", position);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // display the selected context menu text
        Toast.makeText(getApplicationContext(), item.getTitle(),
                Toast.LENGTH_SHORT).show();
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.double_id:
                Intent mainIntent = new Intent(this, MainActivity.class);
                int operand1 = (int) (Math.random() * 100);
                int operand2 = (int) (Math.random() * 100);
                mainIntent.putExtra("opdbl", 2);
                startActivity(mainIntent);


        }


        return super.onContextItemSelected(item);



    }
    /*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selector, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}
