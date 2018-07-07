package com.lzy.j2ee.server.impl.local;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionInjectLocal;

import javax.ejb.Stateless;

/**
 * Created by lzy on 2017/8/11.
 */
@Stateless(name = Constant.StatelessSessionInjectLocal)
public class StatelessSessionInjectLocal implements IStatelessSessionInjectLocal {

    @Override
    public String sayHello() {
        return "Test Inject Success！";
    }
}
