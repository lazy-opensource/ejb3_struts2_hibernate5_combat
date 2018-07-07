package com.lzy.j2ee.client.action.testinstancepool;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionInstancePoolLocal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laizhiyuan on 2017/8/17.
 */
public class TestInstancePoolAction {

    private Map<String, Object> result;

    public String testAlike(){

        IStatelessSessionInstancePoolLocal statelessSessionInstancePoolLocal =
                (IStatelessSessionInstancePoolLocal) EjbHelper.localByJndi(
                        EjbHelper.getJndi(IStatelessSessionInstancePoolLocal.class,
                                Constant.StatelessSessionInstancePoolLocal)
                );

        String response = statelessSessionInstancePoolLocal.returnAlike("laizhiyuan");
        result = new HashMap<String, Object>();
        result.put("测试当该实例空闲时，多个请求可能会返回同一个实例返回结果", response);

        return "success";
    }

    public String testDifferent(){

        IStatelessSessionInstancePoolLocal statelessSessionInstancePoolLocal =
                (IStatelessSessionInstancePoolLocal) EjbHelper.localByJndi(
                        EjbHelper.getJndi(IStatelessSessionInstancePoolLocal.class,
                                Constant.StatelessSessionInstancePoolLocal)
                );

        String response = statelessSessionInstancePoolLocal.returnDifferent("laizhiyuan");
        result = new HashMap<String, Object>();
        result.put("测试当该实例被另一个请求使用时，实例池为其它请求新建或取出其它空闲的实例返回结果", response);

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
