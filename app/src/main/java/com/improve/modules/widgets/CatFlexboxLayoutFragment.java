package com.improve.modules.widgets;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * RecyclerView + FlexboxLayoutManager
 * <p>
 * {@link FlexboxLayoutFragment}
 */
public class CatFlexboxLayoutFragment extends BaseSwipeFragment {

    Toolbar toolbar;
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_flexbox_cat;
    }

    @Override
    protected void initViews(View v) {
//        initTopBar(UIRouter.FRAG_WIDGET_FLEXBOX, true);
        mActivity.setSupportActionBar(toolbar);
        toolbar.setTitle(UIRouter.FRAG_WIDGET_FLEXBOX_CAT);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        toolbar=v.findViewById(R.id.toolbar);
        mRecyclerView=v.findViewById(R.id.recyclerview);

        // 1
        FlexboxLayoutManager manager
                = new FlexboxLayoutManager(mActivity, FlexDirection.ROW, FlexWrap.WRAP);
        // or
        //设置主轴排列方式
        /*manager.setFlexDirection(FlexDirection.ROW);*/
        //设置是否换行
        /*manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);*/
        mRecyclerView.setLayoutManager(manager);

        // 2
        CatRecyclerAdapter adapter = new CatRecyclerAdapter();
        mRecyclerView.setAdapter(adapter);

    }

    class CatRecyclerAdapter extends RecyclerView.Adapter<CatRecyclerAdapter.CatViewHolder> {

        private final int[] CAT_IMAGE_IDS = {
                R.drawable.cat_1,
                R.drawable.cat_2,
                R.drawable.cat_3,
                R.drawable.cat_4,
                R.drawable.cat_5
        };

        @NonNull
        @Override
        public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cat, parent, false);
            return new CatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
            int pos = position % CAT_IMAGE_IDS.length;
//        ImageView imageView = (ImageView) holder.itemView;
            holder.imageView.setImageResource(CAT_IMAGE_IDS[pos]);

            ViewGroup.LayoutParams lp = holder.imageView.getLayoutParams();
            if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                ((FlexboxLayoutManager.LayoutParams) lp).setFlexGrow(1f);
            }
        }

        @Override
        public int getItemCount() {
            return CAT_IMAGE_IDS.length * 4;
        }

        class CatViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            CatViewHolder(View view) {
                super(view);
                imageView=view.findViewById(R.id.imageview);
            }
        }
    }
}