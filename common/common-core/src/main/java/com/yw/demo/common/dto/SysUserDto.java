package com.yw.demo.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yangwei
 * @data 2021/06/03
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SysUserDto {

    private int id;
    private String name;
    private String password;
    private List<String> roles;

}
