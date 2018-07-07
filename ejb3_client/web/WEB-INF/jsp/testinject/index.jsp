<%--
  Created by IntelliJ IDEA.
  User: laizhiyuan
  Date: 2017/8/9
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Test Inject</title>
</head>
<body>
<%--这里作用是开启Debug，可以看到Strust2错误信息在值栈中的封装格式--%>
<s:debug />
<hr>
<%--这里的作用是批量显示出错信息 只能显示addActionError()方法存入的错误信息 --%>
<s:actionerror />

<%--测试国际化输出--%>
<s:property value="%{getText('testText')}" />
<form action="${pageContext.request.contextPath}/struts/testinject/example!testInjectBean" method="post">
    <%--
    ${errors.usernameErrorMsg[0]} ${errors.passwordErrorMsg[0]} 这样使用EL表达式获取逐条的消息
    显示addField()方法显示的错误信息

    --%>
    请输入你的名字：<input name="username" /><span>${errors.usernameErrorMsg[0]}</span>
    <br>
        请输入密码：<input name="password"><span>${errors.passwordErrorMsg[0]}</span>
        <br>
    <input type="submit" value="提交" />
</form>

<s:if test="%{#request.result != null && #request.result != ''}">
    <br>
    <span><s:property value="#request.result" /> </span>
</s:if>
<hr>
<%--注意：使用struts a标签的href不能带${pageContext.request.contextPath}/struts/testinject/--%>
<s:a href="example!language">哇！国际化太有趣了！看看其它语言和国家的国际化文件都是怎么命名的</s:a>
</body>
</html>
