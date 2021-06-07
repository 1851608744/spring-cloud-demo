package com.yw.demo.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangwei
 * @data 2021/06/07
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {

    private String apiBasePackage;

    private boolean enableSecurity;

    private String title;

    private String description;

    private String version;

    private String contactName;

    private String contactUrl;

    private String contactEmail;


}
