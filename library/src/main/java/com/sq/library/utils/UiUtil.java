package com.sq.library.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.transition.Fade;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Spinner;

import com.sq.library.R;

import java.util.List;

/**
 * Created by changbao on 2018/6/28.
 */
public class UiUtil {

    private UiUtil() {
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return toolbarHeight;
    }

    /**
     * 获取屏幕的宽
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = ((Activity) context).getWindowManager();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = ((Activity) context).getWindowManager();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }


    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static final int SCALE_FACTOR = 30;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void configureWindowEnterExitExplodeTransition(Window w) {
        Fade ex = new Fade();
        ex.setInterpolator(new PathInterpolator(0.4f, 0, 1, 1));
        ex.setDuration(5000);
        w.setExitTransition(ex);
        w.setEnterTransition(ex);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void configureFab(View fabButton) {
        int fabSize = fabButton.getContext().getResources()
                .getDimensionPixelSize(R.dimen.dp_56);
        Outline fabOutLine = new Outline();
        fabOutLine.setOval(0, 0, fabSize, fabSize);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static int getStatusBarHeight(Context c) {

        int result = 0;
        int resourceId = c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = c.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void hideRevealEffect(final View v, int centerX, int centerY, int initialRadius) {
        v.setVisibility(View.VISIBLE);
        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(
                v, centerX, centerY, initialRadius, 0);
        anim.setDuration(350);
        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void showRevealEffect(final View v, int centerX, int centerY, Animator.AnimatorListener lis) {
        v.setVisibility(View.VISIBLE);
        int height = v.getHeight();
        Animator anim = ViewAnimationUtils.createCircularReveal(
                v, centerX, centerY, 0, height);
        anim.setDuration(350);
        anim.addListener(lis);
        anim.start();
    }


    public static void hideViewByScale(View view) {
        ViewPropertyAnimator propertyAnimator = view.animate().setStartDelay(SCALE_FACTOR)
                .scaleX(0).scaleY(0);

        propertyAnimator.start();
    }

    public static void showViewByScale(View view) {
        ViewPropertyAnimator propertyAnimator = view.animate().setStartDelay(SCALE_FACTOR)
                .scaleX(1).scaleY(1);

        propertyAnimator.start();
    }


    public static int lighterColor(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public static int darkerColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a,
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
    }

    public static int getThemeColor(Context ctx, int attr) {
        TypedValue tv = new TypedValue();
        if (ctx.getTheme().resolveAttribute(attr, tv, true)) {
            return tv.data;
        }
        return 0;
    }

    public static int getThemeColorFromAttrOrRes(Context ctx, int attr, int res) {
        int color = getThemeColor(ctx, attr);
        if (color == 0) {
            color = ctx.getResources().getColor(res);
        }
        return color;
    }

    public static void clearAnimator(View v) {
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        v.setPivotY(v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null);
    }

    public static int getStatusHeight(Activity context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static void setSelectionForSp(List<String> dataSet, String keyWord, Spinner sp) {
        int position = -1;
        if (TextUtils.isEmpty(keyWord)) {
            return;
        }
        if (dataSet == null || dataSet.size() == 0) {
            return;
        }
        if (sp.getAdapter() == null) {
            return;
        }
        for (String item : dataSet) {
            position++;
            if (keyWord.equals(item)) {
                break;
            }
        }
        sp.setSelection(position == -1 ? 0 : position);
    }


    /**
     * 隐藏输入法
     *
     * @param context
     */
    public static void hideIputKeyboard(final Context context) {
        final Activity activity = (Activity) context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InputMethodManager mInputKeyBoard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                // mInputKeyBoard.isActive()
                if (activity.getCurrentFocus() != null) {
                    mInputKeyBoard.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                }
            }
        });
    }

    //=====================================01优酷菜单======================================//

    public static void hideView(ViewGroup view) {
        hideView(view, 500, 0);
    }

    public static void hideView(ViewGroup view, long startOffset) {
        hideView(view, 500, startOffset);
    }

    /**
     * 解决视图动画的BUG
     * <p>
     * 两种方案：
     * 一、遍历ViewGroup中的Child，动态设置setEnable属性；
     * 二、使用属性动画
     */
    public static void hideView(ViewGroup view, long duration, long startOffset) {
//        RotateAnimation ra = new RotateAnimation(0, 180, view.getWidth() / 2, view.getHeight());
//        ra.setDuration(duration);
//        ra.setFillAfter(true);
//        ra.setStartOffset(startOffset);
//        view.startAnimation(ra);
//        for (int i = 0; i < view.getChildCount(); i++) {
//            view.getChildAt(i).setEnabled(false);
//        }

        //还可以使用属性动画 - 此处提供的两种写法
        //设置旋转轴心
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 180);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);//设置动画时长
        set.setStartDelay(startOffset);//设置延时动画
        set.playTogether(animator);
        set.start();
        //属性动画2
//        view.animate().rotation()
    }

    public static void showView(ViewGroup view) {
        showView(view, 500);
    }

    public static void showView(ViewGroup view, long duration) {
//        RotateAnimation ra = new RotateAnimation(180, 360, view.getWidth() / 2, view.getHeight());
//        ra.setDuration(duration);
//        ra.setFillAfter(true);
//        view.startAnimation(ra);
        //处理视图动画移动后事件任在原处的问题
//        for (int i = 0; i < view.getChildCount(); i++) {
//            view.getChildAt(i).setEnabled(true);
//        }

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 180, 360);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);//设置动画时长
        set.setStartDelay(0);//设置延时动画
        set.playTogether(animator);
        set.start();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
    }
}
