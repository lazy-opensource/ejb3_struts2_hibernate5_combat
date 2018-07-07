<!DOCTYPE html>
<html lang="en" xmlns:s="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
</head>
<body>
<h3>------------------测试JAVA八大基本类型输出-----------</h3><br/>
<h4>加?c</h4>
int: ${out.test_int?c} <br/>
short: ${out.test_short?c} <br/>
double: ${out.test_double?c} <br/>
long: ${out.test_long?c} <br/>
boolean: ${out.test_boolean?c} <br/>
char: ${out.test_char} <br/>
float: ${out.test_float?c} <br/>
byte: ${out.test_byte?c} <br/>
<h4>不加?c</h4>
int: ${out.test_int} <br/>
short: ${out.test_short} <br/>
double: ${out.test_double} <br/>
long: ${out.test_long} <br/>
<#--这个不加?c会报错！除了加?c以外还可以使用如下方式 true：输出yes。false：输出no-->
boolean: ${out.test_boolean?string('yes','no')} <br/>
char: ${out.test_char} <br/>
float: ${out.test_float} <br/>
byte: ${out.test_byte} <br/>
<h3>------------------测试数组输出-----------------------</h3><br/>
<#list out.test_arr as arr>
    ${arr}<br/>
</#list>
<h3>------------------测试List输出-----------------------</h3><br/>
<#list out.test_list as list>
    ${list}<br/>
</#list>
<h3>------------------测试Set输出------------------------</h3><br/>
<#list out.test_set as set>
    ${set}<br/>
</#list>
<h3>------------------测试Map输出------------------------</h3><br/>
<#list out.test_map?keys as key>
    ${key?c}_${out.test_map[key]}<Br/>
</#list>
<h3>------------------测试对象属性输出-------------------</h3><br/>
${out.test_obj.id?c}_${out.test_obj.name}
<h3>------------------测试List中有Map输出----------------</h3><br/>
<#list out.test_list_map as list_map>
    <#list list_map?keys as key>
        ${key}:${list_map[key].id}_${list_map[key].name}<br/>
    </#list><br/>
</#list>
<h3>------------------测试Map中有List输出----------------</h3><br/>
<#list out.test_map_list?keys as key>
    ${key}:<br/>
<#list out.test_map_list[key] as list>
    ${list.id?c}_${list.name}<Br>
</#list><Br/>
</#list>
<h3>------------------测试if else输出--------------------</h3><br/>
<#if out.isExists??>
isExists：true
<#else>
isExists:false
</#if>
<Br/>
<#if yyyyyyyyyyyyyyyyyyyyy??>
${yyyyyyyyyyyyyyyyyyyyy}
<#else>
不存在变量yyyyyyyyyyyyyyyyyyyyy
</#if>
<h3>------------------测试request输出--------------------</h3><br/>
${test_request}<br/>
<h3>------------------测试#include标---------------------</h3><br/>
<#include "sub.ftl" />
<h3>------------------测试操作不存在的属性----------------</h3><br/>
${yyyyyyyyyyyyyyyyy!"不存在该变量"}
</body>
</html>