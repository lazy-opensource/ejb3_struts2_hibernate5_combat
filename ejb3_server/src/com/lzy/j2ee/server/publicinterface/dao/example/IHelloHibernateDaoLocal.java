package com.lzy.j2ee.server.publicinterface.dao.example;

import com.lzy.j2ee.server.entity.HelloHibernate;

import javax.ejb.Local;

/**
 * Created by laizhiyuan on 2017/8/24.
 */
@Local
public interface IHelloHibernateDaoLocal {

    public String insert(HelloHibernate helloHibernate);
}
