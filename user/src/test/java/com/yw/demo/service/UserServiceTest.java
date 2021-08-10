package com.yw.demo.service;

import com.yw.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class UserServiceTest {


    @Test
    public void redisTest() {
        //Runnable runnable = () -> System.out.println("lambda线程");
        //Runnable runnable1 = new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("内部类线程");
        //    }
        //};

        //User user = new User();
        //user.setUserName("工具人");
        //User user1 = new User();
        //user1.setUserName("小哑巴");
        //List<User> userList = Arrays.asList(user1, user);
        List<String> list = Arrays.asList("a", "b", "c", "d");
        //list.stream().forEach(System.out::println);
        //
        //List<String> names = userList.stream().map(i ->
        //        i.getUserName()).collect(Collectors.toList());
        //names.stream().forEach(System.out::println);

        String[] testStrings = {"a", "b", "c", "d"};
        //collect(toList())终止操作
        //由Stream中的值生成一个List列表，也可用collect(toSet())生成一个Set集合
        List<String> list1 = Stream.of(testStrings).collect(Collectors.toList());
        for (int i = 0, length = list1.size(); i < length; i++) {
            System.out.println(list1.get(i));
        }

        //map 中间操作
        //将一种类型的值映射为另一种类型的值，可以将Stream中的每个值都映射为一个新的值，最终转换为一个新的Stream流
        List<String> list2 = Stream.of(testStrings).map(test ->
                test.toUpperCase()).collect(Collectors.toList());
        list2.forEach(System.out::println);

        //filter中间操作
        //遍历筛选出满足条件的元素形成一个新的Stream流
        long count = list.stream().filter(p -> p.startsWith("j")).count();
        System.out.println(count);


    }

}