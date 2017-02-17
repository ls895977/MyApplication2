package com.lykj.mipinduobao.bean;

import java.util.List;

/**
 * Created by lishan on 2017/1/15.
 */

public class AllListZiBean {
    /**
     * state : 0
     * list : [{"cateid":"127","name":"沈阳市"}]
     */

    private int state;
    private List<ListBean> list;

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
         * cateid : 127
         * name : 沈阳市
         */

        private String cateid;
        private String name;

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
