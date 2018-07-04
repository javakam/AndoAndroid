package com.shiqing.retrofit.webservice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class WebServiceActivity extends AppCompatActivity {

    private static final String TAG = "123";
    //    private ActivityMainBinding binding;//ActivityMainBinding
    private WeatherResponseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_service);
//        binding.rvElements.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 根据城市名称获取天气
     *
     * @param view
     */
    public void sendRequest(View view) {
//        if (TextUtils.isEmpty(binding.etCityName.getText())) {
//            Toast.makeText(this, getString(R.string.make_request), Toast.LENGTH_SHORT).show();
//        } else {
//            hideKeyboard();
//
//            //Only by retrofit
////            getWeatherbyCityName();
//
//            //by rxjava2 and retrofit
//            getWeatherbyCityNameWithRx();
//        }
    }

    /**
     * 用RxJava2+Retrofit结合的方式访问XML WebService
     */
    public void getWeatherbyCityNameWithRx() {
//        binding.progressbar.setVisibility(View.VISIBLE);
//        final RequestEnvelope requestEnvelop = new RequestEnvelope();
//        RequestBody requestBody = new RequestBody();
//        RequestModel requestModel = new RequestModel();
//        requestModel.theCityName = binding.etCityName.getText().toString();
//        requestModel.cityNameAttribute = "http://WebXml.com.cn/";
//        requestBody.getWeatherbyCityName = requestModel;
//        requestEnvelop.body = requestBody;
//
//        Log.w(TAG, requestEnvelop.toString());
//        RetrofitGenerator.getWeatherRxInterfaceApi()
//                .getWeatherbyCityNameWithRx(requestEnvelop)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResponseEnvelope>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: " + d);
//                    }
//
//                    @Override
//                    public void onNext(ResponseEnvelope responseEnvelope) {
//                        Log.d(TAG, "onNext: " + responseEnvelope.toString());
//                        binding.progressbar.setVisibility(View.GONE);
//                        if (responseEnvelope != null) {
//                            List<String> weatherResult = responseEnvelope.body.getWeatherbyCityNameResponse.result;
//                            adapter = new WeatherResponseAdapter(weatherResult);
//                            binding.rvElements.setAdapter(adapter);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: " + e.getMessage());
//                        binding.progressbar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete");
//                        binding.progressbar.setVisibility(View.GONE);
//                    }
//                });
    }

    /**
     * 根据城市名获取天气情况
     *
     * @return
     */
    public void getWeatherbyCityName() {
//        binding.progressbar.setVisibility(View.VISIBLE);
//        RequestEnvelope requestEnvelop = new RequestEnvelope();
//        RequestBody requestBody = new RequestBody();
//        RequestModel requestModel = new RequestModel();
//        requestModel.theCityName = binding.etCityName.getText().toString();
//        requestModel.cityNameAttribute = "http://WebXml.com.cn/";
//        requestBody.getWeatherbyCityName = requestModel;
//        requestEnvelop.body = requestBody;
//        Call<ResponseEnvelope> call = RetrofitGenerator.getWeatherInterfaceApi().getWeatherbyCityName(requestEnvelop);
//        call.enqueue(new Callback<ResponseEnvelope>() {
//            @Override
//            public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {
//                binding.progressbar.setVisibility(View.GONE);
//                ResponseEnvelope responseEnvelope = response.body();
//                if (responseEnvelope != null) {
//                    List<String> weatherResult = responseEnvelope.body.getWeatherbyCityNameResponse.result;
//                    adapter = new WeatherResponseAdapter(weatherResult);
//                    binding.rvElements.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseEnvelope> call, Throwable t) {
//                binding.progressbar.setVisibility(View.GONE);
//            }
//        });

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
}
