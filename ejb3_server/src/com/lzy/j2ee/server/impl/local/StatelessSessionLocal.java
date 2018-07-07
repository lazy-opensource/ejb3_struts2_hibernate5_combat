package com.lzy.j2ee.server.impl.local;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionLocal;

import javax.ejb.Stateless;

/**
 * Created by laizhiyuan on 2017/8/16.
 */
@Stateless(name = Constant.StatelessSessionLocal)
public class StatelessSessionLocal implements IStatelessSessionLocal {

    @Override
    public String sayHello(String name) {

        return "Local Say: Hello " + name;
    }
}
