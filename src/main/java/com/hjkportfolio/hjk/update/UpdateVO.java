package com.hjkportfolio.hjk.update;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateVO {
    int uid;
    String title;
    Date writeDate;
    String contents;
    int writerUid;
    int note;
    String name;

    @Override
    public String toString() {
        return "UpdateVO{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", writeDate=" + writeDate +
                ", contents='" + contents + '\'' +
                ", writerUid=" + writerUid +
                ", name='" + name + '\'' +
                ", isNote=" + note +
                '}';
    }
}