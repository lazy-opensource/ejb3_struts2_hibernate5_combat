package com.lzy.j2ee.client.action.testfieldshare;

import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionFieldShareLocal;
import org.apache.struts2.interceptor.RequestAware;
import java.util.Map;

/**
 * Created by laizhiyuan on 2017/8/17.
 */
public class TestFieldShareAction implements RequestAware {

    /*这里使用与Servelt API解耦的方式获取Web资源方式，这些值都是从ValueStack中获取。
    还可以通过实现接口RequestAware, ParameterAware, SessionAware, ApplicationAware，来获得Web的其它资源，
    这些都是与Servelt API解耦的方式。
    下面将花费很大的篇幅来讲解值栈的原理和概念：
    一、ValueStack(值栈)描述:
    贯穿整个 Action 的生命周期(每个 Action 类的对象实例都拥有一个 ValueStack 对象).
    相当于一个数据的中转站. 在其中保存当前 Action 对象和其他相关对象.
    Struts 框架把 ValueStack 对象保存在名为 “struts.valueStack” 的请求属性中
    在 ValueStack 对象的内部有两个逻辑部分:
          1、ObjectStack: Struts  把 Action 和相关对象压入 ObjectStack 中
          2、ContextMap: Struts 把各种各样的映射关系(一些 Map 类型的对象) 压入ContextMap 中.实际上就是对 ActionContext 的一个引用
    Struts 会把下面这些映射压入 ContextMap 中
    parameters: 该 Map 中包含当前请求的请求参数
    request: 该 Map 中包含当前 request 对象中的所有属性
    session: 该 Map 中包含当前 session 对象中的所有属性
    application:该 Map 中包含当前 application  对象中的所有属性
    attr: 该 Map 按如下顺序来检索某个属性: request, session, application
    二、在 JSP 页面上可以可以利用 OGNL(Object-Graph Navigation Language: 对象-图导航语言)访问到值栈(ValueStack) 里的对象属性.
         1、若希望访问值栈中 ContextMap 中的数据, 需要给 OGNL 表达式加上一个前缀字符 #. 如果没有前缀字符 #, 搜索将在 ObjectStack 里进行
         2、访问 Object Stack 里的某个对象的属性. 可以使用以下几种形式之一:
            object.propertyName
            object['propertyName']
            object["propertyName"]
         3、访问 ContextMap 里的某个对象的属性：
            #object.propertyName
            #object['propertyName']
            #object["propertyName"]
         4、示例：
            返回 session 中的 code 属性: #session.code
            返回 request 中的 customer 属性的 name 属性值: #request.customer.name
            返回域对象(按 request, session, application 的顺序)的 lastAccessDate 属性: #attr.lastAccessDate

         5、可以利用 OGNL 调用：
            任何一个 Java 类里的静态字段或方法.
            被压入到 ValueStack 栈的对象上的公共字段和方法.
            默认情况下, Struts2 不允许调用任意 Java 类静态方法,  需要重新设置 struts.ognl.allowStaticMethodAccess 标记变量的值为 true.
            调用静态字段或方法需要使用如下所示的语法:
            @fullyQualifiedClassName@fieldName: @com.lzy.j2ee.client.action.statefulsession.StatefulSessionClientAction@count
            @fullyQualifiedClassName@methodName(argumentList): @com.lzy.j2ee.client.action.statefulsession.StatefulSessionClientAction@getCount()
            调用一个实例字段或方法的语法, 其中 object 是 Object Stack 栈里的某个对象的引用:
            object.fieldName: [0].datePattern
            object.methodName(argumentList): [0].repeat(3, “Hello”);

        6、访问数组类型属性：
            有些属性将返回一个对象数组而不是单个对象, 可以像读取任何其他对象属性那样读取它们. 这种数组型属性的各个元素以逗号分隔, 并且不带方括号
            可以使用下标访问数组中指定的元素: colors[0]
            可以通过调用其 length 字段查出给定数组中有多少个元素: colors.length

         7、ObjectStack 里的对象可以通过一个从零开始的下标来引用. ObjectStack 里的栈顶对象可以用 [0] 来引用, 它下面的那个对象可以用 [1] 引用.
            若希望返回栈顶对象的 message 属性值:  [0].message 或 [0][“message”] 或 [0][‘message’]
            若在指定的对象里没有找到指定的属性, 则到指定对象的下一个对象里继续搜索. 即 [n] 的含义是从第 n 个开始搜索, 而不是只搜索第 n 个对象
            若从栈顶对象开始搜索, 则可以省略下标部分

        8、访问List属性：
            有些属性将返回的类型是 java.util.List, 可以像读取任何其他属性那样读取它们. 这种 List 的各个元素是字符串, 以逗号分隔, 并且带方括号
            可以使用下标访问 List 中指定的元素: colors[0]
            可以通过调用其 size 方法或专用关键字 size 的方法查出给定List 的长度: colors.size 或 colors.size()
            可以通过使用 isEmpty() 方法或专用关键字 isEmpty 来得知给定的 List 是不是空. colors.isEmpty 或 colors.isEmpty()
            还可以使用 OGNL 表达式来创建 List, 创建一个 List 与声明一个 Java 数组是相同的: {“Red”, “Black”, “Green”}

         9、访问Map属性：
            读取一个 Map 类型的属性将以如下所示的格式返回它所有的键值对:
            {key1=value1, key2=value2 .... keyn=valuen}
            若希望检索出某个 Map 的值, 需要使用如下格式: map[key]
            可以使用 size 或 size() 得出某个给定的 Map 的键值对的个数
            可以使用 isEmpty 或 isEmpty() 检查某给定 Map 是不是空.
            可以使用如下语法来创建一个 Map:
             #{key1=value1, key2=value2 .... keyn=valuen}

        10、使用EL访问值栈中对象的属性：
            <s:property value=“fieldName”> 也可以通过 JSP EL 来达到目的: ${fieldName}
            原理: Struts2 将包装 HttpServletRequest 对象后的 org.apache.struts2.dispatcher.StrutsRequestWrapper 对象传到页面上,
            而这个类重写了 getAttribute() 方法.

    我将通过每一个项目慢慢靠近Struts2的知识，而不会对Struts2额外去写测试例子来说明*/
    private Map<String, Object> request;

    private String invokeRlt;

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public String execute(){
        IStatelessSessionFieldShareLocal statelessSessionFieldShareLocal =
                (IStatelessSessionFieldShareLocal) EjbHelper.localByJndi(
                        EjbHelper.getJndi(
                                IStatelessSessionFieldShareLocal.class,
                                Constant.StatelessSessionFieldShareLocal));

        invokeRlt = statelessSessionFieldShareLocal.add();
        request.put("result", invokeRlt);
        return "ok";
    }
}
