package com.lzy.j2ee.server.impl.remote;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionInjectLocal;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessSessionInjectRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by lzy on 2017/8/11.
 */
@Stateless(name = Constant.StatelessSessionInjectRemote)
public class StatelessSessionInjectRemote implements IStatelessSessionInjectRemote {

    /**
     * 注入本地无状态会话Bean
     * @return
     */
    @EJB(beanName = Constant.StatelessSessionInjectLocal, beanInterface = IStatelessSessionInjectLocal.class)
    IStatelessSessionInjectLocal statelessSessionInjectLocal;

    @Override
    public String sayHello() {
        return statelessSessionInjectLocal.sayHello();
    }
}
