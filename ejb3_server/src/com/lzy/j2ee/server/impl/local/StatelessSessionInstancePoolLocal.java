package com.lzy.j2ee.server.impl.local;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionInstancePoolLocal;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by laizhiyuan on 2017/8/10.
 */
@Local
@Stateless(name = Constant.StatelessSessionInstancePoolLocal)
public class StatelessSessionInstancePoolLocal implements IStatelessSessionInstancePoolLocal {

    public StatelessSessionInstancePoolLocal() {
        super();
        System.out.println("======================> Invoke StatelessSessionInstancePoolLocal()");
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

    /**
     * 测试当该实例空闲时，多个请求可能会返回同一个实例
     * @param name
     * @return
     */
    @Override
    public String returnAlike(String name) {
        System.out.println("====================>Begin Invoke returnAlike");
        return "Hello: " + name + " My HashCode is: " + this.hashCode();
    }

    /**
     * 测试当该实例被另一个请求使用时，实例池为其它请求新建或取出其它空闲的实例
     * @param name
     * @return
     */
    @Override
    public String returnDifferent(String name) {
        System.out.println("====================>Begin Invoke returnDifferent");
        try {
            /**
             * 模拟在处理一个耗时的请求
             */
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====================>After Invoke returnDifferent");
        return "Hello: " + name + " My HashCode is: " + this.hashCode();
    }
}
