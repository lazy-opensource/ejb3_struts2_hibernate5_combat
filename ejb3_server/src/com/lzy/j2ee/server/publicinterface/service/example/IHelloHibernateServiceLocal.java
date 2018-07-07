package com.lzy.j2ee.server.publicinterface.service.example;

import com.lzy.j2ee.server.entity.HelloHibernate;

import javax.ejb.Remote;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Remote
public interface IHelloHibernateServiceLocal {

    public String insert(HelloHibernate helloHibernate);
}
