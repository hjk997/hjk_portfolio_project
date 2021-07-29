package com.hjkportfolio.hjk.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminVO {

    int uid;
    private String admin_id;
    private String password;
    private String name;

    @Override
    public String toString() {
        return "AdminBean{" +
                "uid=" + uid +
                ", admin_id='" + admin_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}