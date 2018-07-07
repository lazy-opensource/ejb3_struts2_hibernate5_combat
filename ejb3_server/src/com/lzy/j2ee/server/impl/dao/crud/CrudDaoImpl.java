package com.lzy.j2ee.server.impl.dao.crud;

import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.dao.crud.ICrudDaoLocal;
import com.lzy.j2ee.server.publicinterface.dao.crud.ICrudDaoRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
@Stateless(name = Constant.CrudDaoImpl)
public class CrudDaoImpl implements ICrudDaoLocal, ICrudDaoRemote {

    @PersistenceContext(unitName = "global_db")
    private EntityManager entityManager;

    @Override
    public String insert(HelloHibernate helloHibernate) {
        try{
            entityManager.persist(helloHibernate);
            return Constant.OperSuccess;
        }catch (Exception ex){
            ex.printStackTrace();
            return Constant.OperFaild;
        }
    }

    @Override
    public String update(HelloHibernate helloHibernate) {

        try {
            int count = entityManager.createNamedQuery("updateById")
                    .setParameter("id", helloHibernate.getId())
                    .setParameter("name", helloHibernate.getName())
                    .setParameter("address", helloHibernate.getAddress())
                    .setParameter("age", helloHibernate.getAddress())
                    .setParameter("gender", helloHibernate.getGender())
                    .setParameter("updateTime", helloHibernate.getUpdateTime())
                    .executeUpdate();

            if (count == 1){
                return Constant.OperSuccess;
            }else {
                return "无此数据!";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return Constant.OperFaild;
        }
    }

    @Override
    public String deleteById(Long id) {
        try{
            HelloHibernate obj = (HelloHibernate) entityManager.createNamedQuery("findById")
                    .setParameter("id", id).getSingleResult();
            entityManager.remove(obj);
            return Constant.OperSuccess;
        }catch (Exception ex){
            ex.printStackTrace();
            return Constant.OperFaild;
        }
    }

    @Override
    public List<HelloHibernate> findList(String jpql) {
        Query query = entityManager.createQuery(jpql, HelloHibernate.class);
        List<HelloHibernate> result = query.getResultList();

        return result;
    }

    @Override
    public HelloHibernate findById(Long id) {
        HelloHibernate orm = (HelloHibernate) entityManager.createNamedQuery("findById")
                .setParameter("id", id)
                .getSingleResult();

        return orm;
    }
}
