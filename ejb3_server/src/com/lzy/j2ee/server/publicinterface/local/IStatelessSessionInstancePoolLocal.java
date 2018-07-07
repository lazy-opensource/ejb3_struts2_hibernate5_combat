package com.lzy.j2ee.server.publicinterface.local;

/**
 * Created by laizhiyuan on 2017/8/10.
 */
public interface IStatelessSessionInstancePoolLocal {

    /**
     * 测试当该实例空闲时，多个请求可能会返回同一个实例
     * @param name
     * @return
     */
    public String returnAlike(String name);

    /**
     * 测试当该实例被另一个请求使用时，实例池为其它请求新建或取出其它空闲的实例
     * @param name
     * @return
     */
    public String returnDifferent(String name);
}
