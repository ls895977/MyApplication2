package com.lykj.mipinduobao.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author：LiShan
 * Creation time：2017/2/3 0003
 */

public class WXPayBean {

    /**
     * pay : {"appid":"wxbc0f9aeb945c7eff","partnerid":"1293836501","prepayid":"wx2017020310491619cca8f3a70410805275","noncestr":"x97e4lxog8o1kdbtboww4z2hpt4x9t37","timestamp":"1485971104","package":"Sign=WXPay","sign":"C09D9D1A1620FAF861BD9664FE496D30"}
     * state : 0
     * appid : wxbc0f9aeb945c7eff
     * partnerid : 1293836501
     * prepayid : wx2017020310491619cca8f3a70410805275
     * noncestr : x97e4lxog8o1kdbtboww4z2hpt4x9t37
     * timestamp : 1485971104
     * package : Sign=WXPay
     * sign : C09D9D1A1620FAF861BD9664FE496D30
     */

    private String pay;
    private int state;
    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;
    @SerializedName("package")
    private String packageX;
    private String sign;

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
