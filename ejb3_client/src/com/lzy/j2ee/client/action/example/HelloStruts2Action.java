package com.lzy.j2ee.client.action.example;

/**
 * Created by laizhiyuan on 2017/8/16.
 */
public class HelloStruts2Action {

    public String execute(){

        System.out.println("===================> Hello Struts2");
        return "success";
    }
}
