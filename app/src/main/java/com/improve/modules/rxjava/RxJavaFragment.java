package com.improve.modules.rxjava;

import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by javakam on 2018/7/2.
 */
public class RxJavaFragment extends BaseSwipeFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.main_layout;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("RxJava2 复习", true);

        demo1();
    }

    private void demo1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
