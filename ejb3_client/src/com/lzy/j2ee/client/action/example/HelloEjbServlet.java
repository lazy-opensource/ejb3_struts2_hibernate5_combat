package com.lzy.j2ee.client.action.example;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.remote.IHelloEjbRemote;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by laizhiyuan on 2017/8/15.
 */
public class HelloEjbServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /*
            appName：这里是.EAR包的名称，如果你打包成JAR发布的话，这里则留空
            moduleName：表示模块名，也就是ejb包名，但不包括后缀.jar，如admin_ejb.jar。moduleName为admin_ejb
            distinctName：如果没有定义其更详细的名称，则这里留空
            beanName：这里为实现类的名称
            viewClassName：为接口全路径名称
            */
            String appName = "";
            String moduleName = "ejb3_server";
            String distinctName = "";
            String viewClassName = IHelloEjbRemote.class.getName();

            String jndi = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/"+ Constant.HelloEjb +"!" + viewClassName;

            IHelloEjbRemote example = (IHelloEjbRemote) EjbHelper.localByJndi(jndi);
            String result = example.sayHello("laizhiyuan");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
