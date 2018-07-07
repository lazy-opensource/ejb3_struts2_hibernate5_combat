package com.lzy.j2ee.server.publicinterface.base;

import java.util.List;

/**
 * Created by laizhiyuan on 2017/8/25.
 */
public interface IBaseDao<E> {

    /**
     * 增
     * @param orm
     * @return
     */
    public String insert(E orm);

    /**
     * 改
     * @param orm
     * @return
     */
    public String update(E orm);

    /**
     * 删
     * @param id
     * @return
     */
    public String deleteById(Long id);

    /**
     * 查
     * @return
     */
    public List<E> findList(String jqpl);

    /**
     * 查
     * @return
     */
    public E findById(Long id);
}
