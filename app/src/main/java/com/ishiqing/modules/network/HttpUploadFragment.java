package com.ishiqing.modules.network;

import android.os.SystemClock;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    private String URL_LOGIN_WANANDROID = "http://www.wanandroid.com/user/login";
    //    private String URL_PROJECT_TREE = "http://www.wanandroid.com/project/tree/json";
    private String URL_PROJECT_TREE = "https://www.sojson.com/open/api/weather/json.shtml?city=北京";

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
                        get();
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    /**
     * POST方式
     *
     * @param params
     */
    private void login(Map<String, String> params) {
        StringBuilder strBuilder = new StringBuilder(URL_LOGIN_WANANDROID);
        if (!params.isEmpty()) {
            strBuilder.append("&");
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> set : entrySet) {
                strBuilder.append(set.getKey()).append("=").append(set.getValue()).append("&");
            }
            strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
        }
        System.out.println(strBuilder.toString());
        try {
            URL url = new URL(strBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("登录成功!");
                InputStream is = conn.getInputStream();
                byte[] bytes = new byte[2048];
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                while (bufferedReader.readLine() != null) {
                    SystemClock.sleep(1000);
                    System.out.println(bufferedReader.read() + "\n");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * GET請求
     */
    private void get() {
        try {
            URL url = new URL(URL_PROJECT_TREE);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            int code = conn.getResponseCode();
            System.out.println("code = " + code);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("GET项目分类Success!");
                InputStream is = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println(builder.toString());
//                byte[] bytes = new byte[2048];
//                if (is.read(bytes) != -1) {
//                    System.out.println(new String(bytes, 0, bytes.length));
//                } else {
//                    System.out.println("读取失败");
//                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
