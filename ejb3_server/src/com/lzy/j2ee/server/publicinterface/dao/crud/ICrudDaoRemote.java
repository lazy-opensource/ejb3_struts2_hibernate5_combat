package com.lzy.j2ee.server.publicinterface.dao.crud;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.publicinterface.base.IBaseDao;

import javax.ejb.Remote;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Remote
public interface ICrudDaoRemote extends IBaseDao<HelloHibernate> {
}
