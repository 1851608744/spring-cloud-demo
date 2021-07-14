package com.yw.demo.lock;

import com.yw.demo.jvm.User;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author yangwei
 * @data 2021/06/18
 **/
public class LockUpgrade {

    public static void main(String[] args) {
        User userTemp = new User();
        System.out.println("无状态（01）" + ClassLayout.parseInstance(userTemp).toPrintable());
    }
}
