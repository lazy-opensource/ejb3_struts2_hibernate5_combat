package com.lzy.j2ee.server.publicinterface.local;

import javax.ejb.Local;

/**
 * Created by lzy on 2017/8/19.
 */
@Local
public interface IStatelessSessionWebServiceLocal {

    public String sayHello(String name);
}
