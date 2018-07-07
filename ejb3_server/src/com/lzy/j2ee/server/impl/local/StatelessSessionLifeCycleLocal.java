package com.lzy.j2ee.server.impl.local;


import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionLifeCycleLocal;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

/**
 * Created by lzy on 2017/8/9.
 *
 * 注意：这里没有使用任何@Local @Remote注解，默认是@Local
 */
@Stateless(name = Constant.StatelessSessionLifeCycleLocal)
public class StatelessSessionLifeCycleLocal implements IStatelessSessionLifeCycleLocal {

    public StatelessSessionLifeCycleLocal() {
        super();
        System.out.println("================> Invoke Construct");
    }

    /**
     * Bean 生命周期 does not exist（实例在内存中海没有创建） 状态到 method-ready pool状态时会调这个方法
     * 每个类只允许定义一个注解@PostConstruct
     * 容器将会调用带有这个注解的无返回值/不抛出异常/无参方法
     * 且只调用一次
     */
    @PostConstruct
    public void postConstruct(){
        System.out.println("=====================> Invoke PostConstruct");
    }

    /**
     * 每个类只允许定义一个注解@PreDestroy
     * 容器将会调用带有这个注解的无返回值/不抛出异常/无参方法
     * 且只调用一次
     */
    @PreDestroy
    public void preDestroy(){
        System.out.println("===========================> Invoke PreDestroy");
    }

    @Override
    public String sayHello(String name) {
        System.out.println("============================> Invoke sayHello");
        return "Hello " + name;
    }
}
