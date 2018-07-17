package com.sq.support.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class StudentDAO {
	private DbHelper helper = null;

	public StudentDAO(Context context) {
		helper = new DbHelper(context);
	}

	public long insertStudent(ContentValues values) {
		long id = -1;
		SQLiteDatabase database = null;
		try {
			database = helper.getWritableDatabase();
			id = database.insert("student", null, values);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return id;
	}

	public int deleteStudent(String whereClause, String[] whereArgs) {
		int count = -1;
		SQLiteDatabase database = null;
		try {
			database = helper.getWritableDatabase();
			count = database.delete("student", whereClause, whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return count;
	}

	public int updateStudent(ContentValues values, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase database = null;
		int count = -1;
		try {
			database = helper.getWritableDatabase();
			count = database.update("student", values, whereClause, whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
		return count;
	}

	public Cursor queryStudents(String selection, String[] selectionArgs) {
		SQLiteDatabase database = null;
		Cursor cursor=null;
		try {
			database = helper.getReadableDatabase();
			cursor = database.query(true, "student", null, selection,
					selectionArgs, null, null, null, null);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
//				database.close();
			}
		}
		return cursor;
	}

}
