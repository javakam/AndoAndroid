package com.sq.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static String name="mydb.db";
	private static int version=1;
	public DbHelper(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String sql="create table student (id integer primary key autoincrement,name varchar(64),address varchar(64))";
		database.execSQL(sql);
		ContentValues cv1=new ContentValues();
		cv1.put("name", "jack");
		cv1.put("address", "HK");
		database.insert("student", null, cv1);
		cv1.clear();
		cv1.put("name", "Emma");
		cv1.put("address", "Taiwan");
		database.insert("student", null, cv1);
		cv1.clear();
		cv1.put("name", "Dick");
		cv1.put("address", "HK");
		database.insert("student", null, cv1);
		cv1.clear();
		cv1.put("name", "Tim");
		cv1.put("address", "beijing");
		database.insert("student", null, cv1);
		cv1.clear();
		cv1.put("name", "jimmy");
		cv1.put("address", "beijing");
		database.insert("student", null, cv1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
