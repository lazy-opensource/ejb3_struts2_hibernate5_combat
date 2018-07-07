package com.lzy.j2ee.client.action.testcrud;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.entity.HelloHibernate;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.service.crud.ICrudServiceLocal;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
public class TestCrudAction extends ActionSupport implements ModelDriven<HelloHibernate>{

    private HelloHibernate helloHibernate;

    private Long id;

    private List<HelloHibernate> result;

    private static ICrudServiceLocal crudServiceLocal = (ICrudServiceLocal) EjbHelper
            .localByJndi(EjbHelper.getJndi(ICrudServiceLocal.class,
                    Constant.CrudServiceImpl));

    public String add(){
        crudServiceLocal.insert(helloHibernate);
        return "list";
    }

    public String update(){
        crudServiceLocal.update(helloHibernate);
        return "list";
    }

    public String deleteById(){
        crudServiceLocal.deleteById(id);
        return "list";
    }

    public String findAll(){
        String jqpl = "select hh from HelloHibernate as hh";
        result = crudServiceLocal.findList(jqpl);
        return "load";
    }

    public String toAdd(){
        return "add";
    }

    public String toEdit(){
        return "edit";
    }

    public String toList(){
        return "list";
    }

    public HelloHibernate getHelloHibernate() {
        return helloHibernate;
    }

    public void setHelloHibernate(HelloHibernate helloHibernate) {
        this.helloHibernate = helloHibernate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HelloHibernate> getResult() {
        return result;
    }

    public void setResult(List<HelloHibernate> result) {
        this.result = result;
    }

    @Override
    public HelloHibernate getModel() {
        if (this.helloHibernate == null){
            this.helloHibernate = new HelloHibernate();
        }
        return this.helloHibernate;
    }
}
