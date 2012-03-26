package com.example.AndroidWidget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FullListTab extends ListActivity{
	
    localDB DB;
    ArrayAdapter<String> LAdap;


    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  final Toast t=new Toast(getApplicationContext());
        final Toast t=Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
        
        List<String> ingredientlist=new ArrayList<String>();
        DB=new localDB(FullListTab.this,getApplicationContext());
        DB.open();
	    ingredientlist=DB.getList();
	    DB.close();
		
	    LAdap = new ArrayAdapter<String>(this, R.layout.rowlayout,R.id.label, ingredientlist);
	    this.setListAdapter(LAdap); 	  
	  	ListView lv = this.getListView();
	  	lv.setTextFilterEnabled(true);
	}
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		EditText filterEditText = (EditText) findViewById(R.id.filterText);
		if (filterEditText==null) 
			Toast.makeText(this, "i m null", Toast.LENGTH_LONG)
			.show();
		else{
		filterEditText.setText("Value inserted successfully"); 
		Toast.makeText(this, "" + filterEditText.getText(), Toast.LENGTH_LONG)
		.show();}
	  

	}

    
//	@Override
//	protected void onDestroy() {
//	    super.onDestroy();
//	    filterText.removeTextChangedListener(filterTextWatcher);
//	}
	
}