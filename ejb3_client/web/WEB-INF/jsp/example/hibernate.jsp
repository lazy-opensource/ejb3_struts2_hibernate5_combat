<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: laizhiyuan
  Date: 2017/8/25
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Hibernate</title>
</head>
<body>
<s:form action="hibernate_insert" method="post">
    <table>
        <tr>
            <td>名字：</td>
            <td><s:textfield name="name"/></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><s:textfield name="address"></s:textfield></td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td><s:textfield name="age"></s:textfield></td>
        </tr>
        <tr>
            <td>生日：</td>
            <td>
                <s:textfield name="birthday" class="Wdate" onClick="WdatePicker()">
                </s:textfield>
            </td>
        </tr>
        <tr>
            <td>性别：</td>
            <td><s:radio name="gender" list="#{'1':'男','2':'女'}" listKey="key" listValue="value" value="1"></s:radio></td>
        </tr>
            <td><s:submit value="提交"/></td>
            <td><s:reset value="重置"/></td>
        </tr>
    </table>
</s:form>

<%--引入My97日历插件--%>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/My97DatePicker/WdatePicker.js"></script>
</body>
</html>
