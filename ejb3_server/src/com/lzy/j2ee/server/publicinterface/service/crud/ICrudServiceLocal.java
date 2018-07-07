package com.lzy.j2ee.server.publicinterface.service.crud;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.publicinterface.base.IBaseService;

import javax.ejb.Local;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Local
public interface ICrudServiceLocal  extends IBaseService<HelloHibernate> {
}
