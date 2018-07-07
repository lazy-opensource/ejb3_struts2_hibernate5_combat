package com.lzy.j2ee.server.impl.webservice;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionWebServiceLocal;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessSessionWebServiceRemote;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Created by lzy on 2017/8/19.
 *
 * 发布Web Service只需将其注解为@WebService即可
 */
@Stateless(name = Constant.HelloWebService)
@WebService
public class HelloWebService implements IStatelessSessionWebServiceLocal, IStatelessSessionWebServiceRemote{

    @Override
    public String sayHello(String name) {
        return "Hello: " + name;
    }
}
