package com.example.AndroidWidget;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

public class localDB {
	
	private final Context context;
	private final Context app_context;
	private DBhelper DBHelper;
	private SQLiteDatabase db;
	
	public localDB(Context ctx,Context app_ctx) {
		this.context=ctx;
		DBHelper=new DBhelper(context);
		DBHelper.createDataBase();
		app_context=app_ctx;
	}
	
	public SQLiteDB open() throws SQLException
	{
		DBHelper.OpenDataBase();
		db=DBHelper.getReadableDatabase();
		return null;
	}
	
	public void close()
	{
		DBHelper.close();
	}
	
	public String getFunc(String str)
	{
		String temp = null;
		try
		{
	//	String sql="SELECT Functions FROM Table_stuff";
		Cursor c=db.query("list",new String[] {"Functions"},"ingredient = '" + str+"'",null,null,null,null);
	//	Cursor c=db.rawQuery(sql,new String[] {"Functions"});
		
		c.moveToFirst();
		temp= c.getString(0);
		}
		catch (SQLException e)
		{
			Log.e("log_boo", "Error selecting: "+e.toString());
		}
		return temp;
	}
	
	public ArrayList<String> getList()
	{
		Log.e("logger","here");
		ArrayList<String> ingredients = new ArrayList<String>();
		Log.e("logger","here2");
		try
		{
			Cursor c=db.query("list",new String[] {"ingredient"}, null, null, null, null, null);
			Log.e("logger","here3");
			c.moveToFirst();
			while(c.isAfterLast()==false)
			{
			ingredients.add(c.getString(0));
			c.moveToNext();
			}
		}
		catch(SQLException e)
		{
			Log.e("log_boo","Error selecting list of ingredients:"+e.toString());
		}
	
		return ingredients;
	}
	
	private class DBhelper extends SQLiteOpenHelper{
		
		private static final String DB_PATH = "/data/data/com.example.AndroidWidget/databases/";
		private static final String DB_NAME = "test3.db";
	    
	    private SQLiteDatabase myDataBase; 
	    
	    private final Context myContext;

		public DBhelper(Context context) {
			super(context, DB_NAME, null, 1);
			this.myContext=context;

			// TODO Auto-generated constructor stub
		}
		
		public void createDataBase(){
			boolean dbExist=checkDataBase();
			if(dbExist){
				//do nothing
			}
			else
			{
				this.getReadableDatabase();
				try{
					copyDataBase();
					Log.e("log_debug","copying");
				}
				catch(IOException e){
					Log.e("log_boo","Error copying database"+e.toString());
				}
			}
		}

		private void copyDataBase() throws IOException {
			// TODO Auto-generated method stub
			
			InputStream myInput =myContext.getAssets().open(DB_NAME);
			String outFileName = DB_PATH + DB_NAME;
			OutputStream myOutput= new FileOutputStream(outFileName);
			
			byte[] buffer = new byte[1024];
			int length;
			while((length=myInput.read(buffer))>0){
				myOutput.write(buffer,0,length);
			}
			myOutput.flush();
			myOutput.close();
			myInput.close();
		}

		private boolean checkDataBase() {
			// TODO Auto-generated method stub
			SQLiteDatabase checkDB=null;
			try{
				String myPath=DB_PATH+DB_NAME;
				checkDB=SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
			}
			catch(SQLiteException e){
				Log.e("log_boo","Error database does not exist yet"+e.toString());
			}
			
			if(checkDB != null){
				checkDB.close();
				return true;
			}
			return false;
			
		}
		
		public void OpenDataBase() throws SQLException{
			String myPath = DB_PATH + DB_NAME;
			myDataBase=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
			
		}
		
		@Override
		public synchronized void close(){
			if(myDataBase !=null)
			myDataBase.close();
			super.close();
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
