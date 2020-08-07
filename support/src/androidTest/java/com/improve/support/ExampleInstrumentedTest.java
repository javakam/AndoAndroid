package com.improve.support;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.improve.support", appContext.getPackageName());
    }


    public void calltest() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student");
        Bundle bundle = contentResolver.call(uri, "method", null, null);
        String returnCall = bundle.getString("returnCall");
        Log.i("main", "-------------->" + returnCall);
    }

    public void insert() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student");
        ContentValues values = new ContentValues();
        values.put("name", "Demo");
        values.put("address", "HK");
        Uri returnuir = contentResolver.insert(uri, values);
        Log.i("main", "-------------->" + returnuir.getPath());
    }

    public void delete() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        // 删除多行：content://com.improve.support.provider.StudentProvider/student
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student/1");
        contentResolver.delete(uri, null, null);
    }

    public void deletes() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student");
        String where = "address=?";
        String[] where_args = {"HK"};
        contentResolver.delete(uri, where, where_args);
    }

    public void update() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student/2");
        ContentValues values = new ContentValues();
        values.put("name", "李四");
        values.put("address", "上海");
        contentResolver.update(uri, values, null, null);
    }

    public void updates() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student");
        ContentValues values = new ContentValues();
        values.put("name", "王五");
        values.put("address", "深圳");
        String where = "address=?";
        String[] where_args = {"beijing"};
        contentResolver.update(uri, values, where, where_args);
    }

    public void query() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student/2");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.i("main",
                    "-------------->"
                            + cursor.getString(cursor.getColumnIndex("name")));
        }
    }

    public void querys() {
        ContentResolver contentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.improve.support.provider.StudentProvider/student");
        String where = "address=?";
        String[] where_args = {"深圳"};
        Cursor cursor = contentResolver.query(uri, null, where, where_args,
                null);
        while (cursor.moveToNext()) {
            Log.i("main",
                    "-------------->"
                            + cursor.getString(cursor.getColumnIndex("name")));
        }
    }

}