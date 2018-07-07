package com.lzy.j2ee.server.impl.local;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatefulSessionLifeCycleLocal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;

/**
 * Created by laizhiyuan on 2017/8/16.
 *
 * 注意可以以这种方式（@Local(IStatefulSessionLifeCycleLocal.class)）注解为本地或远程
 *
 * 注解为@Stateful表示有状态会话Bean
 */
@Local(IStatefulSessionLifeCycleLocal.class)
@Stateful(name = Constant.StatefulSessionLifeCycleLocal)
public class StatefulSessionLifeCycleLocal implements IStatefulSessionLifeCycleLocal{

    /**
     * 业务方法
     * @param name
     * @return
     */
    @Override
    public String sayHello(String name) {
        System.out.println("============================> Invoke sayHello");
        return "Hello " + name;
    }

    /**
     * 构造器
     */
    public StatefulSessionLifeCycleLocal() {
        super();
        System.out.println("======================> 执行了构造器方法");
    }

    /**
     * 当完成对构造器的调用后，使用了这个注释的方法会被立即调用。这个注释同时适用有状态和无状态的会话 bean。
     */
    @PostConstruct
    public void postConstruct(){
        System.out.println("=====================> 调用了 postConstruct 方法");
    }

    /**
     * 这个注释指定了有状态 session bean 初始化的方法。
     * 它区别于@PostConstruct 注释在于：多个@Init注释方法可以同时存在于有状态 session bean 中
     * 但每个 bean 实例只会有一个@Init 注释的方法会被调用。
     * 这取决于 bean 是如何创建的（细节请看 EJB 3.0 规范）
     */
    @Init
    public void init(){
        System.out.println("=========================> 调用了 init 方法");
    }

    /**
     * 当一个有状态的 session bean 实例空闲过长的时间，容器将会钝化(passivate)它并把它的状态保存在缓存当中。
     * 使用这个注释的方法会在容器钝化 bean 实例之前调用。
     * 这个注释适用于有状态的会话 bean。
     * 当钝化后，又经过一段时间该 bean 仍然没有被操作，容器将会把它从存储介质中删除。
     * 以后，任何针对该 bean方法的调用容器都会抛出例外。
     */
    @PrePassivate
    public void prePassivate(){
        System.out.println("=========================> 准备钝化了");
    }

    /**
     * 当客户端再次使用已经被钝化的有状态 session bean 时，新的实例被创建，状态被恢复。
     * 使用此注释的 session bean 会在 bean 的激活完成时调用。这个注释只适用于有状态的会话 bean。
     */
    @PostActivate
    public void postActivate(){
        System.out.println("=======================> 我又激活了");
    }

    /**
     * 特别是对于有状态 session bean。当应用通过存根对象调用使用了@Remove 注释的方法时，
     * 容器就知道在该方法执行完毕后，要把 bean 实例从对象池中移走
     */
    @Remove
    public void remove(){
        System.out.println("========================> 谁把我删了");
    }

    /**
     * 使用这个注释的方法会在容器从它的对象池中销毁一个无用的或者过期的 bean 实例之前调用。
     * 这个注释同时适用于有状态和无状态的会话 bean。
     */
    @PreDestroy
    public void preDestroy(){
        System.out.println("======================> 城市套路深，我要回农村了");
    }

}
