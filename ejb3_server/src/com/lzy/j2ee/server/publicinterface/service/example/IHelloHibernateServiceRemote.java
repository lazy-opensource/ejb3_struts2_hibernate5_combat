package com.lzy.j2ee.server.publicinterface.service.example;

import com.lzy.j2ee.server.entity.HelloHibernate;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
public interface IHelloHibernateServiceRemote {

    public String insert(HelloHibernate helloHibernate);
}
