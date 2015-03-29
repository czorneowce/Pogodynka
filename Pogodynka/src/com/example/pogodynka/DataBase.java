package com.example.pogodynka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	public DataBase(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS tabela_miast (id INTEGER PRIMARY KEY, miasto VARCHAR(50))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void insertData(String town) {   
		ContentValues content = new ContentValues();
		content.put("miasto", town);

		SQLiteDatabase db = this.getWritableDatabase();    
		db.insert("tabela_miast", null, content);
		db.close();
	}

	public Cursor getData(){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT id, miasto FROM tabela", null);        

		return cursor;
	}

	public void deleteData(String town)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.delete("tabela", "miasto = " + town, null);
		db.close();
	}

}
