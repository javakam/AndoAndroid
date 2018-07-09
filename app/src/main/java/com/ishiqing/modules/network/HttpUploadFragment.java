package com.ishiqing.modules.network;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;
import com.sq.library.utils.L;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件上传
 * <p>
 * https://ke.qq.com/webcourse/index.html#course_id=120050&term_id=100131722&taid=942668012180722&vid=b14103nbfrb
 * <p>
 * 接口来自玩Android：
 * http://www.wanandroid.com/blog/show/2
 * <p>
 * Created by javakam on 2018/6/26 0026.
 */
public class HttpUploadFragment extends BaseFragment {
    @BindView(R.id.tvResult)
    TextView tvResult;

    private String URL_LOGIN_WANANDROID = "http://www.wanandroid.com/user/login";
    //    private String URL_PROJECT_TREE = "http://www.wanandroid.com/project/tree/json";
    private String URL_PROJECT_TREE = "https://www.sojson.com/open/api/weather/json.shtml";// ?city=北京

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_network_upload;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_NETWORK_UPLOAD, true);
    }

    @OnClick({R.id.btnUpload, R.id.btnGetProjectClass})
    void getNetConn(View v) {

        switch (v.getId()) {
            case R.id.btnUpload:
                // 登录操作
                /*
                主线程联网出现错误：
                 E/AndroidRuntime: FATAL EXCEPTION: main
                     android.os.NetworkOnMainThreadException
                     at android.os.StrictMode$AndroidBlockGuardPolicy.onNetwork(StrictMode.java:1208)
                     ...
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("username", "javakam");
                        map.put("password", "lovekam12");
                        login(map);
                    }
                }).start();
                break;
            case R.id.btnGetProjectClass:
                // 获取项目分类
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("city", "北京");
                        get(map);
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvResult.setText(String.valueOf(msg.obj));
        }
    };

    /**
     * POST方式   // TODO 2018年7月9日 WANAndroid 登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
     *
     * @param params
     */
    private void login(Map<String, String> params) {
        StringBuilder strBuilder = new StringBuilder();
        if (!params.isEmpty()) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> set : entrySet) {
                strBuilder.append(set.getKey()).append("=").append(set.getValue()).append("&");
            }
            strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
            L.i("POST request params : " + strBuilder.toString());
        }
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(URL_LOGIN_WANANDROID);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            // 发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // 发送我们的数据 POST专属！
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(strBuilder.toString());
            out.flush();
            out.close();

            // 读取返回的数据
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                L.i("登录成功!");
                InputStream is = conn.getInputStream();
                byte[] bytes = new byte[2048];
                bufferedReader = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                Message msg = new Message();
                msg.obj = response.toString();
                mHandler.sendMessage(msg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();//你请求的网址如果不正确 这里会报错
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * GET請求  // TODO 2018年7月9日 {"message":"Check the parameters."}   HTTPS登录问题
     */
    private void get(Map<String, String> params) {
        StringBuilder strBuilder = new StringBuilder(URL_PROJECT_TREE);
        if (!params.isEmpty()) {
            strBuilder.append("?");
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> set : entrySet) {
                strBuilder.append(set.getKey()).append("=").append(set.getValue()).append("&");
            }
            strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
            L.i("GET request params: " + strBuilder.toString());
        }

        try {
            URL url = new URL(strBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            //Get请求不需要DoOutPut
            conn.setDoInput(true);
            conn.setDoOutput(false);
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            int code = conn.getResponseCode();
            L.i("code = " + code);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                L.i("GET请求北京天气成功!");
                InputStream is = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                L.i(builder.toString());
                Message msg = new Message();
                msg.obj = builder.toString();
                mHandler.sendMessage(msg);
//                byte[] bytes = new byte[2048];
//                if (is.read(bytes) != -1) {
//                    L.i(new String(bytes, 0, bytes.length));
//                } else {
//                    L.i("读取失败");
//                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
