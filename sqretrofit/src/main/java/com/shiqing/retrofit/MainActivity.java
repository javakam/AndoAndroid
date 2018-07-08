package com.shiqing.retrofit;

import android.content.Intent;
import android.os.Bundle;
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
import com.sq.domain.cnodc.AssetCheck;
import com.sq.domain.cnodc.CocBookBean;
import com.sq.domain.cnodc.CocTaskHeaders;
import com.sq.domain.cnodc.CocTaskLines;
import com.sq.domain.dao.CocBookBeanDao;
import com.sq.domain.dao.CocTaskHeadersDao;
import com.sq.domain.dao.CocTaskLinesDao;
import com.sq.domain.dao.DaoUtils;
import com.sq.library.widget.SQTipDialogUtil;

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
        //通用测试 Retrofit + RxJava  -- 获取台账
        findViewById(R.id.btCommonRx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonRequestRx(initRetrofit("http://www.wanandroid.com/tools/mockapi/2018/"));
            }
        });
        //通用测试 Retrofit + RxJava  -- 获取任务单
        findViewById(R.id.btCommonRx2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonRequestRx2(initRetrofit("http://www.wanandroid.com/tools/mockapi/2018/"));
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
     * <p>
     * https://blog.csdn.net/u012943767/article/details/52036600
     *
     * @param retrofit
     */
    private void commonRequestRx(Retrofit retrofit) {
        SQTipDialogUtil.getInstance().createSimpleLoadingTipDialog(this);
        // 获取 CocBookBeanDao 的 Dao
        final CocBookBeanDao dao = DaoUtils.getDao().getCocBookBeanDao();
//        final RxDao<CocBookBean, String> rxDao = dao.rx();

        // 步骤5:创建 网络请求接口 的实例
        CommonRxApi request = retrofit.create(CommonRxApi.class);
        Observer<AssetBook> bookObserver = request.getAssetBook()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<AssetBook>() {
                    @Override
                    public void accept(AssetBook assetBook) throws Exception {
                        System.out.println("doOnNext assetBook size :" + assetBook.getData().size());
                        dao.insertOrReplaceInTx(assetBook.getData());
                        System.out.println("插入成功!");
//                        SystemClock.sleep(10000);
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
                        StringBuilder sb = new StringBuilder("台账 : \n");
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

    /**
     * 得到数据后，在 doOnNext 存入，onNext 获取
     *
     * @param retrofit
     */
    private void commonRequestRx2(Retrofit retrofit) {
        SQTipDialogUtil.getInstance().createSimpleLoadingTipDialog(this);
        // 获取 CocBookBeanDao 的 Dao
        final CocTaskHeadersDao headersDao = DaoUtils.getDao().getCocTaskHeadersDao();
        final CocTaskLinesDao linesDao = DaoUtils.getDao().getCocTaskLinesDao();
//        final RxDao<CocTaskHeaders, String> headersStringRxDao = headersDao.rx();
//        final RxDao<CocTaskLines, String> linesStringRxDao = linesDao.rx();

        // 网络请求  doOnNext 存入，onNext中获取
        CommonRxApi request = retrofit.create(CommonRxApi.class);
        Observer<AssetCheck> checkObserver = request.getAssetCheck()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<AssetCheck>() {
                    @Override
                    public void accept(AssetCheck assetCheck) throws Exception {
                        System.out.println("onNext headerList size :" + assetCheck.getHeaderList().size()
                                + " , lineList : " + assetCheck.getLineList().size());
                        headersDao.insertOrReplaceInTx(assetCheck.getHeaderList());
                        linesDao.insertOrReplaceInTx(assetCheck.getLineList());
                        System.out.println("插入成功! AssetCheck");
                        // TODO 18/7/8 如果插入的数据过多，应该 通过 compose 将 RxDao 压合到网络请求的流里面
//                        SystemClock.sleep(10000);
                    }
                })
                .subscribeWith(new Observer<AssetCheck>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("MainActivity.onSubscribe");
                    }

                    @Override
                    public void onNext(AssetCheck assetCheck) {
                        System.out.println("MainActivity.onNext");
                        System.out.println("onNext headerList size :" + assetCheck.getHeaderList().size()
                                + " , lineList : " + assetCheck.getLineList().size());
                        // 换种方式，我们从 本地读取(From Dao)
//                        List<CocTaskHeaders> headerList = assetCheck.getHeaderList();
//                        List<CocTaskLines> linesList = assetCheck.getLineList();
                        List<CocTaskHeaders> headerList = headersDao.loadAll();
                        List<CocTaskLines> linesList = linesDao.loadAll();

                        StringBuilder sb = new StringBuilder("任务单 : \n");
                        for (CocTaskHeaders header : headerList) {
                            sb.append(header.toString()).append("\n\n");
                        }
                        sb.append("CocTaskHeaders ↑ =============== ↓ CocTaskLines").append("\n\n");
                        for (CocTaskLines line : linesList) {
                            sb.append(line.toString()).append("\n\n");
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
