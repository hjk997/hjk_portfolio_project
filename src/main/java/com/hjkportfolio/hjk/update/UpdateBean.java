package com.hjkportfolio.hjk.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBean {
    int uid;
    String title;
    Date writeDate;
    String contents;
    int writerUid;
    String name;

    @Override
    public String toString() {
        return "UpdateBean{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", writeDate=" + writeDate +
                ", contents='" + contents + '\'' +
                ", writerUid=" + writerUid +
                ", name=" + name +
                '}';
    }
}