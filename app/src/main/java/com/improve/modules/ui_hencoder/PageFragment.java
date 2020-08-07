package com.improve.modules.ui_hencoder;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.improve.R;

import java.util.Random;

public class PageFragment extends Fragment {
    private static final String FRAG_TAG = "practiceLayoutRes";
    @LayoutRes
    int practiceLayoutRes;

    public static PageFragment newInstance(@LayoutRes int practiceLayoutRes) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(FRAG_TAG, practiceLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            practiceLayoutRes = args.getInt(FRAG_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        ViewStub practiceStub = view.findViewById(R.id.practiceStub);
        practiceStub.setLayoutResource(practiceLayoutRes);
        //无效
//        practiceStub.setBackgroundColor(...);

        //布局的加载有两种方式，一种是stub.inflate();
        //另一种是stub.setVisibility(View.VISIBLE);
//        practiceStub.inflate();
        practiceStub.setVisibility(View.VISIBLE);
        //实例化之后就可以拿到stub布局的根节点，然后可以对之进行操作
        View root = view.findViewById(R.id.inflated_id);
        root.setBackgroundColor(Color.BLUE);
        Random random = new Random();
        String bgColors[] = new String[]{"#FFFAFA", "#FFFFE0", "#FFF0F5", "#F8F8FF", "#F0FFF0", "#E0FFFF"};
        root.setBackgroundColor(Color.parseColor(bgColors[random.nextInt(bgColors.length)]));

        return view;
    }

}
