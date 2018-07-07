package com.lzy.j2ee.client.action.testinject;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessSessionInjectRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lzy on 2017/8/17.
 *
 * 这里说说继承ActionSupport的好处
 * 1、如果你的execute方法只是返回一个页面，没有任何逻辑，那么可以省略该方法，只是result必须为success
 * 2、支持刷新验证功能
 * 3、方便读取properties文件属性
 * 4、支持国际化
 */
public class TestInjectAction extends ActionSupport{

    private String username;

    private String password;

    @SkipValidation
    public String index() {
        return SUCCESS;
    }

    /**
     * 注意这里返回JSON的方式
     * @return
     */
    public String testInjectBean(){

        IStatelessSessionInjectRemote statelessSessionInjectRemote =
                (IStatelessSessionInjectRemote) EjbHelper.localByJndi(
                        EjbHelper.getJndi(IStatelessSessionInjectRemote.class, Constant.StatelessSessionInjectRemote)
                );
        String msg = statelessSessionInjectRemote.sayHello() + getUsername();
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("result", msg);
        return SUCCESS;
    }

    /**
     * 这个action 返回java支持的所有的国家和语言
     * 为什么写这个方法呢：
     * 这是告诉大家，国际化配置文件可以命名的方式如：
     * info_zh_CN.properties
     * info:国际化配置文件的标识,这个可随意命名
     * zh:语言(中文)
     * CN:国家(中国)
     *
     * 注意，注解为@SkipValidation跳过验证
     * @return
     */
    @SkipValidation
    public String language() throws IOException {
        Locale[] localeArray = Locale.getAvailableLocales();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < localeArray.length; i++){
            Locale locale = localeArray[i];
            builder.append("语言为：").append(locale.getLanguage())
                    .append(" 国家为:").append(locale.getCountry())
                    .append(" 的配置文件命名为")
                    .append("xxx_").append(locale.getLanguage())
                    .append("_").append(locale.getCountry())
                    .append(".properties").append("<br>");
        }

        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        ServletActionContext.getResponse().setContentType("text/html");
        ServletActionContext.getResponse().getWriter().write(builder.toString());
        ServletActionContext.getResponse().getWriter().close();
        return null;
    }

    /**
     * 这里演示验证的例子
     */
    @Override
    public void validate() {
        System.out.println("凡是调用该Action的不带注解@SkipValidation的任何方法之前，都会先调用我，经过我的同意！");
        List<String> params = new ArrayList<String>();
        params.add("laizhiyuan");
        params.add("666666");
        if (!"laizhiyuan".equals(getUsername()) || "".equals(getUsername())){
            /**
             * 注意这里的this.getText()方法，这里可以获取到国际化配置文件的key
             * 国际化配置文件的标识在struts-injectbean.xml配置
             * 这就是继续ActionSupport带来福利
             */
            this.addFieldError("usernameErrorMsg", this.getText("addFieldError-usernameRequired", params));
            this.addActionError(this.getText("addActionError-usernameRequired", params));
        }

        if (!"666666".equals(getPassword()) || "".equals(getPassword())){
            this.addFieldError("passwordErrorMsg", this.getText("addFieldError-passwordRequered", params));
            this.addActionError(this.getText("addActionError-passwordRequered", params));
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
