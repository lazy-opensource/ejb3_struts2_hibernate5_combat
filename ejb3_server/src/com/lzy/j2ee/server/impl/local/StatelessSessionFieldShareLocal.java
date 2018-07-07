package com.lzy.j2ee.server.impl.local;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionFieldShareLocal;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by lzy on 2017/8/9.
 *
 */
@Local
@Stateless(name = Constant.StatelessSessionFieldShareLocal)
public class StatelessSessionFieldShareLocal implements IStatelessSessionFieldShareLocal {

    private int index = 1;

    @Override
    public String add() {
        index++;
        String str = "current field index value is: " + index;
        return str;
    }
}
