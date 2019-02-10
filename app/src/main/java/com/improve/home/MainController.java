package com.improve.home;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.improve.R;
import com.improve.base.QDItemDescription;
import com.improve.base.adapter.BaseRecyclerAdapter;
import com.improve.base.adapter.RecyclerViewHolder;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.view.GridDividerItemDecoration;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author javakam
 * @date 2018-6-16
 */
public abstract class MainController extends FrameLayout {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MainControlListener mMainControlListener;
    private MainItemAdapter mMainItemAdapter;
    private int mDiffRecyclerViewSaveStateId = QMUIViewHelper.generateViewId();

    public MainController(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.main_layout, this);
        ButterKnife.bind(this);
        initTopBar();
        initRecyclerView();
    }

    protected void startFragment(BaseSwipeFragment fragment) {
        if (mMainControlListener != null) {
            mMainControlListener.startFragment(fragment);
        }
    }

    protected void startActivity(Activity activity) {
        if (mMainControlListener != null) {
            mMainControlListener.startActivity(activity);
        }
    }

    public void setMainControlListener(MainControlListener homeControlListener) {
        mMainControlListener = homeControlListener;
    }

    protected abstract String getTitle();

    private void initTopBar() {
        mTopBar.setTitle(getTitle());

//        mTopBar.addRightImageButton(R.mipmap.icon_topbar_about, R.id.topbar_right_about_button).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                QDAboutFragment fragment = new QDAboutFragment();
//                startFragment(fragment);
//            }
//        });
    }

    private void initRecyclerView() {
        mMainItemAdapter = getItemAdapter();
        mMainItemAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                QDItemDescription item = mMainItemAdapter.getItem(pos);
                try {
                    Object o = item.getDemoClass().newInstance();
                    if (o instanceof BaseSwipeFragment) {
                        BaseSwipeFragment fragment = (BaseSwipeFragment) o;
                        startFragment(fragment);
                    } else if (o instanceof Activity) {
                        Activity activity = (Activity) o;
                        startActivity(activity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mRecyclerView.setAdapter(mMainItemAdapter);
        int spanCount = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), spanCount));
    }

    protected abstract MainItemAdapter getItemAdapter();

    public interface MainControlListener {
        void startFragment(BaseSwipeFragment fragment);

        void startActivity(Activity activity);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        int id = mRecyclerView.getId();
        mRecyclerView.setId(mDiffRecyclerViewSaveStateId);
        super.dispatchSaveInstanceState(container);
        mRecyclerView.setId(id);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        int id = mRecyclerView.getId();
        mRecyclerView.setId(mDiffRecyclerViewSaveStateId);
        super.dispatchRestoreInstanceState(container);
        mRecyclerView.setId(id);
    }

    static class MainItemAdapter extends BaseRecyclerAdapter<QDItemDescription> {

        public MainItemAdapter(Context ctx, List<QDItemDescription> data) {
            super(ctx, data);
        }

        @Override
        public int getItemLayoutId(int viewType) {
            return R.layout.main_item_layout;
        }

        @Override
        public void bindData(RecyclerViewHolder holder, int position, QDItemDescription item) {
            holder.getTextView(R.id.item_name).setText(item.getName());
            if (item.getIconRes() != 0) {
                holder.getImageView(R.id.item_icon).setImageResource(item.getIconRes());
            }
        }
    }
}
