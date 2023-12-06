package org.nb.hrm.net.param;

import lombok.Data;


@Data
public class LoginOrRegisterParam {
    private String phone;
    private int role;
    private String code;
}
