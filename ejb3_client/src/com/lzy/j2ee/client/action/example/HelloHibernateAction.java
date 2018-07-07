package com.lzy.j2ee.client.action.example;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.service.example.IHelloHibernateServiceLocal;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
public class HelloHibernateAction extends ActionSupport implements ModelDriven<HelloHibernate>{

    private HelloHibernate helloHibernate;

    private static IHelloHibernateServiceLocal helloHibernateServiceLocal = (IHelloHibernateServiceLocal) EjbHelper
            .localByJndi(EjbHelper.getJndi(IHelloHibernateServiceLocal.class,
                    Constant.HelloHibernateServiceImpl));

    public String insert() throws IOException {
        String result = helloHibernateServiceLocal.insert(helloHibernate);

        ServletActionContext.getResponse().getWriter().write(result);
        ServletActionContext.getResponse().getWriter().close();
        return null;
    }

    public HelloHibernate getHelloHibernate() {
        return helloHibernate;
    }

    public void setHelloHibernate(HelloHibernate helloHibernate) {
        this.helloHibernate = helloHibernate;
    }

    @Override
    public HelloHibernate getModel() {
        if (this.helloHibernate == null){
            this.helloHibernate = new HelloHibernate();
        }
        return this.helloHibernate;
    }
}
