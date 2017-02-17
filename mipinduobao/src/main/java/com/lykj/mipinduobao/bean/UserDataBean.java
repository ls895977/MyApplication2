package com.lykj.mipinduobao.bean;

/**
 * author：LiShan
 * Creation time：2017/1/19 0019
 */

public class UserDataBean {

    /**
     * msg : 登陆成功！
     * state : 0
     * user : {"uid":"6750","username":"157****9320","mobile":"15708319320","score":"100","jingyan":"0","money":"0.00","img":"http://m.mingpinduobao.com/statics/uploads/photo/member.jpg"}
     */

    private String msg;
    private int state;
    private UserBean user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * uid : 6750
         * username : 157****9320
         * mobile : 15708319320
         * score : 100
         * jingyan : 0
         * money : 0.00
         * img : http://m.mingpinduobao.com/statics/uploads/photo/member.jpg
         */

        private String uid;
        private String username;
        private String mobile;
        private String score;
        private String jingyan;
        private String money;
        private String img;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getJingyan() {
            return jingyan;
        }

        public void setJingyan(String jingyan) {
            this.jingyan = jingyan;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
