package com.lzy.j2ee.server.impl.remote;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.remote.IHelloEjbRemote;
import javax.ejb.Stateless;

/**
 * Created by laizhiyuan on 2017/8/15.
 *
 * 注解@Stateless表示这是个无状态会话Bean
 * 关于无状态和有状态会话Bean,后面章节有讲解
 *
 * name：表示JNDI节点名称
 */
@Stateless(name = Constant.HelloEjb)
public class HelloEjbRemote implements IHelloEjbRemote {

    @Override
    public String sayHello(String name) {
        return "Hello: " + name;
    }
}
