package com.ishiqing.modules.service.aidl;

import android.view.View;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import butterknife.OnClick;

/**
 * AIDL（Android interface defination language）通信技术
 * <p>
 * Created by javakam on 2018/6/25 0025.
 */
public class AIDIServiceFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_aidl;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_AIDL, true);
    }

    @OnClick({R.id.btnStart, R.id.btnBind, R.id.btnUnbind, R.id.btnStop})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                break;
            case R.id.btnBind:
                break;
            case R.id.btnUnbind:
                break;
            case R.id.btnStop:
                break;
            default:
                break;
        }
    }
}
