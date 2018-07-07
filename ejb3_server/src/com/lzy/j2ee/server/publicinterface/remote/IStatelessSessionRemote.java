package com.lzy.j2ee.server.publicinterface.remote;

import javax.ejb.Remote;

/**
 * Created by lzy on 2017/8/7.
 */
@Remote
public interface IStatelessSessionRemote {

    public String sayHello(String name);
}
