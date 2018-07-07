package com.lzy.j2ee.server.publicinterface.dao.crud;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.publicinterface.base.IBaseDao;

import javax.ejb.Local;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Local
public interface ICrudDaoLocal extends IBaseDao<HelloHibernate> {
}
