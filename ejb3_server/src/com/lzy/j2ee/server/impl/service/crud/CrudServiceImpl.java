package com.lzy.j2ee.server.impl.service.crud;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.dao.crud.ICrudDaoLocal;
import com.lzy.j2ee.server.publicinterface.service.crud.ICrudServiceLocal;
import com.lzy.j2ee.server.publicinterface.service.crud.ICrudServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Stateless(name = Constant.CrudServiceImpl)
public class CrudServiceImpl implements ICrudServiceLocal , ICrudServiceRemote {

    @EJB
    private ICrudDaoLocal crudDaoLocal;

    @Override
    public String insert(HelloHibernate orm) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        try {
            orm.setCreateTime(simpleDateFormat.parse(currentTime));
            orm.setUpdateTime(orm.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return Constant.OperFaild;
        }
        return crudDaoLocal.insert(orm);
    }

    @Override
    public String update(HelloHibernate orm) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        try {
            orm.setUpdateTime(simpleDateFormat.parse(currentTime));
        } catch (ParseException e) {
            e.printStackTrace();
            return Constant.OperFaild;
        }
        return crudDaoLocal.update(orm);
    }

    @Override
    public String deleteById(Long id) {
        return crudDaoLocal.deleteById(id);
    }

    @Override
    public List<HelloHibernate> findList(String jqpl) {

        return crudDaoLocal.findList(jqpl);
    }

    @Override
    public HelloHibernate findById(Long id) {
        return crudDaoLocal.findById(id);
    }
}
