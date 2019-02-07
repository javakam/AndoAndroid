package com.improve.modules.widgets.smarttable;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.utils.DensityUtils;
import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.utils.UiUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2018/7/19.
 */
public class SmartTableSwipeFragment extends BaseSwipeFragment {
    private SmartTable<ResultBean> table;
    private FloatingActionButton floatButton;
    //演示数据
    private List<ResultBean> resultBeans = new ArrayList<>();

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_smarttable;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("SmartTable", true);

        FontStyle.setDefaultTextSize(DensityUtils.sp2px(mActivity, 15));
        table = (SmartTable) v.findViewById(R.id.table);
        floatButton = v.findViewById(R.id.floatButton);
        //  定制SmartTable显示效果
        makeTableNice();
        //  模拟扫描完成更新行状态（颜色等）
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!resultBeans.isEmpty()) {
                    changeTable();
                }
            }
        });
        getData();
    }

    /**
     * 定制SmartTable显示效果
     */
    private void makeTableNice() {
//        FontStyle.setDefaultTextSize(DensityUtils.sp2px(mActivity, 15));
        int dp5 = DensityUtils.dp2px(mActivity, 10);
        table.getConfig().setVerticalPadding(dp5)
                .setTextLeftOffset(dp5);
        //展示Table名
        table.getConfig().setShowTableTitle(true);
        //X序号列
        table.getConfig().setShowXSequence(false);
        //Y序号列
        table.getConfig().setShowYSequence(true);
        //固定列标题
        table.getConfig().setFixedTitle(true);
        table.getConfig().setColumnTitleBackground(new BaseBackgroundFormat(
                ContextCompat.getColor(mActivity, android.R.color.holo_purple)));
        table.getConfig().setColumnTitleVerticalPadding(14);
        //设置最小宽度 - 不铺满屏幕宽度情况下会很丑。。。
        table.getConfig().setMinTableWidth(UiUtil.getScreenWidth(mActivity));
    }

    /**
     * 模拟扫描完成更新行状态（颜色等）
     */
    private void changeTable() {
        /*
        有无差异就说的是房间是不是一样,数量是不是一样,是不是同一个东西这些。
        盘点到的情况下,才区分有无差异
        "diff": "0",   // 1 有差异  0 无差异
        "status": "1"   // 1盘点到，-1未盘到，0多盘 , 2未盘点-default
         */
        //1模拟扫描
        for (int i = 0; i < resultBeans.size(); i++) {
            ResultBean result = resultBeans.get(i);
            if (i == 2 || i == 5) {//模拟 diff=0  status=1
                result.setColor(ContextCompat.getColor(mActivity, android.R.color.holo_green_light));
                result.setDiff("0");
                result.setStatus("1");
            } else if (i == 9) {//模拟 diff=1  status=1
                result.setColor(ContextCompat.getColor(mActivity, android.R.color.holo_orange_light));
                result.setDiff("1");
                result.setStatus("1");
            }
        }
        //模拟一条多盘的 diff=""  status=0
        ResultBean resultExtra = new ResultBean();
        resultExtra.setId("63F6C0D4FF49B5C7674B893B77A5AA39");
        resultExtra.setTaskId("PL219C1" + (int) Math.random() * 10);
        resultExtra.setTaskNum("G400040001180621016");
        resultExtra.setZcfdd("通信干扰器");
        resultExtra.setAnln1("10100000621" + (int) Math.random() * 10);
        resultExtra.setColor(ContextCompat.getColor(mActivity, android.R.color.darker_gray));
        resultExtra.setDiff("");
        resultExtra.setStatus("0");
        resultBeans.add(resultExtra);

        //2重置颜色
        table.getConfig().setContentCellBackgroundFormat(new BaseCellBackgroundFormat<CellInfo>() { // 设置行颜色
            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                int color = 0;
                for (int i = 0; i < resultBeans.size(); i++) {
                    color = resultBeans.get(i).getColor();
                    if (color != Color.WHITE) {
                        if (cellInfo.row == i) {
                            return color;
                        }
                    }
                }
//                if (cellInfo.row == 3 || cellInfo.row == 6) {
//                    return ContextCompat.getColor(mActivity, R.color.colorAccent);
//                }
                return Color.WHITE;
            }
        });
        table.setData(resultBeans);
    }

    public void getData() {
        //直接设置Map
       /* List<Object> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("资产名称", "台式机");
        map.put("账面数量", 1);
        map.put("扫描数量", 1);
        map.put("单位", "台");
        map.put("规格型号", "45*50");
        list.add(map);
        map = new HashMap<>();
        map.put("资产名称", "笔记本");
        map.put("账面数量", 2);
        map.put("扫描数量", 3);
        map.put("单位", "台");
        map.put("规格型号", "45*50");
        list.add(map);
        MapTableData tableData = MapTableData.create("测试", list);
        table.setTableData(tableData);*/

        //Json
//        String json = "{\"name\":\"BeJson\",\"url\":\"http://www.bejson.com\",\"page\":88,\"isNonProfit\":true,\"links\":[{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"},{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"},{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"},{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"}]}";
        for (int i = 0; i < 10; i++) {
            ResultBean result = new ResultBean();
            result.setId("80F6C0D4FF49B5C7674B893B77A5CC" + (i * 9));
            result.setTaskId("PL318D2" + (i * 8));
            result.setTaskNum("G400040001180621018");
            result.setZcfdd("通信干扰器");
            result.setAnln1("10100000284" + i);
            result.setDiff("");
            result.setStatus("2");
            result.setColor(ContextCompat.getColor(mActivity, android.R.color.white));
            resultBeans.add(result);
        }
//        MapTableData tableData = MapTableData.create("Json表格", JsonHelper.jsonToMapList(json));
//        table.setTableData(tableData);
        table.setData(resultBeans);
    }
}
