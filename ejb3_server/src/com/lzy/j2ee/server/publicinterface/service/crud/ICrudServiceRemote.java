package com.lzy.j2ee.server.publicinterface.service.crud;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.publicinterface.base.IBaseService;

import javax.ejb.Remote;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Remote
public interface ICrudServiceRemote extends IBaseService<HelloHibernate> {
}
