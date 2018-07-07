<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/8/10
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Test Stateful Session</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"></link>
</head>
<body>
欢迎：<s:property value="#request.username" /><br><hr>
你目前拥有商品数：<input type="text" id="count" value="${requestScope.count}" disabled /><br>
请输入商品名称：<input type="text" id="productName" name="productName" />
<a href="javascript:add();">添加</a>
<a href="javascript:del();">删除</a>
<br>
<span id="testnull">@laizhiyuan 版本所有</span><br>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.bootbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script>

    function Product(name){
        var obj = new Object();
        obj.name = name;
        return obj;
    }

    function del(){
        var productName = $('#productName').val();
        if (!productName){
            bootbox.alert('商品名称不能为空!');
            $('#productName').focus();
            return;
        }
        bootbox.confirm("确定删除吗?", function(r){
            if (r){
                var product = new Product(productName);
                $.get(
                        '${pageContext.request.contextPath}/struts/teststateful/TestStatefulSessionAction_remove',
                        product,
                        function(data){
                            console.log(data);
                            bootbox.alert('删除成功!');
                            $('#count')[0].value = data;
                        },'json'
                )
            }
        });
    }
    function add(){
        var productName = $('#productName').val();
        if (!productName){
            bootbox.alert('商品名称不能为空!');
            $('#productName').focus();
            return;
        }

        var product = new Product(productName);
        $.post(
                '${pageContext.request.contextPath}/struts/teststateful/TestStatefulSessionAction_add',
                product,
                function(data){
                    bootbox.alert('添加成功!');
                    /**
                    *从控制台输出可以知道，那些被排除的字段没有序列化到前台
                     */
                    console.log(data);
                    $('#count').val(data.testinclude_count);
                    $('#testinclude').html('你刚才添加的商品为：' + JSON.stringify(data.testinclude_product));
                },'json'
        )
    }
</script>
</body>
</html>
