package com.lzy.j2ee.server.publicinterface.local;

import javax.ejb.Local;

/**
 * Created by lzy on 2017/8/11.
 */
@Local
public interface IStatelessSessionInjectLocal {

    public String sayHello();
}
