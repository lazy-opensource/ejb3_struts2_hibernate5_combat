package com.lzy.j2ee.client.action.testfreemarker;

import org.apache.struts2.ServletActionContext;

import java.util.*;

/**
 * Created by lzy on 2017/8/26.
 */
public class TestFreeMarkerAction {

    private Map<String, Object> out;

    public String test(){
        //------------------测试JAVA八大基本类型输出-----------------------
        int test_int = 100000000;
        short test_short = 5000;
        double test_double = 100000000.52222266666;
        long test_long = 800000000000088888L;
        boolean test_boolean = false;
        char test_char = 'A';
        float test_float = 339999999;
        byte test_byte = 127;
        out = new HashMap<String, Object>();
        out.put("test_int", test_int);
        out.put("test_short", test_short);
        out.put("test_double", test_double);
        out.put("test_long", test_long);
        out.put("test_boolean", test_boolean);
        out.put("test_char", test_char);
        out.put("test_float", test_float);
        out.put("test_byte", test_byte);

        //------------------测试数组输出-----------------------
        String[] test_arr = {"one", "two", "three", "four", "five"};
        out.put("test_arr", test_arr);

        //------------------测试List输出-----------------------
        List<String> test_list = new ArrayList<String>();
        test_list.add("hello");
        test_list.add("world");
        out.put("test_list", test_list);

        //------------------测试Set输出-----------------------
        Set<Integer> test_set = new HashSet<Integer>();
        test_set.add(1000);
        test_set.add(2000);
        test_set.add(3000);
        out.put("test_set", test_set);

        //------------------测试Map输出-----------------------
        Map<Integer,String> test_map = new HashMap<Integer,String>();
        test_map.put(1, "zhangsan");
        test_map.put(2, "lisi");
        test_map.put(3, "wangwu");
        out.put("test_map", test_map);

        //------------------测试对象属性输出-----------------------
        User test_obj = new User();
        test_obj.setId(99L);
        test_obj.setName("laizhiyuan");
        out.put("test_obj", test_obj);

        //------------------测试List中有Map输出-----------------------
        List<Map<String, User>> test_list_map = new ArrayList<Map<String, User>>();
        Map<String, User> map = new HashMap<String, User>();
        map.put("user1", new User(100L, "zhangsan"));
        map.put("user2", new User(200L, "lisi"));
        test_list_map.add(map);
        out.put("test_list_map", test_list_map);

        //------------------测试Map中有List输出-----------------------
        Map<String, List<User>> test_map_list = new HashMap<String, List<User>>();
        List<User> list = new ArrayList<User>();
        list.add(new User(300L, "wangwu"));
        list.add(new User(400L, "zhaoliu"));
        test_map_list.put("user", list);
        out.put("test_map_list", test_map_list);

        //------------------测试if else输出-----------------------
        boolean isExists = false;
        out.put("isExists", isExists);

        //------------------测试request输出-----------------------
        ServletActionContext.getRequest().setAttribute("test_request", "this is from request");

        return "main";
    }

    public Map<String, Object> getOut() {
        return out;
    }

    public void setOut(Map<String, Object> out) {
        this.out = out;
    }
}
