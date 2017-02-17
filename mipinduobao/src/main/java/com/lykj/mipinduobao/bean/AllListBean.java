package com.lykj.mipinduobao.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishan on 2017/1/15.
 */

public class AllListBean {


    /**
     * state : 0
     * list : [{"cateid":"0","name":"全部商品","durl":"/?/mobile/mobile/app_menudata/0/10/1","listzi":[{"cateid":"0","name":"全部商品","durl":"/?/mobile/mobile/app_menudata/0/10/1","listsun":[{"cateid":"0","name":"全部商品","durl":"/?/mobile/mobile/app_menudata/0/10/1"}]}]}]
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
         * cateid : 0
         * name : 全部商品
         * durl : /?/mobile/mobile/app_menudata/0/10/1
         * listzi : [{"cateid":"0","name":"全部商品","durl":"/?/mobile/mobile/app_menudata/0/10/1","listsun":[{"cateid":"0","name":"全部商品","durl":"/?/mobile/mobile/app_menudata/0/10/1"}]}]
         */
        private boolean isStata;

        public boolean isStata() {
            return isStata;
        }

        public void setStata(boolean stata) {
            isStata = stata;
        }

        private String cateid;
        private String name;
        private String durl;
        private List<ListziBean> listzi=new ArrayList<>();

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

        public String getDurl() {
            return durl;
        }

        public void setDurl(String durl) {
            this.durl = durl;
        }

        public List<ListziBean> getListzi() {
            return listzi;
        }

        public void setListzi(List<ListziBean> listzi) {
            this.listzi = listzi;
        }

        public static class ListziBean {
            /**
             * cateid : 0
             * name : 全部商品
             * durl : /?/mobile/mobile/app_menudata/0/10/1
             * listsun : [{"cateid":"0","name":"全部商品","durl":"/?/mobile/mobile/app_menudata/0/10/1"}]
             */
            private boolean ziStatas;

            public boolean isZiStatas() {
                return ziStatas;
            }

            public void setZiStatas(boolean ziStatas) {
                this.ziStatas = ziStatas;
            }

            private String cateid;
            private String name;
            private String durl;
            private List<ListsunBean> listsun=new ArrayList<>();

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

            public String getDurl() {
                return durl;
            }

            public void setDurl(String durl) {
                this.durl = durl;
            }

            public List<ListsunBean> getListsun() {
                return listsun;
            }

            public void setListsun(List<ListsunBean> listsun) {
                this.listsun = listsun;
            }

            public static class ListsunBean {
                /**
                 * cateid : 0
                 * name : 全部商品
                 * durl : /?/mobile/mobile/app_menudata/0/10/1
                 */

                private String cateid;
                private String name;
                private String durl;

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

                public String getDurl() {
                    return durl;
                }

                public void setDurl(String durl) {
                    this.durl = durl;
                }
            }
        }
    }
}
