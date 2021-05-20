package com.hjkportfolio.hjk.post;

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
}