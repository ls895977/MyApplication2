package com.lykj.mipinduobao;

import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshScrollView;
import com.lykj.mipinduobao.adapter.Paixu_Gridview_Adapter;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.bean.HomeTableBean;
import com.lykj.mipinduobao.common.BaseFragment;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;
import com.lykj.mipinduobao.tools.OnShpinNumUi;
import com.lykj.mipinduobao.ui.fragment.Fgt_Home;
import com.lykj.mipinduobao.view.GridView_listview;
import com.lykj.mipinduobao.view.MyViewPager;

/**
 * author：LiShan
 * Creation time：2017/1/13 0013
 */

public class Fgt_TableHome extends BaseFragment implements ApiCallback, Fgt_Home.OnPullDown, AdapterView.OnItemClickListener, Paixu_Gridview_Adapter.OnClickNum {
    private String indext;
    private String URL = "";
    private ApiHttp apiHttp;
    private GridView_listview myGridView;
    private LoadingDialog loading;

    @Override
    public int initLayoutId() {
        return R.layout.fgt_homtable;
    }

    @Override
    public void initView() {
        hideHeader();
        indext = getArguments().getString("indext");
        myGridView = getView(R.id.home_listview_gridview_1);
        myGridView.setOnItemClickListener(this);
        Fgt_Home.setPull(this);
    }

    @Override
    public void initData() {
        apiHttp = new ApiHttp(context);
        loading = new LoadingDialog(context);
        requestData();
    }

    String hou0 = "mobile/app_renqi";

    @Override
    public void requestData() {
        loading.show("数据刷中...");
        switch (indext) {
            case "0":
                URL = ComantUtils.HttpUrl + hou0;
                break;
            case "1":
                URL = ComantUtils.HttpUrl + "mobile/app_renqi/20";
                break;
            case "2":
                URL = ComantUtils.HttpUrl + "mobile/app_renqi/30";
                break;
            case "3":
                URL = ComantUtils.HttpUrl + "mobile/app_renqi/40";
                break;
        }
        apiHttp.postDataCallBack(0, URL, this, true);
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

    Paixu_Gridview_Adapter adapte = null;
    HomeTableBean bean;

    @Override
    public void onApiSuccess(int status, Object resultData) {
        loading.dismiss();
        Gson gson = new Gson();
        bean = gson.fromJson(resultData.toString(), HomeTableBean.class);
        if (adapte == null) {
            adapte = new Paixu_Gridview_Adapter(context, bean.getList());
            myGridView.setAdapter(adapte);
        } else {
            adapte.refresh(bean.getList());
        }
        adapte.setContext(context);
        adapte.setOnclickNum(this);
    }

    @Override
    public void onApiError(int status, String errors) {
        loading.dismiss();
    }

    @Override
    public void pullDown(MyViewPager myViewPager, PullToRefreshScrollView myScrollView) {
        indext = String.valueOf(myViewPager.getCurrentItem());
        myScrollView.onRefreshComplete();
        requestData();
    }

    @Override
    public void upDataUI(int indext2) {
        indext = String.valueOf(indext2);
        hou0 = "mobile/app_renqi/10";
        requestData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        shoping.onShopingOnclick(bean.getList().get(position).getId());
    }


    /**
     * 商品详情回调
     */
    private static OnBackShoping shoping;

    public static void setOnBack(OnBackShoping shoping2) {
        shoping = shoping2;
    }

    /**
     * 购物车点击事件
     *
     * @param position
     */
    @Override
    public void backNum(int position) {
        CartListBean cartItem = new CartListBean();
        cartItem.setId(Long.valueOf(bean.getList().get(position).getId()));
        cartItem.setThumb(bean.getList().get(position).getThumb());
        cartItem.setQishu(bean.getList().get(position).getQishu());
        cartItem.setTitle(bean.getList().get(position).getTitle());
        cartItem.setZongrenshu(bean.getList().get(position).getZongrenshu());
        cartItem.setCanyurenshu(bean.getList().get(position).getCanyurenshu());
        cartItem.setShenyurenshu(bean.getList().get(position).getShenyurenshu());
        cartItem.setYunjiage(bean.getList().get(position).getYunjiage());
        onshpinNum.onUpNumUI(cartItem);
    }

    public interface OnBackShoping {
        void onShopingOnclick(String id);
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

}
