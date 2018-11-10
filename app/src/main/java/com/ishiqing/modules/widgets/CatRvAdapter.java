package com.ishiqing.modules.widgets;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.ishiqing.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javakam on 2018/7/6.
 */
public class CatRvAdapter extends RecyclerView.Adapter<CatRvAdapter.CatViewHolder> {
    private int[] CAT_IMAGE_IDS = {
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

    protected class CatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview)
        ImageView imageView;

        public CatViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
