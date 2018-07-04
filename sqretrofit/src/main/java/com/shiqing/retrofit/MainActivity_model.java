package com.shiqing.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shiqing.retrofit.ciba.GetRequest_Interface_CIBA;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_model extends AppCompatActivity {
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRetrofit();
        net();
    }

    private void initRetrofit() {
        // 第2部分：在创建Retrofit实例时通过.baseUrl()设置
        retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .build();
        // 从上面看出：一个请求的URL可以通过 替换块 和 请求方法的参数 来进行动态的URL更新。
        // 替换块是由 被{}包裹起来的字符串构成
        // 即：Retrofit支持动态改变网络请求根目录
    }

    private void net() {
        //  具体使用
        GetRequest_Interface_CIBA service = retrofit.create(GetRequest_Interface_CIBA.class);
        service.getCall();
        // @FormUrlEncoded
//        Call<ResponseBody> call1 = service.testFormUrlEncoded1("Carson", 24);

        //  @Multipart
//        RequestBody name = RequestBody.create(textType, "Carson");
//        RequestBody age = RequestBody.create(textType, "24");

//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
//        Call<ResponseBody> call3 = service.testFileUpload1(name, age, filePart);
    }
}
