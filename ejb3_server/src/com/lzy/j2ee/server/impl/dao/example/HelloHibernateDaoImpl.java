package com.lzy.j2ee.server.impl.dao.example;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.dao.example.IHelloHibernateDaoLocal;
import com.lzy.j2ee.server.publicinterface.dao.example.IHelloHibernateDaoRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by laizhiyuan on 2017/8/24.
 */
@Stateless(name = Constant.HelloHibernateDaoImpl)
public class HelloHibernateDaoImpl implements IHelloHibernateDaoLocal , IHelloHibernateDaoRemote{

    @PersistenceContext(unitName = "global_db")
    private EntityManager entityManager;

    @Override
    public String insert(HelloHibernate helloHibernate) {
        try{
            /**
             * 测试自增触发器
             */
            if (helloHibernate != null && helloHibernate.getId() != null){
                helloHibernate.setId(null);
            }
            entityManager.persist(helloHibernate);
            return Constant.OperSuccess;
        }catch (Exception ex){
            ex.printStackTrace();
            return Constant.OperFaild;
        }
    }
}
