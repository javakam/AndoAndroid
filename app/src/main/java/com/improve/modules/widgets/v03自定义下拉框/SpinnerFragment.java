package com.improve.modules.widgets.v03自定义下拉框;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.utils.UiUtil;

import java.util.ArrayList;

/**
 * 01优酷菜单 {@link UiUtil hideView() }
 * Created by machangbao on 2018/10/1.
 */
public class SpinnerFragment extends BaseSwipeFragment {

    protected EditText et_input;
    protected ImageView iv_down_arrow;
    //
    private PopupWindow popupWindow;
    private ListView listview;
    private MyAdapter myAdapter;

    private ArrayList<String> msgs;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_v03_sp;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_SPINNER, true);
        et_input = v.findViewById(R.id.et_input);
        iv_down_arrow = v.findViewById(R.id.iv_down_arrow);

        et_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (popupWindow == null) {
                    popupWindow = new PopupWindow(mActivity);
                    popupWindow.setWidth(et_input.getWidth());
                    int height = UiUtil.dip2px(mActivity, 200);//dp->px
                    Toast.makeText(mActivity, "height==" + height, Toast.LENGTH_SHORT).show();
                    popupWindow.setHeight(height);//px

                    popupWindow.setContentView(listview);
                    popupWindow.setFocusable(true);//设置焦点
                }

                popupWindow.showAsDropDown(et_input, 0, 0);
            }
        });

        listview = new ListView(mActivity);
        listview.setBackgroundResource(R.drawable.listview_background);
        //准备数据
        msgs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            msgs.add(i + "--aaaaaaaaaaaaaa--" + i);
        }
        myAdapter = new MyAdapter();
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1.得到数据
                String msg = msgs.get(position);
                //2.设置输入框
                et_input.setText(msg);

                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return msgs.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.item_spinner_youku, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_msg = (TextView) convertView.findViewById(R.id.tv_msg);
                viewHolder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到数据
            final String msg = msgs.get(position);
            viewHolder.tv_msg.setText(msg);

            //设置删除
            viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //1.从集合中删除
                    msgs.remove(msg);
                    //2.刷新ui-适配器刷新
                    myAdapter.notifyDataSetChanged();//getCount()-->getView();
                }
            });
            return convertView;
        }
    }

    static class ViewHolder {
        TextView tv_msg;
        ImageView iv_delete;
    }
}
