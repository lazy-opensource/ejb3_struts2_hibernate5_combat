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
    <title>Test Jms</title>
</head>
<body>
<%--这里作用是开启Debug，可以看到Strust2错误信息在值栈中的封装格式--%>
<s:debug />
<hr>
<%--这里的作用是批量显示出错信息 只能显示addActionError()方法存入的错误信息 --%>
<s:actionerror />

<%--测试国际化输出--%>
<s:property value="%{getText('testText')}" /><hr>

<s:if test="%{#request.fileList != null && not #request.fileList == ''}">
    <s:if test="%{#request.fileList.size == 0}">
        <s:a href="TestJmsAction_execute">没有图片?去上传</s:a><hr>
    </s:if>
    <s:else>
        <s:iterator id="filePath" value="#request.fileList">
            <s:a href="TestJmsAction_download?filePath=%{filePath}">下载:<s:property value="filePath" /></s:a><br>
        </s:iterator>
        <hr>
        <s:a href="TestJmsAction_execute">去上传</s:a><hr>
    </s:else>
</s:if>

<%--文件上传--%>
<s:else>
    <span style="color:red;"><s:property value="#request.msg" default="" /></span><hr>
    <%--
    上传文件固定设置：enctype="multipart/form-data" method="post"
    在进行文件上传时，表单提交方式一定要是post的方式，因为文件上传时二进制文件可能会很大
    还有就是enctype属性，这个属性一定要写成multipart/form-data，
    --%>
    <form action="${pageContext.request.contextPath}/struts/testjms/TestJmsAction_uploadFile"
          enctype="multipart/form-data" method="post">
        <h3>演示单文件上传</h3>
        <input name="uploadFile" type="file"/>
        <input type="submit" value="上传" />
    </form>

    <br>
    <s:form action="TestJmsAction_uploadMultipartFile"
            enctype="multipart/form-data" method="post">
        <h3>演示多文件上传</h3>
        <%--注意这里的name--%>
        <s:file name="multipartFileUpload" />
        <s:file name="multipartFileUpload" />
        <s:file name="multipartFileUpload" />
        <s:submit value="上传" />
    </s:form>

    <br>
    <form action="${pageContext.request.contextPath}/struts/testjms/TestJmsAction_uploadFileByPojo"
          enctype="multipart/form-data" method="post">
        <h3>演示实体映射单文件上传</h3>
            <%--注意这里的name--%>
        <input name="uploadFilePojo.uploadFile" type="file"/>
        <input type="submit" value="上传" />
    </form>

    <br>
    <s:form action="TestJmsAction_uploadMultipartFileByPojo"
            enctype="multipart/form-data" method="post">
        <h3>演示实体映射多文件上传</h3>
        <%--注意这里的name--%>
        <s:file name="uploadFilePojo.multipartFileUpload" />
        <s:file name="uploadFilePojo.multipartFileUpload" />
        <s:file name="uploadFilePojo.multipartFileUpload" />
        <s:submit value="上传" />
    </s:form>
    <hr>
    <a href="${pageContext.request.contextPath}/struts/testjms/TestJmsAction_loasFileList">去下载文件</a>
</s:else>

</body>
</html>
