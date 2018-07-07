package com.lzy.j2ee.server.publicinterface.remote;

import javax.ejb.Remote;

/**
 * Created by laizhiyuan on 2017/8/15.
 *
 * 注解@Remote标示实现这个接口的会话Bean,属于远程会话，可以供不同的JVM进行远程调用,底层技术采用RMI
 */
@Remote
public interface IHelloEjbRemote {

    public String sayHello(String name);
}
