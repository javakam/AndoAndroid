package com.shiqing.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.shiqing.retrofit.ciba.GetRequest_Interface_CIBA;
import com.shiqing.retrofit.ciba.TranslationCIBA;
import com.shiqing.retrofit.common.CommonApi;
import com.shiqing.retrofit.common.CommonRxApi;
import com.shiqing.retrofit.webservice.WebServiceActivity;
import com.shiqing.retrofit.youdao.PostRequest_Interface_YouDao;
import com.shiqing.retrofit.youdao.TranslateYouDao;
import com.sq.domain.cnodc.AssetBook;
import com.sq.domain.cnodc.CocBookBean;
import com.sq.domain.dao.CocBookBeanDao;
import com.sq.domain.dao.DaoUtils;
import com.sq.library.widget.SQTipDialogUtil;

import org.greenrobot.greendao.rx.RxDao;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit入门
 * Created by mcb on 2018-3-20 11:39:50
 * <p>
 * http://blog.csdn.net/carson_ho/article/details/73732076
 */
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/sq/mainactivity")
public class MainActivity extends AppCompatActivity {
    private final static int DEFUALT_TIME_OUT = 60;

    private Unbinder unbinder;
    @BindView(R2.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R2.id.tvResult)
    TextView tvResult;

    private void initTopBar() {
        if (mTopBar != null) {
            mTopBar.setTitle("Retrofit");
            mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.finish();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopBar();
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        //通用测试接口的方法
        findViewById(R.id.btCommon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonRequest(
                        initRetrofit("http://www.wanandroid.com/tools/mockapi/2018/"));
            }
        });
        //通用测试 Retrofit + RxJava
        findViewById(R.id.btCommonRx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonRequestRx(
                        initRetrofit("http://www.wanandroid.com/tools/mockapi/2018/"));
            }
        });
        //金山词霸
        findViewById(R.id.btCiba).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cibaRequest(initRetrofit("http://fy.iciba.com/"));
            }
        });
        //有道翻译
        findViewById(R.id.btYouDao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youdaoRequest(initRetrofit("http://fanyi.youdao.com/"));
            }
        });
        //XML WebService
        findViewById(R.id.btWebService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebServiceActivity.class));
            }
        });
    }

    private Retrofit initRetrofit(String url) {
        //打印拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("123", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)//添加打印拦截器
                .connectTimeout(DEFUALT_TIME_OUT, TimeUnit.SECONDS)//设置请求超时时间
                .readTimeout(DEFUALT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFUALT_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();

        return new Retrofit.Builder()
                .baseUrl(url) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .client(httpClient)
                .build();
    }

    /**
     * 通过 Retrofit + RxJava 请求成功后，用 GreenDao 保存到数据库
     *
     * @param retrofit
     */
    private void commonRequestRx(Retrofit retrofit) {
        SQTipDialogUtil.getInstance().createSimpleLoadingTipDialog(this);
        // 获取 CocBookBeanDao 的 Dao
        final CocBookBeanDao dao = DaoUtils.getDao().getCocBookBeanDao();
        final RxDao<CocBookBean, String> rxDao = dao.rx();
        // 步骤5:创建 网络请求接口 的实例
        CommonRxApi request = retrofit.create(CommonRxApi.class);
        Observer<AssetBook> bookObserver = request.getAssetBook()
//                .compose(new ObservableTransformer<AssetBook, AssetBook>() {
//                    @Override
//                    public ObservableSource<AssetBook> apply(Observable<AssetBook> upstream) {
//                        rxDao.insertOrReplaceInTx(upstream.)
//                        return Observable.create(rxDao.insertOrReplaceInTx(upstream.blockingIterable()));
//                    }
//                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<AssetBook>() {
                    @Override
                    public void accept(AssetBook assetBook) throws Exception {
                        System.out.println("doOnNext assetBook size :" + assetBook.getData().size());
                        dao.insertOrReplaceInTx(assetBook.getData());
                        System.out.println("插入成功!");
                        SystemClock.sleep(10000);
                    }
                })
                .subscribeWith(new Observer<AssetBook>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("MainActivity.onSubscribe");
                    }

                    @Override
                    public void onNext(AssetBook assetBook) {
                        System.out.println("MainActivity.onNext");
                        System.out.println("onNext assetBook size :" + assetBook.getData().size());
                        List<CocBookBean> list = assetBook.getData();
                        StringBuilder sb = new StringBuilder("Net Content : \n");
                        for (CocBookBean book : list) {
                            sb.append(book.toString()).append("\n\n");
                        }
                        tvResult.setText(sb.toString());
                        System.out.println("请求成功");
                    }

                    @Override
                    public void onError(Throwable t) {
                        tvResult.setText("请求失败" + t.getMessage());
                        System.out.println("请求失败" + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("MainActivity.onComplete");
                        SQTipDialogUtil.getInstance().dismiss();
                    }
                });
    }

    private void commonRequest(Retrofit retrofit) {
        // 步骤5:创建 网络请求接口 的实例
        CommonApi request = retrofit.create(CommonApi.class);
        Call<AssetBook> call = request.getAssetBook();
        call.enqueue(new Callback<AssetBook>() {
            @Override
            public void onResponse(Call<AssetBook> call, Response<AssetBook> response) {
                AssetBook body = response.body();
                List<CocBookBean> list = body.getData();
                StringBuilder sb = new StringBuilder("Net Content : \n");
                for (CocBookBean book : list) {
                    sb.append(book.toString()).append("\n\n");
                }
                tvResult.setText(sb.toString());
                System.out.println("请求成功");
            }

            @Override
            public void onFailure(Call<AssetBook> call, Throwable t) {
                tvResult.setText("请求失败" + t.getMessage());
                System.out.println("请求失败" + t.getMessage());
            }
        });
    }

    private void youdaoRequest(Retrofit retrofit) {
        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface_YouDao request = retrofit.create(PostRequest_Interface_YouDao.class);
        Call<TranslateYouDao> call = request.getCall("I love you");
        call.enqueue(new Callback<TranslateYouDao>() {
            @Override
            public void onResponse(Call<TranslateYouDao> call, Response<TranslateYouDao> response) {
                // 输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<TranslateYouDao> call, Throwable t) {
                System.out.println("请求失败");
                System.out.println(t.getMessage());
            }
        });
    }

    private void cibaRequest(Retrofit retrofit) {
        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface_CIBA request = retrofit.create(GetRequest_Interface_CIBA.class);
        //对 发送请求 进行封装
        Call<TranslationCIBA> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<TranslationCIBA>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<TranslationCIBA> call, Response<TranslationCIBA> response) {
                // 步骤7：处理返回的数据结果
                System.out.println("连接成功");
                response.body().show();
                response.errorBody();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<TranslationCIBA> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
