package com.improve.modules.annotation;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.improve.R;
import com.improve.modules.annotation.base.ContentView;
import com.improve.modules.annotation.base.ViewInjectUtils;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Android注解开发
 * <p>
 * Created by javakam on 2018/6/20.
 */
@ContentView(R.layout.fragment_annotation)
public class AnnotationActivity extends AppCompatActivity {

    private QMUITopBar topbar;
    private QMUIAlphaButton mBtAnnotation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        初始化标题栏();
        //【核心方法】
        ViewInjectUtils.inject(this);

        topbar = findViewById(R.id.topbar);
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
    }
}