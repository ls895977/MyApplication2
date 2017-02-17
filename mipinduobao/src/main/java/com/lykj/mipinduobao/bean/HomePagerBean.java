package com.lykj.mipinduobao.bean;

import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/14 0014
 */

public class HomePagerBean {

    /**
     * state : 0
     * listItems : [{"alt":"#53117c","url":"http://m.mingpinduobao.com/?/mobile/mobile/item/3","src":"http://m.mingpinduobao.com/statics/uploads/banner/20150620/29783988772079.png","width":"614PX","height":"110PX"}]
     */

    private int state;
    private List<ListItemsBean> listItems;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ListItemsBean> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItemsBean> listItems) {
        this.listItems = listItems;
    }

    public static class ListItemsBean {
        /**
         * alt : #53117c
         * url : http://m.mingpinduobao.com/?/mobile/mobile/item/3
         * src : http://m.mingpinduobao.com/statics/uploads/banner/20150620/29783988772079.png
         * width : 614PX
         * height : 110PX
         */

        private String alt;
        private String url;
        private String src;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

    }
}
