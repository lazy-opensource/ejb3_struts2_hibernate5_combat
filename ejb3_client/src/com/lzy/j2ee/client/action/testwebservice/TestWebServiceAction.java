package com.lzy.j2ee.client.action.testwebservice;

import org.apache.struts2.ServletActionContext;
import java.io.IOException;

/**
 * Created by lzy on 2017/8/19.
 */
public class TestWebServiceAction {

    public String index() throws IOException {
        HelloWebService helloWebService = new HelloWebServiceService().getHelloWebServicePort();
        String result = helloWebService.sayHello("laizhiyuan");

        ServletActionContext.getResponse().getWriter().write(result);
        ServletActionContext.getResponse().getWriter().close();

        return null;
    }
}
