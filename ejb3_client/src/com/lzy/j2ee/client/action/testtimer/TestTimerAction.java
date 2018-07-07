package com.lzy.j2ee.client.action.testtimer;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionTimerServiceLocal;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

/**
 * Created by lzy on 2017/8/20.
 */
public class TestTimerAction {

    public String index() throws IOException {
        IStatelessSessionTimerServiceLocal statelessSessionTimerServiceLocal =
                (IStatelessSessionTimerServiceLocal) EjbHelper.localByJndi(EjbHelper.getJndi(IStatelessSessionTimerServiceLocal.class,
                        Constant.StatelessSessionTimerServiceLocal));

        String result = statelessSessionTimerServiceLocal.createTimer();
        ServletActionContext.getResponse().getWriter().write(result);
        ServletActionContext.getResponse().getWriter().close();

        return null;
    }
}
