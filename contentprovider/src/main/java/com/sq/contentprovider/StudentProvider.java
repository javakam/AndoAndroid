package com.sq.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.sq.contentprovider.dao.StudentDAO;

/**
 * 自定义 ContentProvider
 * <p>
 * https://www.cnblogs.com/plokmju/p/android_ContentProvider.html
 * Created by javakam on 2018-6-24 .
 */
public class StudentProvider extends ContentProvider {
    private final static String TAG = "123";
    private StudentDAO studentDao = null;
    private static final UriMatcher URI_MATCHER = new UriMatcher(
            UriMatcher.NO_MATCH);
    private static final int STUDENT = 1;
    private static final int STUDENTS = 2;

    static {
        //添加两个URI筛选
        URI_MATCHER.addURI("com.sq.contentprovider.StudentProvider",
                "student", STUDENTS);
        //使用通配符#，匹配任意数字
        URI_MATCHER.addURI("com.sq.contentprovider.StudentProvider",
                "student/#", STUDENT);
    }

    public StudentProvider() {

    }

    @Override
    public boolean onCreate() {
        // 初始化一个数据持久层
        studentDao = new StudentDAO(getContext());
        Log.i(TAG, "---->>onCreate()被调用");
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri resultUri = null;
        //解析Uri，返回Code
        int flag = URI_MATCHER.match(uri);
        if (flag == STUDENTS) {
            long id = studentDao.insertStudent(values);
            Log.i(TAG, "---->>插入成功, id=" + id);
            resultUri = ContentUris.withAppendedId(uri, id);
        }
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = -1;
        try {
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case STUDENT:
                    // delete from student where id=?
                    // 单条数据，使用ContentUris工具类解析出结尾的Id
                    long id = ContentUris.parseId(uri);
                    String where_value = "id = ?";
                    String[] args = {String.valueOf(id)};
                    count = studentDao.deleteStudent(where_value, args);
                    break;
                case STUDENTS:
                    count = studentDao.deleteStudent(selection, selectionArgs);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "---->>删除成功,count=" + count);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = -1;
        try {
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case STUDENT:
                    long id = ContentUris.parseId(uri);
                    String where_value = " id = ?";
                    String[] args = {String.valueOf(id)};
                    count = studentDao.updateStudent(values, where_value, args);
                    break;
                case STUDENTS:
                    count = studentDao.updateStudent(values, selection,
                            selectionArgs);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "---->>更新成功，count=" + count);
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case STUDENT:
                    long id = ContentUris.parseId(uri);
                    String where_value = " id = ?";
                    String[] args = {String.valueOf(id)};
                    cursor = studentDao.queryStudents(where_value, args);
                    break;
                case STUDENTS:
                    cursor = studentDao.queryStudents(selection, selectionArgs);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "---->>查询成功，Count=" + cursor.getCount());
        return cursor;
    }

    /**
     * getType()中的MIME
     * <p>
     * 　　MIME类型就是设定某种扩展名的文件用一种应用程序来打开的方式类型。
     * 在ContentProvider中的getType方法，返回的就是一个MIME类型的字符串。如果支持需要使用ContentProvider来访问数据，
     * 就上面这个Demo，getType()完全可以只返回一个Null，并不影响效果，
     * 但是覆盖ContentProvider的getType方法对于用new Intent(String action, Uri uri)方法启动activity是很重要的，
     * 如果它返回的MIME type和activity在<intent filter>中定义的data的MIME type不一致，将造成activity无法启动。
     * 这就涉及到Intent和Intent-filter的内容了，以后有机会再说，这里不再详解。
     * <p>
     * 　　从官方文档了解到，getType返回的字符串，如果URI针对的是单条数据，
     * 则返回的字符串以vnd.android.cursor.item/开头；如果是多条数据，则以vnd.adroid.cursor.dir/开头。
     *
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
//		int flag = URI_MATCHER.match(uri);
//		String type = null;
//		switch (flag) {
//		case STUDENT:
//			type = "vnd.android.cursor.item/student";
//			Log.i(TAG, "----->>getType return item");
//			break;
//		case STUDENTS:
//			type = "vnd.android.cursor.dir/students";
//			Log.i(TAG, "----->>getType return dir");
//			break;
//		}
//		return type;
        return null;
    }

    /**
     * Tips: call()和bulkInsert()方法 !
     * 使用call，理论上可以在ContentResolver中执行ContentProvider暴露出来的任何方法，而bulkInsert()方法用于插入多条数据。
     *
     * @param method
     * @param arg
     * @param extras
     * @return
     */
    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        Log.i(TAG, "------>>" + method);
        Bundle bundle = new Bundle();
        bundle.putString("returnCall", "call被执行了");
        return bundle;
    }
}
