package com.dongdongshop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class UserDTO {
    private Integer uid;

    private String uname;

    private String upwd;

    private String salt;

}