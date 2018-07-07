package com.lzy.j2ee.server.impl.remote;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessSessionRemote;

import javax.ejb.Stateless;

/**
 * Created by lzy on 2017/8/7.
 */
@Stateless(name = Constant.StatelessSessionRemote)
public class StatelessSessionRemote implements IStatelessSessionRemote {

    @Override
    public String sayHello(String name) {

        return "Remote Say: Hello " + name;
    }
}
