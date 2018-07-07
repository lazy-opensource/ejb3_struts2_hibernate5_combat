package com.lzy.j2ee.server.impl.local;

import com.lzy.j2ee.server.entity.ProductEntity;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatefulSessionLocal;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laizhiyuan on 2017/8/10.
 *
 * 有状态会话Bean不同之处是使用注解@Stateful
 */
@Stateful(name = Constant.StatefulSessionLocal)
public class StatefulSessionLocal implements IStatefulSessionLocal {

    /**
     * 案例说明：让每一个用户拥有自己的购物车
     *
     * 购物车
     */
    private List<ProductEntity> cart = new ArrayList<ProductEntity>();


    @Override
    public void add(ProductEntity product) {
        cart.add(product);
    }

    @Override
    public void delete(ProductEntity product) {
        cart.remove(product);
    }

    @Override
    public Integer getCount() {
        return cart.size();
    }
}
