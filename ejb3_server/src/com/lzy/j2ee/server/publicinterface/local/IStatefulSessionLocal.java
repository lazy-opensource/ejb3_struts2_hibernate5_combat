package com.lzy.j2ee.server.publicinterface.local;

import com.lzy.j2ee.server.entity.ProductEntity;
import javax.ejb.Local;

/**
 * Created by laizhiyuan on 2017/8/10.
 */
@Local
public interface IStatefulSessionLocal {

    public void add(ProductEntity product);

    public void delete(ProductEntity product);

    public Integer getCount();

}