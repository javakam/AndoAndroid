package com.improve.modules.widgets;

import android.support.design.widget.TextInputLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * AutoCompleteTextView & TextInputLayout 基本操作
 * Created by javakam on 2018/6/28.
 */
public class AutoCompleteTextViewSwipeFragment extends BaseSwipeFragment {
    @BindView(R.id.actv_username)
    AutoCompleteTextView actvUsername;
    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.actv_password)
    AutoCompleteTextView actvPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    /**
     * Data List
     */
    ArrayList<String> mUserNames = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_actv;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_WIDGET_ACTV, true);
        //在 AutoCompleteTextView 弹出的列表底部提示 用户名
//        actvUsername.setCompletionHint("请选择账号"); //不好看
        //取出上次登录过的用户名，方便登录
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_dropdown_item_1line);
        //模拟数据
        for (int i = 0; i < 20; i++) {
            mUserNames.add("没有铁的钢铁侠 " + i);
        }
        adapter.addAll(mUserNames);
        actvUsername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String user = mUserNames.get(position);
                actvUsername.setText(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        actvUsername.setAdapter(adapter);
        /*mUserName.setDropDownBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bg_login)));*/
        //定制下拉List的高度
        actvUsername.setDropDownHeight(600);
        /*
        需求：除了输入一个字母提示外，还要在点击输入框时显示整个列表集合。just do it !
         */
        actvUsername.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                actvUsername.showDropDown();
                return false;
            }
        });

        // 在密码框的最后面多个 显示/隐藏 密码的小眼睛
        tilPassword.setPasswordVisibilityToggleEnabled(true);
    }
}
