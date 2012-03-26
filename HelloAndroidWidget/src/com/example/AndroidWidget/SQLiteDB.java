package com.example.AndroidWidget;

import java.util.Locale;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDB{
	
	private final Context context;
	private SQLiteDBhelper DBHelper;
	private SQLiteDatabase db;
	
	public SQLiteDB(Context ctx)
	{
		this.context=ctx;
		DBHelper=new SQLiteDBhelper(context);
	}
	
	public SQLiteDB open() throws SQLException
	{
		db=DBHelper.getWritableDatabase();
		return null;
	}
	
	public void close()
	{
		DBHelper.close();
	}
	
	public void INSERT(String A, String B)
	{
		ContentValues cv = new ContentValues();
		cv.put("Ingredient", A);
		cv.put("Functions", B);
		try{
		db.insertOrThrow("Table_stuff", null, cv);
		}
		catch(SQLException e)
		{
			Log.e("log_tag", "Error inserting: "+e.toString());
		}
	}
	
	public String getFunc(String str)
	{
		String temp = null;
		try
		{
	//	String sql="SELECT Functions FROM Table_stuff";
		Cursor c=db.query("Table_stuff",new String[] {"Functions"},"ingredient = '" + str+"'",null,null,null,null);
	//	Cursor c=db.rawQuery(sql,new String[] {"Functions"});
		
		c.moveToFirst();
		temp= c.getString(0);
		}
		catch (SQLException e)
		{
			Log.e("log_tag", "Error selecting: "+e.toString());
		}
		return temp;
	}


private class SQLiteDBhelper extends SQLiteOpenHelper{
	static final String dbName="demoDB";
	static final String deptTable="Table_stuff";
	static final String colDeptID="Ingredient";
	static final String colDeptName="Functions";
	
	public SQLiteDBhelper (Context context) {
		  super(context, dbName, null,1); 
		  }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE `Table_stuff` (`Ingredient` VARCHAR(256) NOT NULL PRIMARY KEY ,`Functions` VARCHAR(3000) NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
}

