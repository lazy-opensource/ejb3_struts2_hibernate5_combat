package com.lzy.j2ee.server.publicinterface.remote;

import javax.ejb.Remote;

/**
 * Created by lzy on 2017/8/11.
 */
@Remote
public interface IStatelessSessionInjectRemote {

    public String sayHello();
}
