package com.lzy.j2ee.server.publicinterface.local;

import javax.ejb.Local;

/**
 * Created by laizhiyuan on 2017/8/16.
 *
 * 实现注解了@Local的EJB 会话Bean，均具备本地的特点
 */
@Local
public interface IStatelessSessionLocal {

    public String sayHello(String name);
}
