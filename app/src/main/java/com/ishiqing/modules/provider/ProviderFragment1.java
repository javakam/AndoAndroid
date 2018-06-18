package com.ishiqing.modules.provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ContentProvider共享机制
 * <p>
 * Created by javakam on 2018/6/18.
 */
public class ProviderFragment1 extends BaseFragment {
    @BindView(R.id.tvProvider)
    TextView tvProvider;

    @Override
    protected int getLayoutResId() {
        return R.layout.button_layout;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.btProvider})
    void getContacts(View v) {
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


    }
}
