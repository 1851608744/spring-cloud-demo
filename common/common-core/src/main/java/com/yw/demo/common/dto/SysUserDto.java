package com.yw.demo.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author yangwei
 * @data 2021/06/03
 **/
@Data
// equals方法和 hashCode方法
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SysUserDto {

    private int id;
    private String name;
    private String password;
    private List<String> roles;
    private Date updateDate;
    private Date createDate;


}
