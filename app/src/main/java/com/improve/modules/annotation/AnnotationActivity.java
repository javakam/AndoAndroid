package com.improve.modules.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.improve.R;
import com.improve.modules.annotation.base.ContentView;
import com.improve.modules.annotation.base.ViewInjectUtils;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;

/**
 * Android注解开发
 * <p>
 * Created by javakam on 2018/6/20.
 */
@ContentView(R.layout.fragment_annotation)
public class AnnotationActivity extends AppCompatActivity {

    private QMUIAlphaButton mBtAnnotation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        初始化标题栏();
        //【核心方法】
        ViewInjectUtils.inject(this);

        mBtAnnotation = findViewById(R.id.btAnnotation);
        //测试  @ViewInject 是否生效
        mBtAnnotation.setOnClickListener(v -> Toast.makeText(AnnotationActivity.this,
                "测试  @ViewInject 是否生效", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btAnnotationProcessor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnnotationActivity.this, "Android注解处理机", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void 初始化标题栏() {
        setTitle("Android注解处理机");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}