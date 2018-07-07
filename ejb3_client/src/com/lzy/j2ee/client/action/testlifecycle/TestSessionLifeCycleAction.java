package com.lzy.j2ee.client.action.testlifecycle;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatefulSessionLifeCycleLocal;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionLifeCycleLocal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laizhiyuan on 2017/8/16.
 */
public class TestSessionLifeCycleAction {

    private Map<String, Object> result;

    public String testStatelessLifecycle(){

        IStatelessSessionLifeCycleLocal statelessSessionLifeCycleLocal =
                (IStatelessSessionLifeCycleLocal) EjbHelper.localByJndi(
                        EjbHelper.getJndi(IStatelessSessionLifeCycleLocal.class,
                                Constant.StatelessSessionLifeCycleLocal)
                );

        /**
         * 这里演示以JSON的方式到客户端
         */
        String response = statelessSessionLifeCycleLocal.sayHello("laizhiyuan");
        result = new HashMap<String, Object>();
        result.put("测试无状态会话生命周期和回调函数返回结果", response);

        return "success";
    }

    public String testStatefulLifecycle(){

        IStatefulSessionLifeCycleLocal statefulSessionLifeCycleLocal =
                (IStatefulSessionLifeCycleLocal) EjbHelper.localByJndi(
                        /**
                         * 注意这里是调用getStatefulJndi
                         */
                        EjbHelper.getStatefulJndi(IStatefulSessionLifeCycleLocal.class,
                                Constant.StatefulSessionLifeCycleLocal)
                );

        /**
         * 这里演示以JSON的方式到客户端
         */
        String response = statefulSessionLifeCycleLocal.sayHello("laizhiyuan");
        /**
         * 这里调用remove
         */
        statefulSessionLifeCycleLocal.remove();
        result = new HashMap<String, Object>();
        result.put("测试有状态会话生命周期和回调函数返回结果", response);

        return "success";
    }

    /**
     * get set 方法
     * @return
     */
    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
