package com.lzy.j2ee.server.impl.service.example;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.dao.example.IHelloHibernateDaoLocal;
import com.lzy.j2ee.server.publicinterface.service.example.IHelloHibernateServiceLocal;
import com.lzy.j2ee.server.publicinterface.service.example.IHelloHibernateServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Stateless(name = Constant.HelloHibernateServiceImpl)
public class HelloHibernateServiceImpl implements IHelloHibernateServiceLocal, IHelloHibernateServiceRemote {

    @EJB
    private IHelloHibernateDaoLocal helloHibernateDaoLocal;

    @Override
    public String insert(HelloHibernate orm) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        try {
            orm.setCreateTime(simpleDateFormat.parse(currentTime));
            orm.setUpdateTime(orm.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return Constant.Faild;
        }
        return helloHibernateDaoLocal.insert(orm);
    }

}
