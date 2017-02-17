package com.lykj.mipinduobao.bean;

import java.util.List;

/**
 * Created by lishan on 2017/1/16.
 */

public class ProuctDetallBean {
    /**
     * iszerobuy : false
     * state : 0
     * item : {"id":"1240","sid":"1184","cateid":"86","brandid":"152","title":"中华软包 两条装","title_style":"","title2":"","keywords":"","description":"","money":"1469.00","zmoney":null,"zhigou":null,"yunjiage":"1.00","zongrenshu":"1469","canyurenshu":"5","shenyurenshu":"1464","def_renshu":"0","qishu":"4","maxqishu":"60000","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20151204/93593885201117.jpg","picarr":["shopimg/20151204/73849496201123.jpg"],"content":"<p><img src=\"http://db.kulehu.cn/statics/uploads/shopimg/20151204/88441914201134.jpg\" title=\"675076523037573965.jpg\"/><\/p>","codes_table":"shopcodes_1","xsjx_time":"0","pos":"0","renqi":"0","time":"1449824432","order":"1","q_uid":null,"q_user":null,"q_user_code":null,"q_uid_a":"0","q_user_assign":"","q_content":null,"q_counttime":null,"q_end_time":null,"win_action":"N","q_showtime":"N","renqipos":"0","newpos":"0","bannershop":"0","posthumb":"photo/goods.jpg","quyu":"0","color":null,"mods":null,"q_username":""}
     * shaidan : []
     * sumpinglun : 0
     */

    private boolean iszerobuy;
    private int state;
    private ItemBean item;
    private int sumpinglun;
    private List<?> shaidan;

    public boolean isIszerobuy() {
        return iszerobuy;
    }

    public void setIszerobuy(boolean iszerobuy) {
        this.iszerobuy = iszerobuy;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public int getSumpinglun() {
        return sumpinglun;
    }

    public void setSumpinglun(int sumpinglun) {
        this.sumpinglun = sumpinglun;
    }

    public List<?> getShaidan() {
        return shaidan;
    }

    public void setShaidan(List<?> shaidan) {
        this.shaidan = shaidan;
    }

    public static class ItemBean {
        /**
         * id : 1240
         * sid : 1184
         * cateid : 86
         * brandid : 152
         * title : 中华软包 两条装
         * title_style :
         * title2 :
         * keywords :
         * description :
         * money : 1469.00
         * zmoney : null
         * zhigou : null
         * yunjiage : 1.00
         * zongrenshu : 1469
         * canyurenshu : 5
         * shenyurenshu : 1464
         * def_renshu : 0
         * qishu : 4
         * maxqishu : 60000
         * thumb : http://m.mingpinduobao.com/statics/uploads/shopimg/20151204/93593885201117.jpg
         * picarr : ["shopimg/20151204/73849496201123.jpg"]
         * content : <p><img src="http://db.kulehu.cn/statics/uploads/shopimg/20151204/88441914201134.jpg" title="675076523037573965.jpg"/></p>
         * codes_table : shopcodes_1
         * xsjx_time : 0
         * pos : 0
         * renqi : 0
         * time : 1449824432
         * order : 1
         * q_uid : null
         * q_user : null
         * q_user_code : null
         * q_uid_a : 0
         * q_user_assign :
         * q_content : null
         * q_counttime : null
         * q_end_time : null
         * win_action : N
         * q_showtime : N
         * renqipos : 0
         * newpos : 0
         * bannershop : 0
         * posthumb : photo/goods.jpg
         * quyu : 0
         * color : null
         * mods : null
         * q_username :
         */

        private String id;
        private String sid;
        private String cateid;
        private String brandid;
        private String title;
        private String title_style;
        private String title2;
        private String keywords;
        private String description;
        private String money;
        private Object zmoney;
        private Object zhigou;
        private String yunjiage;
        private String zongrenshu;
        private String canyurenshu;
        private String shenyurenshu;
        private String def_renshu;
        private String qishu;
        private String maxqishu;
        private String thumb;
        private String content;
        private String codes_table;
        private String xsjx_time;
        private String pos;
        private String renqi;
        private String time;
        private String order;
        private Object q_uid;
        private Object q_user;
        private Object q_user_code;
        private String q_uid_a;
        private String q_user_assign;
        private Object q_content;
        private Object q_counttime;
        private Object q_end_time;
        private String win_action;
        private String q_showtime;
        private String renqipos;
        private String newpos;
        private String bannershop;
        private String posthumb;
        private String quyu;
        private Object color;
        private Object mods;
        private String q_username;
        private List<String> picarr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getBrandid() {
            return brandid;
        }

        public void setBrandid(String brandid) {
            this.brandid = brandid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_style() {
            return title_style;
        }

        public void setTitle_style(String title_style) {
            this.title_style = title_style;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public Object getZmoney() {
            return zmoney;
        }

        public void setZmoney(Object zmoney) {
            this.zmoney = zmoney;
        }

        public Object getZhigou() {
            return zhigou;
        }

        public void setZhigou(Object zhigou) {
            this.zhigou = zhigou;
        }

        public String getYunjiage() {
            return yunjiage;
        }

        public void setYunjiage(String yunjiage) {
            this.yunjiage = yunjiage;
        }

        public String getZongrenshu() {
            return zongrenshu;
        }

        public void setZongrenshu(String zongrenshu) {
            this.zongrenshu = zongrenshu;
        }

        public String getCanyurenshu() {
            return canyurenshu;
        }

        public void setCanyurenshu(String canyurenshu) {
            this.canyurenshu = canyurenshu;
        }

        public String getShenyurenshu() {
            return shenyurenshu;
        }

        public void setShenyurenshu(String shenyurenshu) {
            this.shenyurenshu = shenyurenshu;
        }

        public String getDef_renshu() {
            return def_renshu;
        }

        public void setDef_renshu(String def_renshu) {
            this.def_renshu = def_renshu;
        }

        public String getQishu() {
            return qishu;
        }

        public void setQishu(String qishu) {
            this.qishu = qishu;
        }

        public String getMaxqishu() {
            return maxqishu;
        }

        public void setMaxqishu(String maxqishu) {
            this.maxqishu = maxqishu;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCodes_table() {
            return codes_table;
        }

        public void setCodes_table(String codes_table) {
            this.codes_table = codes_table;
        }

        public String getXsjx_time() {
            return xsjx_time;
        }

        public void setXsjx_time(String xsjx_time) {
            this.xsjx_time = xsjx_time;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getRenqi() {
            return renqi;
        }

        public void setRenqi(String renqi) {
            this.renqi = renqi;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public Object getQ_uid() {
            return q_uid;
        }

        public void setQ_uid(Object q_uid) {
            this.q_uid = q_uid;
        }

        public Object getQ_user() {
            return q_user;
        }

        public void setQ_user(Object q_user) {
            this.q_user = q_user;
        }

        public Object getQ_user_code() {
            return q_user_code;
        }

        public void setQ_user_code(Object q_user_code) {
            this.q_user_code = q_user_code;
        }

        public String getQ_uid_a() {
            return q_uid_a;
        }

        public void setQ_uid_a(String q_uid_a) {
            this.q_uid_a = q_uid_a;
        }

        public String getQ_user_assign() {
            return q_user_assign;
        }

        public void setQ_user_assign(String q_user_assign) {
            this.q_user_assign = q_user_assign;
        }

        public Object getQ_content() {
            return q_content;
        }

        public void setQ_content(Object q_content) {
            this.q_content = q_content;
        }

        public Object getQ_counttime() {
            return q_counttime;
        }

        public void setQ_counttime(Object q_counttime) {
            this.q_counttime = q_counttime;
        }

        public Object getQ_end_time() {
            return q_end_time;
        }

        public void setQ_end_time(Object q_end_time) {
            this.q_end_time = q_end_time;
        }

        public String getWin_action() {
            return win_action;
        }

        public void setWin_action(String win_action) {
            this.win_action = win_action;
        }

        public String getQ_showtime() {
            return q_showtime;
        }

        public void setQ_showtime(String q_showtime) {
            this.q_showtime = q_showtime;
        }

        public String getRenqipos() {
            return renqipos;
        }

        public void setRenqipos(String renqipos) {
            this.renqipos = renqipos;
        }

        public String getNewpos() {
            return newpos;
        }

        public void setNewpos(String newpos) {
            this.newpos = newpos;
        }

        public String getBannershop() {
            return bannershop;
        }

        public void setBannershop(String bannershop) {
            this.bannershop = bannershop;
        }

        public String getPosthumb() {
            return posthumb;
        }

        public void setPosthumb(String posthumb) {
            this.posthumb = posthumb;
        }

        public String getQuyu() {
            return quyu;
        }

        public void setQuyu(String quyu) {
            this.quyu = quyu;
        }

        public Object getColor() {
            return color;
        }

        public void setColor(Object color) {
            this.color = color;
        }

        public Object getMods() {
            return mods;
        }

        public void setMods(Object mods) {
            this.mods = mods;
        }

        public String getQ_username() {
            return q_username;
        }

        public void setQ_username(String q_username) {
            this.q_username = q_username;
        }

        public List<String> getPicarr() {
            return picarr;
        }

        public void setPicarr(List<String> picarr) {
            this.picarr = picarr;
        }
    }
}
