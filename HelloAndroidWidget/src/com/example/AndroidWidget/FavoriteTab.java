package com.example.AndroidWidget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FavoriteTab extends ListActivity{
	
    localDB DB;
    
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      //  final Toast t=new Toast(getApplicationContext());
        final Toast t=Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
        
        List<String> ingredientlist=new ArrayList<String>();
        DB=new localDB(FavoriteTab.this,getApplicationContext());
        	DB.open();
	    	ingredientlist=DB.getList();
	        DB.close();

	      setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, ingredientlist)); 	  
	  	  ListView lv = getListView();
	  	  lv.setTextFilterEnabled(true);
	  	  
	  	lv.setOnItemClickListener(new OnItemClickListener() {
	  	    public void onItemClick(AdapterView<?> parent, View view,
	  	        int position, long id) {
	  	    	String str=(String) ((TextView)view).getText();
	  	    	DB.open();
	  			String func=DB.getFunc(str);
	  			DB.close();
	  			t.setText(func);
	  			t.show();
	  		//	Toast.makeText(getApplicationContext(),func,
	  			//  	          Toast.LENGTH_LONG).show();
	  	    }
	   });
	}

}