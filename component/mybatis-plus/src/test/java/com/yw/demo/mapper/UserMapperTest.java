package com.yw.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.base.Function;
import com.yw.demo.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @org.junit.Test
    public void test() {
        //System.out.println(("----- selectAll method test ------"));
        //List<User> userList = userMapper.selectList(null);
        //Assert.assertEquals(2, userList.size());
        //userList.forEach(System.out::println);



    }

    @Test
    public void WrapperTest() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(User::getUserAge).eq(User::getId, 2);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        System.out.println(user.getUserAge());

        System.out.println("----- 普通查询 ------");
        List<User> plainUsers =
                userMapper.selectList(new QueryWrapper<User>().eq("id", 1));
        List<User> lambdaUsers =
                userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getId, 1));
        Assert.assertEquals(plainUsers.size(), lambdaUsers.size());
        System.out.println("=========== plain ============");
        print(plainUsers);
        System.out.println("=========== lambda ============");
        print(lambdaUsers);


        System.out.println("----- 带子查询（sql注入） ------");
        List<User> plainUsers2 =
                userMapper.selectList(new LambdaQueryWrapper<User>()
                        .inSql(User::getUserAge, "select user_age from user where id = 1"));
        List<User> lambdaUsers2 =
                userMapper.selectList(new QueryWrapper<User>().lambda()
                        .inSql(User::getUserAge, "select user_age from user where id = 2"));
        Assert.assertEquals(plainUsers2.size(), lambdaUsers2.size());
        System.out.println("=========== plain ============");
        print(plainUsers2);
        System.out.println("=========== lambda ============");
        print(lambdaUsers2);
        System.out.println();
        System.out.println("----- 带嵌套查询 ------");
        List<User> plainUsers3 =
                userMapper.selectList(new LambdaQueryWrapper<User>()
                        .nested(i -> i.eq(User::getId, 1).or().eq(User::getUserAccount, 123456))
                                .and(i -> i.ge(User::getUserAge, 24)));
        print(plainUsers3);

        System.out.println("------ 自定义（sql注入）------");
        List<User> plainUsers4 =
                userMapper.selectList(new QueryWrapper<User>()
                        .apply("id = 2"));
        print(plainUsers4);


        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.set("user_password", "654321");
        uw.eq("id", 1);
        userMapper.update(new User(), uw);
        User u4 = userMapper.selectById(1);
        System.out.println(u4);
        Assert.assertNotNull(u4.getUserPassword());

    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    /**
     * SELECT * FROM demo_user
     * WHERE (1 = 1) AND ((user_age = ? AND user_account = ?) OR (user_age = ? AND user_account = ?))
     */
    @Test
    public void testSql() {
        QueryWrapper<User> w = new QueryWrapper<>();
        w.and(i -> i.eq("1", 1))
                .nested(i -> i.and(j -> j.eq("user_age", 24).eq("user_account", "654321"))
                        .or(j -> j.eq("user_age", 25).eq("user_account", "123456")));
        userMapper.selectList(w);
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.select("id", "user_name").between("user_age", 20, 30);
        List<User> plainUsers = userMapper.selectList(qw);

        userMapper.selectList(qw);
        LambdaQueryWrapper<User> lwq = new LambdaQueryWrapper<>();
        lwq.select(User::getId, User::getUserName).between(User::getUserAge, 20, 30);
        List<User> lambdaUsers = userMapper.selectList(lwq);
        print(lambdaUsers);
        
        
    }

    @Test
    public void testLambada() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        list.forEach(UserMapperTest::printValue);
        Consumer<String> mehtod = UserMapperTest::printValue;
        list.forEach(x -> mehtod.accept(x));
    }

    public static void printValue(String str) {
        System.out.println("print value : " + str);
    }





}