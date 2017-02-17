package com.lykj.mipinduobao.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshBase;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshScrollView;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.Fgt_TableHome;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.adapter.HomeTableAdapter;
import com.lykj.mipinduobao.adapter.Home_gridview_newadapter;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.bean.HomePagerBean;
import com.lykj.mipinduobao.bean.Home_gridview_new;
import com.lykj.mipinduobao.common.BaseFragment;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;
import com.lykj.mipinduobao.tools.OnShpinNumUi;
import com.lykj.mipinduobao.ui.act.Act_MyWeb;
import com.lykj.mipinduobao.ui.act.Act_Recharge;
import com.lykj.mipinduobao.ui.act.Act_Sign;
import com.lykj.mipinduobao.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员页 2016-1-16下午1:50:35
 */
public class Fgt_Home extends BaseFragment implements ApiCallback, OnShpinNumUi {
    private TabLayout myTable;
    private MyViewPager myViewPager;
    private String[] titles = {"最热", "最新", "高价", "低价"};
    private PullToRefreshScrollView myScrollView;
    private LinearLayout newLinear;
    private ApiHttp apiHttp;
    private Activity act;
    private ImageView myHomeImage;

    public void setAct(Activity act) {
        this.act = act;
    }

    @Override
    public int initLayoutId() {
        return R.layout.fgt_home;
    }

    @Override
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.hoame_relat_image1);
        setOnClickListener(R.id.hoame_relat_image2);
        setOnClickListener(R.id.hoame_relat_image3);
        setOnClickListener(R.id.hoame_relat_image4);
        setOnClickListener(R.id.home_lsit_qianggou1);
        setOnClickListener(R.id.home_lsit_qianggou2);
        setOnClickListener(R.id.home_lsit_qianggou3);
        setOnClickListener(R.id.home_lsit_qianggou4);
        setOnClickListener(R.id.home_zuixin_jiexiao_id);
        newLinear = getView(R.id.home_list_horizontall);
        myHomeImage = getViewAndClick(R.id.homelistview_item1);
        myTable = getView(R.id.tab_layout);
        myViewPager = getView(R.id.info_viewpager);
        myViewPager.setOffscreenPageLimit(4);
        myScrollView = getView(R.id.home_scrollview);
        setOnClickListener(R.id.iv_right);
        myScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        myScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pullDown2.pullDown(myViewPager, myScrollView);
                requestData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
        myViewPager.setScanScroll(false);
    }

    List<BaseFragment> fragments;

    @Override
    public void initData() {
        apiHttp = new ApiHttp(context);
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Fgt_TableHome fragment = new Fgt_TableHome();
            fragment.setShopinUI(this);//实现子fragment的回调购物车点击事件
            Bundle bundle = new Bundle();
            bundle.putString("indext", String.valueOf(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        HomeTableAdapter adapter = new HomeTableAdapter(myManager);
        adapter.setFragments(fragments);
        adapter.setContext(context);
        adapter.setTitles(titles);
        myViewPager.setAdapter(adapter);
        myTable.setupWithViewPager(myViewPager);
        myTable.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titles.length; i++) {//判断tablayout有几个菜单数量
            TabLayout.Tab tab = myTable.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));//将adapter设置好的获取图片和文字的方法设置到视图中
            }
        }
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                pullDown2.upDataUI(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        myViewPager.setCurrentItem(0);
        myScrollView.setFocusable(true);
        myScrollView.setFocusableInTouchMode(true);
        myScrollView.requestFocus();
        requestData();
    }

    @Override
    public void requestData() {
        apiHttp.postDataCallBack(1, ComantUtils.HttpUrl + "mobile/app_index_zxjx", this, true);
        apiHttp.postDataCallBack(2, ComantUtils.HttpUrl + "ajax/slides", this, true);
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onViewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.homelistview_item1://首页bannaer图
                intent.putExtra("stats", "item");
                intent.putExtra("id", "3");
                intent.setClass(context, Act_MyWeb.class);
                startActivityForResult(intent, 10);
                break;
            case R.id.hoame_relat_image1:// 即将揭晓
                onbuttonCurrent.setOnClick(1);
                break;
            case R.id.hoame_relat_image2:// 最新晒单
                intent.putExtra("stats", "shaidan");
                intent.setClass(context, Act_MyWeb.class);
                startActivityForResult(intent, 10);
                break;
            case R.id.hoame_relat_image3:// 夺宝记录
                if (ACache.get(context).getAsString("sign") != null) {
                    intent.putExtra("stats", "duobao");
                    intent.setClass(context, Act_MyWeb.class);
                    startActivityForResult(intent, 10);
                } else {
                    startAct(Act_Sign.class);
                }
                break;
            case R.id.hoame_relat_image4:// 账户充值
                if (ACache.get(context).getAsString("sign") != null) {
                    intent.setClass(context, Act_Recharge.class);
                    startActivityForResult(intent, 10);
                } else {
                    startAct(Act_Sign.class);
                }
                break;
            case R.id.home_lsit_qianggou1://零元
                onbuttonCurrent.setOnClick(1);
                onbutton.setOnClick("zero");
                break;
            case R.id.home_lsit_qianggou2://10元
                onbuttonCurrent.setOnClick(1);
                onbutton.setOnClick("10z");
                break;
            case R.id.home_lsit_qianggou3://100元
                onbuttonCurrent.setOnClick(1);
                onbutton.setOnClick("100z");
                break;
            case R.id.home_lsit_qianggou4://限时
                onbuttonCurrent.setOnClick(1);
                onbutton.setOnClick("xg");
                break;
            case R.id.home_zuixin_jiexiao_id://最新揭晓
                onbuttonCurrent.setOnClick(2);
                break;
            case R.id.iv_right:
//                startAct(Act_ZhiFu.class);
                break;
        }
    }

    @Override
    public void sendMsg(int flag, Object obj) {

    }

    private FragmentManager myManager;

    public void setMyManager(FragmentManager myManager) {
        this.myManager = myManager;
    }

    public static OnPullDown pullDown2;

    public static void setPull(OnPullDown pullDown) {
        pullDown2 = pullDown;
    }

    private Home_gridview_newadapter adapter = null;
    private HomePagerBean bean1;

    @Override
    public void onApiSuccess(int status, Object resultData) {
        Gson gson = new Gson();
        switch (status) {
            case 1:
                newLinear.removeAllViews();
                Home_gridview_new bean = gson.fromJson(resultData.toString(), Home_gridview_new.class);
                adapter = new Home_gridview_newadapter(context, bean.getList());
                for (int i = 0; i < bean.getList().size(); i++) {
                    View convertView = LayoutInflater.from(context).inflate(R.layout.home_gridview_list, null);
                    LinearLayout myLiner = getView(convertView, R.id.myLiner);
                    Display display = act.getWindowManager().getDefaultDisplay();
                    int widt = display.getWidth() / 3;
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) myLiner.getLayoutParams();
                    params.width = widt;
                    myLiner.setLayoutParams(params);
                    ImageView image = getView(convertView, R.id.home_gridview_list_image);
                    TextView tv = getView(convertView, R.id.home_gridview_list_text);
                    ImageView gifview = getView(convertView, R.id.home_gridview_list_gif2);
                    Home_gridview_new.ListBean item = bean.getList().get(i);
                    tv.setText(item.getQ_username());
                    if (item.getThumb().length() > 5) {
                        gifview.setVisibility(View.GONE);
                        image.setVisibility(View.VISIBLE);
                        Glide.with(context).load(item.getThumb()).placeholder(R.drawable.appmofen).into(image);
                    } else {
                        image.setVisibility(View.GONE);
                        gifview.setVisibility(View.VISIBLE);
                    }
                    convertView.setTag(i);
                    convertView.setOnClickListener(v -> {
                        int indext = (int) v.getTag();
                        Intent intent = new Intent();
                        intent.putExtra("id", String.valueOf(bean.getList().get(indext).getId()));
                        intent.putExtra("stats", "jiexiaojg");
                        intent.setClass(context, Act_MyWeb.class);
                        startActivityForResult(intent, 10);

                    });
                    newLinear.addView(convertView, i);
                }
                break;
            case 2:
                bean1 = gson.fromJson(resultData.toString(), HomePagerBean.class);
                Glide.with(context).load(bean1.getListItems().get(0).getSrc()).into(myHomeImage);
                break;
        }


    }

    @Override
    public void onApiError(int status, String errors) {
        MyToast.show(context, errors);
    }


    public interface OnPullDown {
        void pullDown(MyViewPager myViewPager, PullToRefreshScrollView myScrollView);

        void upDataUI(int indext);
    }

    /**
     * buuton的点击事件
     */
    private static OnButtonClick onbutton;

    public static void setHomeButonClick(OnButtonClick onButtonClick) {
        onbutton = onButtonClick;
    }

    public interface OnButtonClick {
        void setOnClick(String id);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == 5) {
                onbuttonCurrent.setOnClick(4);
            }
        }
    }

    /**
     * 页面的切换
     */
    //此接口定义实现控制替换当前页面
    private OnButtonCurrent onbuttonCurrent;

    public void setHomeButonCurrent(OnButtonCurrent onButtonClick) {
        onbuttonCurrent = onButtonClick;
    }

    public interface OnButtonCurrent {
        void setOnClick(int id);
    }

    /**
     * 商品点击购物车事件
     *
     * @return
     */
    private OnShpinNumUi onshpinNum;

    public void setShopinUI(OnShpinNumUi onshpin) {
        this.onshpinNum = onshpin;
    }

    /**
     * 实现子fragment的回调
     * 购物车点击实现回调处
     * @param bean
     */
    @Override
    public void onUpNumUI(CartListBean bean) {
        onshpinNum.onUpNumUI(bean);
    }
}
