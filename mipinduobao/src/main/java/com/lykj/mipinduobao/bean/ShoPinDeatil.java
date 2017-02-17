package com.lykj.mipinduobao.bean;

import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/17 0017
 */

public class ShoPinDeatil {

    /**
     * sumpage : 1
     * count : 6
     * state : 0
     * list : [{"id":"1352","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20160411/36675200353323.jpg","qishu":"1","title":"爱的承诺","zongrenshu":"60","canyurenshu":"10","shenyurenshu":"50","yunjiage":"1.00"},{"id":"1354","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20160411/11576000353970.jpg","qishu":"1","title":"永远快乐&nbsp;","zongrenshu":"60","canyurenshu":"3","shenyurenshu":"57","yunjiage":"1.00"},{"id":"1358","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20160411/84006400354560.jpg","qishu":"1","title":"宠爱你","zongrenshu":"60","canyurenshu":"3","shenyurenshu":"57","yunjiage":"1.00"},{"id":"1353","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20160411/51831600353586.jpg","qishu":"1","title":"想你&nbsp;","zongrenshu":"60","canyurenshu":"0","shenyurenshu":"60","yunjiage":"1.00"},{"id":"1359","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20160411/44966800355198.jpg","qishu":"1","title":"温暖&nbsp;","zongrenshu":"60","canyurenshu":"0","shenyurenshu":"60","yunjiage":"1.00"},{"id":"1357","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20160411/97202000354272.jpg","qishu":"1","title":"母爱","zongrenshu":"60","canyurenshu":"0","shenyurenshu":"60","yunjiage":"1.00"}]
     */

    private int sumpage;
    private int count;
    private int state;
    private List<ListBean> list;

    public int getSumpage() {
        return sumpage;
    }

    public void setSumpage(int sumpage) {
        this.sumpage = sumpage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1352
         * thumb : http://m.mingpinduobao.com/statics/uploads/shopimg/20160411/36675200353323.jpg
         * qishu : 1
         * title : 爱的承诺
         * zongrenshu : 60
         * canyurenshu : 10
         * shenyurenshu : 50
         * yunjiage : 1.00
         */

        private String id;
        private String thumb;
        private String qishu;
        private String title;
        private String zongrenshu;
        private String canyurenshu;
        private String shenyurenshu;
        private String yunjiage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getQishu() {
            return qishu;
        }

        public void setQishu(String qishu) {
            this.qishu = qishu;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getYunjiage() {
            return yunjiage;
        }

        public void setYunjiage(String yunjiage) {
            this.yunjiage = yunjiage;
        }
    }
}
