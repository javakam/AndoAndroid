package com.ishiqing.modules.network;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.UIRouter;
import com.ishiqing.base.fragment.BaseSwipeFragment;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 网络编程常用的API
 * <p>
 * 网络连接方式：3/4G 、 WIFI等
 * 查看网络连接状态：ConnectivityManager 、 NetworkInfo
 * 一些常用设置：
 * 1 系统设置网络的Action ：Settings.ACTION_WIRELESS_SETTINGS ；
 * 2 系统监听网络连接状态的广播接收者：android.net.conn.CONNECTIVITY_CHANGE
 * <p>
 * Created by javakam on 2018/6/26 0026.
 */
public class HttpUrlConnectionSwipeFragment extends BaseSwipeFragment {
    @BindView(R.id.tvNetWork)
    TextView tvNetWork;
    @Nullable
    @BindView(R.id.imgDownLoad)
    ImageView imgDownLoad;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_network;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_NETWORK, true);
    }

    @OnClick({R.id.btnGetActiveNet, R.id.btnGetAllNet, R.id.btnDownLoad, R.id.btnGoToUpload
            , R.id.btnDownAndInstallApk})
    void getNetConn(View v) {
        ConnectivityManager connManager
                = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        switch (v.getId()) {
            case R.id.btnGetActiveNet:
                // 网络连接
                NetworkInfo info = connManager.getActiveNetworkInfo();
                if (info != null) {
                    tvNetWork.setText(info.getTypeName() + "  " + info.getState());
                }
                break;
            case R.id.btnGetAllNet:
                // 获取全部网络连接
                NetworkInfo[] infos = connManager.getAllNetworkInfo();
                StringBuilder stringBuilder = new StringBuilder();
                for (NetworkInfo io : infos) {
                    stringBuilder.append(io.toString() + "\n\n");
                }
                tvNetWork.setText(stringBuilder.toString());
                break;
            case R.id.btnDownLoad:
                // 无网络时，通过  Notification 通知进入系统网络设置界面；
                // 有网络时，进行下载
                if (!NetTools.isNetAvaliable(mActivity)) {
                    // 在通知栏中显示通知
                    String text = "内容：点击设置";
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(mActivity);
                    // 跳转网络设置的系统页面 ：Settings.ACTION_WIRELESS_SETTINGS
                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pendingIntent = PendingIntent.getActivity(mActivity
                            , 100, intent, PendingIntent.FLAG_ONE_SHOT);// 0
                    builder.setTicker("设置网络连接！")//设置在第一个通知到达时显示在状态栏的文本
                            .setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                            .setContentTitle("标题：网络连接")//设置内容标题
                            .setStyle(new NotificationCompat.BigPictureStyle())//设置显示的样式
                            .setContentText(text)//设置显示的内容
                            .setNumber(100)//在通知的右侧设置一个数字
                            .setOngoing(false)//设置是否是一个可持续的通知
                            .setContentInfo("大文本")//设置右侧显示的文本，
                            .setAutoCancel(true)//设置此标志将使它以便当用户点击它在面板中的通知被自动取消
                            .setContentIntent(pendingIntent)
                            .setColor(Color.BLUE);
                    Notification notification = builder.build();
                    NotificationManagerCompat.from(mActivity).notify(0, notification);
                } else {
                    // 下载 HttpURLConnection API
                    download();
                }
                break;
            case R.id.btnGoToUpload:
                // 上传
                /*
                上传文件头文件的格式
                客户端实现文件上传
                 */
                mActivity.startFragment(new HttpUploadSwipeFragment());
                break;
            case R.id.btnDownAndInstallApk:
                //下载并安装 APK
                // http://www.gulixueyuan.com/course/112/task/1701/show
                ProgressBar progressBar = new ProgressBar(mActivity);

                break;
            default:
                break;
        }
    }

    private String img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530027345438&di=06c5f8e6fb39bd1788b5cf5e2e5df61a&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F4610b912c8fcc3cec52444bf9e45d688d53f2051.jpg";

    private void download() {
        new MyDownLoadAsyncTask().execute(img);
    }

    /**
     * 随手复习下AsyncTask，第一个参数是execute传入过来的，中间参数是进度值，最后一个参数是返回值
     */
    class MyDownLoadAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mTipDialogUtil.createIconWithTipDialog(mActivity, QMUITipDialog.Builder.ICON_TYPE_LOADING, "正在加载...");
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                //1 创建URL对象
                URL url = new URL(strings[0]);
                //2 通过URL获得HttpURLConnection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //3 基础设置
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setDoInput(true);// 向服务器写数据
                conn.connect();

                //4 得到相应代码
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("网络连接成功！");
                    InputStream inputStream = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mTipDialogUtil.dismiss();
            if (bitmap != null) {
                imgDownLoad.setImageBitmap(bitmap);
            }
            super.onPostExecute(bitmap);
        }
    }
}
