package com.lykj.mipinduobao.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshBase;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshGridView;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.Fgt_TableHome;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.adapter.AllListAdapter;
import com.lykj.mipinduobao.adapter.GridAdapter;
import com.lykj.mipinduobao.bean.AllListBean;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.bean.ShoPinDeatil;
import com.lykj.mipinduobao.common.BaseFragment;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;
import com.lykj.mipinduobao.tools.OnShpinNumUi;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有商品页 2016-1-16下午1:50:35
 */
public class Fgt_AllProduct extends BaseFragment implements ApiCallback, AdapterView.OnItemClickListener, AllListAdapter.OnCheageStats, GridAdapter.onAddShoping {
    private ListView myList;
    private PullToRefreshGridView myGridView;
    private ApiHttp apiHttp;
    private TabLayout myTable;
    private String[] titles = {"最热", "最新", "高价", "低价"};
    private int[] imageviewId = {R.drawable.send_selector,
            R.drawable.send_selector1,
            R.drawable.send_selector2,
            R.drawable.send_selector3};
    private LoadingDialog loading;
    private int shopingPager = 1;
    private int type = 10, initType = 10;
    private List<ShoPinDeatil.ListBean> shopDatas;
    private GridAdapter gridAdapter = null;
    private TextView noGridDatas;
    /**
     * 商品点击详表
     */
    private Fgt_TableHome.OnBackShoping onbackShoping;

    public void setShoping(Fgt_TableHome.OnBackShoping onshoping) {
        this.onbackShoping = onshoping;
    }
    @Override
    public int initLayoutId() {
        return R.layout.fgt_all;
    }

    @Override
    public void initView() {
        hideHeader();
        myTable = getView(R.id.all_table);
        myList = getView(R.id.all_List);
        noGridDatas = getView(R.id.noGridDatas);
        myGridView = getView(R.id.all_gridview);
        myList.setOnItemClickListener(this);
        loading = new LoadingDialog(context);
        myGridView = getView(R.id.all_gridview);
        myGridView.setMode(PullToRefreshBase.Mode.BOTH);
        myGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                shopingPager = 1;
                shopDatas.clear();
                requestData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                shopingPager++;
                requestData();
            }
        });
        myGridView.setOnItemClickListener(shopIitem);
        Fgt_Home.setHomeButonClick(id -> {//实现首页按钮回调
            shopDatas.clear();
            shopingPager = 1;
            menuID = id;
            loading.show("数据刷新中...");
            requestData();
        });
    }

    AdapterView.OnItemClickListener shopIitem = (parent, view, position, id) -> {//点击整个商品跳转到大图页
        onbackShoping.onShopingOnclick(shopDatas.get(position).getId());
    };

    @Override
    public void initData() {
        shopDatas = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {//判断tablayout有几个菜单数量
            TabLayout.Tab tab = myTable.newTab();
            if (tab != null) {
                tab.setCustomView(getTabView(i));//将adapter设置好的获取图片和文字的方法设置到视图中
            }
            if (i == 0)
                myTable.addTab(tab, true);
            else
                myTable.addTab(tab);
        }
        myTable.setTabMode(TabLayout.MODE_FIXED);
        myTable.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                type = initType * (tab.getPosition() + 1);
                loading.show("数据刷新中...");
                shopDatas.clear();
                shopingPager = 1;
                requestData();//求请商品数据
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        showLoading();
        apiHttp = new ApiHttp(context);
        apiHttp.postDataCallBack(1, ComantUtils.HttpUrl + "mobile/app_menu1", this, true);
        requestData();
    }

    @Override
    public void requestData() {
        apiHttp.postDataCallBack(4, ComantUtils.HttpUrl + "mobile/app_menudata/" + menuID + "/" + type + "/" + shopingPager, this, true);
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void sendMsg(int flag, Object obj) {

    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_tab, null);
        TextView textview = (TextView) view.findViewById(R.id.tab_tv1);
        ImageView imageview = (ImageView) view.findViewById(R.id.tab_iv1);
        textview.setText(titles[position]);
        view.setTag(String.valueOf(position));
        imageview.setImageResource(imageviewId[position]);
        return view;
    }

    List<AllListBean.ListBean> listDatas = new ArrayList<>();
    private AllListAdapter adapterList;

    @Override
    public void onApiSuccess(int status, Object resultData) {
        Gson gson = new Gson();
        switch (status) {
            case 1:
                AllListBean list = gson.fromJson(resultData.toString(), AllListBean.class);
                for (int i = 0; i < list.getList().size(); i++) {
                    listDatas.add(list.getList().get(i));
                    listDatas.get(i).setStata(false);
                }
                if (adapterList == null) {
                    adapterList = new AllListAdapter(context, listDatas, this);
                    myList.setAdapter(adapterList);
                } else {
                    adapterList.refresh(listDatas);
                }
                break;
            case 2:
                AllListBean list1 = gson.fromJson(resultData.toString(), AllListBean.class);
                listDatas.get(indext).getListzi().clear();//清空上次获取的二级目录信息
                if (list1.getList() != null && list1.getList().size() > 0) {//是否存在二级目录数据
                    listDatas.get(indext).setStata(true);//开启二级目录
                    for (int i = 0; i < list1.getList().size(); i++) {
                        AllListBean.ListBean.ListziBean bean = new AllListBean.ListBean.ListziBean();
                        bean.setZiStatas(false);
                        bean.setCateid(list1.getList().get(i).getCateid());
                        bean.setName(list1.getList().get(i).getName());
                        listDatas.get(indext).getListzi().add(bean);
                        listDatas.get(indext).getListzi().get(i).setZiStatas(false);
                    }
                    loading.dismiss();
                } else {
                    shopDatas.clear();
                    shopingPager = 1;
                    requestData();//求请商品数据
                    listDatas.get(indext).setStata(false);//关闭二级目录
                }
                adapterList.notifyDataSetChanged();
                break;
            case 3:
                AllListBean list2 = gson.fromJson(resultData.toString(), AllListBean.class);
                listDatas.get(indext).setStata(true);
                for (int i = 0; i < listDatas.get(indext).getListzi().size(); i++) {//清空所有三级目录
                    listDatas.get(indext).getListzi().get(i).getListsun().clear();
                }
                if (list2.getList() != null && list2.getList().size() > 0) {
                    listDatas.get(indext).getListzi().get(foriD).getListsun().clear();
                    listDatas.get(indext).getListzi().get(foriD).setZiStatas(true);
                    for (int i = 0; i < list2.getList().size(); i++) {
                        AllListBean.ListBean.ListziBean.ListsunBean bean = new AllListBean.ListBean.ListziBean.ListsunBean();
                        bean.setCateid(list2.getList().get(i).getCateid());
                        bean.setName(list2.getList().get(i).getName());
                        listDatas.get(indext).getListzi().get(foriD).getListsun().add(bean);
                    }
                    loading.dismiss();
                } else {
                    shopDatas.clear();
                    shopingPager = 1;
                    requestData();//求请商品数据
                }
                adapterList.notifyDataSetChanged();
                break;
            case 4:
                myGridView.onRefreshComplete();
                ShoPinDeatil bean = gson.fromJson(resultData.toString(), ShoPinDeatil.class);
                for (int i = 0; i < bean.getList().size(); i++) {
                    shopDatas.add(bean.getList().get(i));
                }
                if (gridAdapter == null) {
                    gridAdapter = new GridAdapter(context, shopDatas, this);
                    myGridView.setAdapter(gridAdapter);
                } else {
                    gridAdapter.refresh(shopDatas);
                }
                if (shopDatas.size() > 0) {
                    noGridDatas.setVisibility(View.GONE);
                    myGridView.setVisibility(View.VISIBLE);
                } else {
                    noGridDatas.setVisibility(View.VISIBLE);
                    myGridView.setVisibility(View.GONE);
                }
                showCView();
                loading.dismiss();
                break;
        }

    }

    @Override
    public void onApiError(int status, String errors) {
        myGridView.onRefreshComplete();
        MyToast.show(context, errors);
        loading.dismiss();
        showCView();
    }

    int indext = 0;

    //商品列表list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indext = position;
        if (listDatas.get(position).getListzi().size() > 0) {//排除有二级目录的点击事件
            listDatas.get(position).setStata(true);//控制是否显示二级目录
            if (foriD <= listDatas.get(position).getListzi().size() && listDatas.get(position).getListzi().get(foriD).getListsun().size() > 0) {
                listDatas.get(position).getListzi().get(foriD).setZiStatas(true);//控制是否显示三级目录
            }
            adapterList.notifyDataSetChanged();
            return;
        }
        menuID = listDatas.get(position).getCateid();
        loading.show("数据刷新中...");
        apiHttp.postDataCallBack(2, ComantUtils.HttpUrl + "mobile/app_menu2/" + listDatas.get(position).getCateid(), this, true);
    }

    //改变点击后的子布局状态
    @Override
    public void setOnstats() {
        listDatas.get(indext).setStata(false);
    }

    String menuID = "0";
    int foriD;

    @Override
    public void backOnclickView(View view, int forID) {//二级目录处理
        foriD = forID;
        menuID = String.valueOf(view.getTag());
        loading.show("数据刷新中...");
        apiHttp.postDataCallBack(3, ComantUtils.HttpUrl + "mobile/app_menu2/" + menuID, this, true);
    }

    @Override
    public void backSanOnclickView(View view, int forID) {//三级目录点击事件
        loading.show("数据刷新中...");
        menuID = String.valueOf(view.getTag());
        shopDatas.clear();
        shopingPager = 1;
        requestData();//求请商品数据
    }

    @Override
    public void setOnZiStats(int i) {
        listDatas.get(indext).getListzi().get(i).setZiStatas(false);
    }

    /**
     * 商品购物车点击事件
     * @param position
     */
    @Override
    public void addShoping(int position) {
        ShoPinDeatil.ListBean bean = shopDatas.get(position);
        CartListBean item = new CartListBean();
        item.setId(Long.valueOf(bean.getId()));
        item.setThumb(bean.getThumb());
        item.setQishu(bean.getQishu());
        item.setTitle(bean.getTitle());
        item.setZongrenshu(bean.getZongrenshu());
        item.setCanyurenshu(bean.getCanyurenshu());
        item.setShenyurenshu(bean.getShenyurenshu());
        item.setYunjiage(bean.getYunjiage());
        onshpinNum.onUpNumUI(item);
    }
    /**
     * 商品点击购物车事件
     * @return
     */
    private OnShpinNumUi onshpinNum;
    public void setShopinUI(OnShpinNumUi onshpin) {
        this.onshpinNum = onshpin;
    }
}
