package com.ishiqing.modules.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ContentProvider共享机制
 * <p>
 * 注意：
 * 1. onCreate() 默认执行在主线程，别做耗时操作，query() 也最好异步执行
 * 2. 增删改查操作都可能会被多个线程并发访问，因此需要注意线程安全
 * <p>
 * Created by javakam on 2018/6/18.
 * <p>
 * 自定义Provider
 * 参考：https://www.cnblogs.com/plokmju/p/android_ContentProvider.html
 */
public class ProviderFragment1 extends BaseFragment {
    /*
1 概述:
　　ContentProvider可以理解为一个Android应用对外开放的接口，只要是符合它所定义的Uri格式的请求，均可以正常访问执行操作。
   其他的Android应用可以使用ContentResolver对象通过与ContentProvider同名的方法请求执行，被执行的就是ContentProvider中的同名方法。
   所以ContentProvider很多对外可以访问的方法，在ContentResolver中均有同名的方法，是一一对应的，
     */
    /*
2 Uri:
　　在Android中，Uri是一种比较常见的资源访问方式。而对于ContentProvider而言，
　　Uri也是有固定格式的：
         <srandard_prefix>://<authority>/<data_path>/<id>

    <srandard_prefix>：ContentProvider的srandard_prefix始终是content://。
    <authority>：ContentProvider的名称。
    <data_path>：请求的数据类型。
    <id>：指定请求的特定数据。

  Tips:
    还有两个非常有意思的方法，必须要提一下，call()和bulkInsert()方法。
    使用call，理论上可以在ContentResolver中执行ContentProvider暴露出来的任何方法，
    而bulkInsert()方法用于插入多条数据。

3 UriMatcher对象
   在ContentProvider的CRUD操作，均会传递一个Uri对象，通过这个对象来匹配对应的请求。
   那么如何确定一个Uri执行哪项操作呢？需要用到一个UriMatcher对象，这个对象用来帮助内容提供者匹配Uri。
   它所提供的方法非常简单，仅有两个：
void addURI(String authority,String path,int code)：
    添加一个Uri匹配项，authority为AndroidManifest.xml中注册的ContentProvider中的authority属性；
    path为一个路径，可以设置通配符，#表示任意数字，*表示任意字符；code为自定义的一个Uri代码。
int match(Uri uri)：
    匹配传递的Uri，返回addURI()传递的code参数。

4
     */

    @BindView(R.id.tvProvider)
    TextView tvProvider;

    @Override
    protected int getLayoutResId() {
        return R.layout.button_layout;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.btProvider,R.id.btProvider2})
    void getContacts(View v) {
        switch (v.getId()) {
            case R.id.btProvider:
                Toast.makeText(mActivity, "使用系统提供者", Toast.LENGTH_LONG).show();
                ContentResolver resolver = mActivity.getContentResolver();
                //用 系统应用内部提供的 联系人URI  进行简单的查询
                Cursor cursor = resolver.query(ContactsContract.RawContacts.CONTENT_URI,
                        null, null, null, null);
                StringBuilder sb = new StringBuilder();
                while (cursor.moveToNext()) {
                    // cursor传入列名
                    String id = cursor.getString(cursor.getColumnIndex("_id"));
                    String pinned = cursor.getString(cursor.getColumnIndex("pinned"));
                    String display_name = cursor.getString(cursor.getColumnIndex("display_name"));
                    tvProvider.post(new Runnable() {
                        @Override
                        public void run() {
                            sb
                                    .append("id: ").append(id).append("   ")
                                    .append("pinned: ").append(pinned).append("   ")
                                    .append("姓名: ").append(display_name).append("   ")
                                    .append("\n");
                            tvProvider.setText("通讯录联系人 : \n " + sb.toString());
                        }
                    });
                }
                break;
            case R.id.btProvider2:
                ContentResolver contentResolver =mActivity.getContentResolver();
                Uri uri = Uri
                        .parse("content://com.sq.support.StudentProvider/student");
                ContentValues values = new ContentValues();
                values.put("name", "请输入名称");
                values.put("address", "请输入地址");
                Uri returnuir = contentResolver.insert(uri, values);
                Log.i("123", "-------------->" + returnuir.getPath());
                Toast.makeText(mActivity, "自定义提供者: \n" + returnuir.toString(), Toast.LENGTH_LONG).show();
                tvProvider.setText("自定义提供者: \n" + returnuir.toString());
                break;
        }

    }
}
