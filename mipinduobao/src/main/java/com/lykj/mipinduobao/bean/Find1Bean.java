package com.lykj.mipinduobao.bean;

import java.io.Serializable;

/**
 * author：LiShan
 * Creation time：2017/1/19 0019
 */

public class Find1Bean implements Serializable{

    /**
     * state : 0
     * msg : 验证码发送成功！
     * name : 35edCQYCUlMJAgYHVAMLUAIFBFFUBVkEUw4EVABXVFUEWgQIDwFQUw
     */

    private int state;
    private String msg;
    private String name;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
