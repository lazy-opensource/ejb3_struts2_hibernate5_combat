package com.lzy.j2ee.server.publicinterface.local;

import javax.ejb.Local;

/**
 * Created by lzy on 2017/8/20.
 */
@Local
public interface IStatelessSessionTimerServiceLocal {

    /**
     * 在这里创建定时器
     * @return
     */
    public String createTimer();
}
