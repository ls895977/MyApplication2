package com.lykj.myapplication.ui;

import android.view.View;

import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.myapplication.R;
import com.lykj.myapplication.bean.DataBean;
import com.lykj.myapplication.common.BaseActivity;
import com.lykj.myapplication.retrofits.Service;

import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author：LiShan
 * Creation time：2017/1/5 0005
 */

public class RXJAvaAct extends BaseActivity {
    private Flowable flowable;

    @Override
    public int initLayoutId() {
        return R.layout.act_rxjava;
    }

    @Override
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.my_fashe);
        setOnClickListener(R.id.my_fashe1);
        setOnClickListener(R.id.my_caozuofu);
    }

    @Override
    public void initData() {
        flowable = new Flowable() {
            @Override
            protected void subscribeActual(Subscriber s) {

            }
        }.subscribeOn(Schedulers.io())//开启无压力
                .observeOn(Schedulers.single());//错误处理;


    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.my_fashe:
                sendService();
                break;
            case R.id.my_fashe1:
                sendmy_fashe1();
                break;
            case R.id.my_caozuofu:
                sendmy_fashe2();
                break;
        }
    }

    public void sendService() {
        Flowable.just("Hello world").subscribe(s -> {
            Debug.e(s);
        });
    }

    public void sendmy_fashe1() {
        flowable.fromCallable(callable)
                .subscribe(consumer);//支持反应和背压
    }
    Callable callable = () -> {//发射
        DataBean dataBean = new DataBean();
        dataBean.setName("haha====");
        return dataBean;
    };

    Consumer<DataBean> consumer = s ->//接收
            Debug.e(s.getName());

    public void sendmy_fashe2() {
        flowable.fromCallable(callable1)
                .subscribe(consumer1);//订阅
    }

    Callable callable1 = () -> {//发射
        String mm = "呀。。。";
        return mm;
    };
    Consumer<String> consumer1 = s ->//接收
            Debug.e(s);
}
