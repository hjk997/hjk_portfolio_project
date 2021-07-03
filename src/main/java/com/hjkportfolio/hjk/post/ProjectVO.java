package com.hjkportfolio.hjk.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ProjectVO {

    int uid;
    int projectType;
    int gradeType;
    String title;
    String summary;
    String part;
    String review;
    String link;
    int writerUid;
    Date startedDate;
    Date endedDate;

}
