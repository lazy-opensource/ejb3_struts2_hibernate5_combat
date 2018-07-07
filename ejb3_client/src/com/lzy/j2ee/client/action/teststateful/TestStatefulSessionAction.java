package com.lzy.j2ee.client.action.teststateful;

import com.lzy.j2ee.server.entity.ProductEntity;
import com.lzy.j2ee.server.publicinterface.local.IStatefulSessionLocal;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laizhiyuan on 2017/8/17.
 *
 * 如果要映射vo 需要实现ModelDriven接口，这个接口只有getModel()方法需要客户端实现
 * 再次展示解耦的方式获取web资源
 */
public class TestStatefulSessionAction implements ModelDriven<ProductEntity>, SessionAware, RequestAware{

    /**
     * 将前台添加商品的参数映射为实体
     */
    private ProductEntity product;

    private Map<String, Object> session;

    private Map<String, Object> request;

    /**
     * 返回json要添加get set方法
     */
    private Map<String, Object> resultMap;

    /**
     * @return
     */
    public String index()  {

        return "index";
    }

    /**
     * 添加商品
     *
     * 返回JSON基于 Struts2 的JSON 插件
     * 1. 导入整合json 相关jar
     * 2. package 继承 json-default
     * 3. result 的 type = json
     */
    public String add(){
        IStatefulSessionLocal statefulSessionLocal =
                (IStatefulSessionLocal) session.get("myCart");

        statefulSessionLocal.add(getModel());
        Integer countStr = statefulSessionLocal.getCount();

        /**
         * 返回json格式，注意添加resultMap的get set方法
         */
        resultMap = new HashMap<String, Object>();
        /**
         * 演示返回json时可以自定义排除项和包含项
         */
        resultMap.put("testinclude_count", countStr);
        resultMap.put("testinclude_product", product);
        resultMap.put("testexclude_date", new Date());
        resultMap.put("testnull_null", null);
        return "add";
    }

    /**
     * 删除商品
     * 返回JSON 基于响应流方式
     * @return
     */
    public String remove() throws IOException {
        IStatefulSessionLocal statefulSessionLocal =
                (IStatefulSessionLocal) session.get("myCart");

        statefulSessionLocal.delete(getModel());
        String countStr = "" + statefulSessionLocal.getCount();

        /**
         * 耦合的方式获取web资源response
         */
        ServletActionContext.getResponse().setContentType("text/html");
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        ServletActionContext.getResponse().getWriter().printf(countStr);
        ServletActionContext.getResponse().getWriter().flush();
        ServletActionContext.getResponse().getWriter().close();

        return null;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Override
    public ProductEntity getModel() {
        /**
         * 基本写法
         */
        if (product == null){
            product = new ProductEntity();
        }
        return product;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
