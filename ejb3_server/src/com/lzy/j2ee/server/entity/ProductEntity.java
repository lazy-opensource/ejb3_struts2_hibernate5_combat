package com.lzy.j2ee.server.entity;

import java.io.Serializable;

/**
 * Created by laizhiyuan on 2017/8/11.
 */
public class ProductEntity implements Serializable{

    static final long serialVersionUID = 9527L;

    public ProductEntity() {
        super();
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 必须重写equals 和 hashCode的算法，否则不会正确地删除商品
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof ProductEntity)){
            return false;
        }

        ProductEntity thant = (ProductEntity) obj;
        if (thant.getName() == null){
            return false;
        }
        return thant.getName().equals(this.getName());

    }

    @Override
    public int hashCode() {
        int code = 47;
        code = code * 12;
        code = code * this.getName().hashCode();
        return code;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
