<%--
  Created by IntelliJ IDEA.
  User: laizhiyuan
  Date: 2017/8/15
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Test Index</title>
  </head>
  <body>
     <a href="${pageContext.request.contextPath}/struts/example/index">Test Hello Struts2</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testlifecycle/TestSessionLifeCycleAction_testStatelessLifecycle">Test StatelessSession Bean LifeCycle</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testlifecycle/TestSessionLifeCycleAction_testStatefulLifecycle">Test StatefulSession Bean LifeCycle</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testinstancepool/TestInstancePoolAction_testAlike">Test StatelessSession Bean instancepool testAlike</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testinstancepool/TestInstancePoolAction_testDifferent">Test StatelessSession Bean instancepool testDifferent</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testfieldshare/index">Test Test Share Field</a><br/>
     <a href="${pageContext.request.contextPath}/struts/teststateful/TestStatefulSessionAction_index">Test Stateful Session </a><br/>
     <a href="${pageContext.request.contextPath}/struts/testinject/example!index">Test Inject Bean </a><br/>
     <a href="${pageContext.request.contextPath}/struts/testjms/TestJmsAction_execute">Test Jms P2p/Topic</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testwebservice/index">Test Web Service</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testtimer/index">Test Timer</a><br/>
     <a href="${pageContext.request.contextPath}/struts/example/hibernate_execute">Test Hibernate</a><br/>
     <a href="${pageContext.request.contextPath}/struts/testfreemarker/index">Test FreeMarker</a><br/>
  </body>
</html>
