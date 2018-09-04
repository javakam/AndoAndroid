package com.ishiqing.modules.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ishiqing.R;
import com.ishiqing.modules.annotation.base.ContentView;
import com.ishiqing.modules.annotation.base.OnClick;
import com.ishiqing.modules.annotation.base.ViewInject;
import com.ishiqing.modules.annotation.base.ViewInjectUtils;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;

/**
 * Android注解开发
 * <p>
 * Created by javakam on 2018/6/20.
 */
@ContentView(R.layout.fragment_annotation)
public class AnnotationActivity extends AppCompatActivity {
    @ViewInject(R.id.btAnnotation)
    QMUIAlphaButton mBtAnno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        初始化标题栏();
        //【核心方法】
        ViewInjectUtils.inject(this);

        //测试  @ViewInject 是否生效
        mBtAnno.setOnClickListener(v -> Toast.makeText(AnnotationActivity.this,
                "测试  @ViewInject 是否生效", Toast.LENGTH_SHORT).show());
    }

    private void 初始化标题栏() {
        setTitle("Android注解处理机");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick({R.id.btAnnotationProcessor})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btAnnotationProcessor:
                Toast.makeText(this, "Android注解处理机", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
