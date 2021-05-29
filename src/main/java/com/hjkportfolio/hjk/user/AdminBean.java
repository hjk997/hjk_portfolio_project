package com.hjkportfolio.hjk.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminBean {

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