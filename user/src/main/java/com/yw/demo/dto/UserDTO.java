package com.yw.demo.dto;

import lombok.Data;

/**
 * @author yangwei
 * @data 2021/05/31
 **/
@Data
public class UserDTO {

    /**
     * 主键
     */
    private String id;

    /**
     * 账号
     */
    private String user_account;

    /**
     * 密码
     */
    private String user_password;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 用户年龄
     */
    private String user_age;

    /**
     * 住址
     */
    private String user_addr;

    /**
     * 修改时间
     */
    private String update_time;

    /**
     * 创建时间
     */
    private String create__time;
}
