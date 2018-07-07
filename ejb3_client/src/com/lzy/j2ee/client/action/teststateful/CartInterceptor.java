package com.lzy.j2ee.client.action.teststateful;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatefulSessionLocal;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * Created by laizhiyuan on 2017/8/11.
 *
 * <p>
 *     Struts2 拦截器继承AbstractInterceptor即可
 * </p>
 */
public class CartInterceptor extends AbstractInterceptor {

    /**
     * 拦截请求是否是新用户
     * @param actionInvocation
     * @return
     * @throws Exception
     */
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        /**
         * 这里采用耦合的方式访问web资源
         */
        System.out.println("=====================> 拦截请求：" + ServletActionContext.getRequest().getRequestURI());
        /**
         * 耦合的方式访问web资源
         */
        HttpSession session = ServletActionContext.getRequest().getSession();
        IStatefulSessionLocal statefulSessionLocal = null;
        Object myCart = session.getAttribute("myCart");
        if (myCart == null){
            System.out.println("该用户不存在购物车");
            statefulSessionLocal = (IStatefulSessionLocal) EjbHelper.localByJndi(
                    /**
                     * 注意这里获取Bean的方式不同，这里调EjbHelper.getStatefulJndi
                     */
                    EjbHelper.getStatefulJndi(IStatefulSessionLocal.class, Constant.StatefulSessionLocal));
            session.setAttribute("myCart", statefulSessionLocal);
        }else {
            System.out.println("该用户存在购物车");
            statefulSessionLocal = (IStatefulSessionLocal) myCart;
        }

        /**
         * 这里使用耦合的方式获取web资源
         * 可以发现import中有这一行：import javax.servlet.http.HttpSession;
         */
        ServletActionContext.getRequest().setAttribute("count", statefulSessionLocal.getCount());
        return actionInvocation.invoke();
    }
}
