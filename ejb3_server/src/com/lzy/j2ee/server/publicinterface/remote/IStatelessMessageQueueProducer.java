package com.lzy.j2ee.server.publicinterface.remote;

/**
 * Created by laizhiyuan on 2017/8/14.
 */
public interface IStatelessMessageQueueProducer {

    public String send(String msg);
}
