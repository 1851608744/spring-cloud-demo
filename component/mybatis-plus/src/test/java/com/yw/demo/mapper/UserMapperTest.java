package com.yw.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yw.demo.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @org.junit.Test
    public void test() {

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

        List<User> plainUsers5 = userMapper.selectList(new QueryWrapper<User>()
                .apply("id = {0}", 2));
        Assert.assertEquals(plainUsers4.size(), plainUsers5.size());

        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.set("user_password", "654321");
        uw.eq("id", 1);
        userMapper.update(new User(), uw);
        User u4 = userMapper.selectById(1);
        System.out.println(u4);
        Assert.assertNotNull(u4.getUserPassword());

        List<User> lambdaUser6 = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getId, 1).
                and(i -> i.eq(User::getUserAge, "24")).and(i -> i.eq(User::getUserAddr, "南京")));
    }

    @Test
    public void updateTest() {
        User user = new User();
        //user.setId(1);
        //user.setUserName("yw");
        user.setUserAddr("东京");
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        //uw.lambda().eq(User::getId, 1);
        //uw.eq("id", 1);
        //uw.set("user_addr", "南京");
        userMapper.update(user, uw);
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


    @Test
    public void redisTest() {
        Map map = new HashMap();
        map.put("goods_num", 10);
        map.put("goods_info", "{title:good,price:200}");
        ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("java", 1.00);
        ZSetOperations.TypedTuple<String> typedTuple2 = new DefaultTypedTuple<String>("hello", 2.00);
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<ZSetOperations.TypedTuple<String>>();
        set.add(typedTuple);
        set.add(typedTuple2);
        set.add(new DefaultTypedTuple<>("中分哥", 3.00));
        set.add(new DefaultTypedTuple<>("骚鸟", 3.13));
        set.add(new DefaultTypedTuple<>("毒妖鸟", 3.00));
        set.add(new DefaultTypedTuple<>("飞雷龙", 3.23));

        //ValueOperations valueOperations = redisTemplate.opsForValue();
        //valueOperations.set("user", "Hunter");

        //redisTemplate.opsForValue().set("user", "Hunter");
        //redisTemplate.opsForHash().putAll("user:id:1", map);
        //redisTemplate.opsForList().rightPushAll("user:id:2", 1, 2, 3, 4, 5, 6);
        //redisTemplate.opsForSet().add("user:id:3", 1, 2, 3, 4, 5);
        //redisTemplate.opsForZSet().add("user:id:4", set);
        System.out.println(redisTemplate.opsForZSet().range("user:id:4", -1, 0));

    }

    @Test
    public void getRedisTest() {
        Set<ZSetOperations.TypedTuple<String>> range = redisTemplate.opsForZSet().rangeByScoreWithScores("user:id:4", 0, -1);
        System.out.println(range);
    }


}