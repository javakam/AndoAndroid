package com.improve.view;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.improve.common.Constant;
import com.improve.utils.StringUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * {@link QMUITipDialog }的工具类
 * <p>
 *
 * @author javakam
 * @date 2018/6/18
 */
public class TipDialogUtils {

    private static final TipDialogUtils ourInstance = new TipDialogUtils();

    public static TipDialogUtils getInstance() {
        return ourInstance;
    }

    private TipDialogUtils() {
    }

    private QMUITipDialog tipDialog;
    private QMUITipDialog.Builder builder;

    /**
     * 创建简单的 Loading 对话框
     *
     * @param context
     */
    public void createSimpleLoadingTipDialog(@NonNull Context context) {
        this.createLoadingTipDialog(context, null);
    }

    /**
     * 创建 Loading 对话框
     *
     * @param context
     * @param tipWord 提示的文字
     */
    public void createLoadingTipDialog(@NonNull Context context, @Nullable String tipWord) {
        if (StringUtils.isBlank(tipWord)) {
            tipWord = Constant.DIALOG_LOADING;
        }
        this.createIconWithTipDialog(context, QMUITipDialog.Builder.ICON_TYPE_LOADING, tipWord);
    }

    /**
     * {@link QMUITipDialog.Builder }
     * <p>
     * ICON_TYPE_NOTHING    不显示任何icon
     * <p>
     * ICON_TYPE_LOADING    显示 Loading 图标
     * <p>
     * ICON_TYPE_SUCCESS   显示成功图标
     * <p>
     * ICON_TYPE_FAIL      显示失败图标
     * <p>
     * ICON_TYPE_INFO      显示信息图标 -- 请勿重复操作 etc
     *
     * @param context
     * @param tipWord
     */
    public void createIconWithTipDialog(@NonNull Context context, @Nullable int iconType,
                                        @Nullable String tipWord) {
        if (tipDialog == null) {
            builder = new QMUITipDialog.Builder(context)
                    .setIconType(iconType)
                    .setTipWord(tipWord);
            tipDialog = builder.create();
            tipDialog.show();
        } else {
            dismiss();
        }
    }

    /**
     * 自定义内容提示框
     *
     * @param context
     */
    public void createCustomDialog(@NonNull Context context, @LayoutRes int layout) {
        if (tipDialog == null) {
            tipDialog = new QMUITipDialog.CustomBuilder(context)
                    .setContent(layout)
                    .create();
            tipDialog.show();
        } else {
            dismiss();
        }
    }

    /**
     * 隐藏对话
     */
    public void hide() {
        tipDialog.cancel();
        if (tipDialog != null) {
            if (tipDialog.isShowing()) {
                tipDialog.hide();
            }
        }
    }

    /**
     * 销毁对话
     */
    public void dismiss() {
        if (tipDialog != null) {
            if (tipDialog.isShowing()) {
                tipDialog.hide();
            }
            tipDialog.dismiss();
            tipDialog = null;
        }
    }
}
