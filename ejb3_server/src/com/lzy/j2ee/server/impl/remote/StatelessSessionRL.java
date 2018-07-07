package com.lzy.j2ee.server.impl.remote;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionLocal;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessSessionRemote;

import javax.ejb.Stateless;

/**
 * Created by laizhiyuan on 2017/8/16.
 *
 * 这里实现两接口，一个是注解为本地，一个注解为远程
 */
@Stateless(name = Constant.StatelessSessionRL)
public class StatelessSessionRL implements IStatelessSessionLocal, IStatelessSessionRemote {


    @Override
    public String sayHello(String name) {
        return "RL sayHello: " + name;
    }
}
