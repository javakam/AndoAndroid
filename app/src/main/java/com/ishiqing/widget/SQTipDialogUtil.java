package com.ishiqing.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * {@link QMUITipDialog }的工具类
 * <p>
 *
 * @author javakam
 * @date 2018/6/18
 */
public class SQTipDialogUtil {
    private static final SQTipDialogUtil ourInstance = new SQTipDialogUtil();

    public static SQTipDialogUtil getInstance() {
        return ourInstance;
    }

    private SQTipDialogUtil() {
    }

    private QMUITipDialog tipDialog;

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
            tipDialog = new QMUITipDialog.Builder(context)
                    .setIconType(iconType)
                    .setTipWord(tipWord)
                    .create();
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
