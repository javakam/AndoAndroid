package com.improve.modules.thread;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.utils.L;

/**
 * HandlerThread -- 【疑惑】
 * <p>
 * 测试类加载,跟 HandlerThread 半毛钱关系都没有。。。正文{@see IntentService}
 * <p>
 * Created by javakam on 2018/8/15.
 */
public class ClassLoaderFragment extends BaseSwipeFragment {

    TextView tvResult;

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_handler_thread;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("HandlerThread", true);
        tvResult = v.findViewById(R.id.tvResult);

        v.findViewById(R.id.btTest1).setOnClickListener(this);
        v.findViewById(R.id.btTest2).setOnClickListener(this);
        v.findViewById(R.id.btTest3).setOnClickListener(this);
    }

    /*
    【重】静态成员 会在类加载的时候初始化
    【重】静态成员变量 属于类不属于对象，所以不会参与序列化过程；其次用 transient 关键字标记的成员变量不参与序列化过程。
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btTest1:
                //注意看 TestClassLoad中的静态代码块是否执行
                //若执行，说明TestClassLoad.class字节码对象被加载进内存
                //若未执行，说明并没有执行TestClassLoad的类加载过程！-- 实际上没有执行
                tvResult.setText(TestClassLoad.POS);
                L.e("Test1: " + TestClassLoad.POS);
                break;
            case R.id.btTest2:
                //static{} 在TestClassLoad的字节码对象加载进内存时执行一次，再次调用责不再执行
                TestClassLoad t = new TestClassLoad();
                TestClassLoad t1 = new TestClassLoad();
                TestClassLoad t2 = new TestClassLoad();
                break;
            case R.id.btTest3:
                //再次验证 Test1
                //【重】若static{} 仍未输出，说明 调用静态变量，JVM并不会加载 TestClassLoad.class 到内存中
                tvResult.setText(TestClassLoad.POS222);
                L.e("Test3: " + TestClassLoad.POS222);
                break;
            default:
                break;
        }
    }
}
