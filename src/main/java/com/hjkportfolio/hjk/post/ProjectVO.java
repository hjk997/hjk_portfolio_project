package com.hjkportfolio.hjk.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endedDate;

    @Override
    public String toString() {
        return "ProjectVO{" +
                "uid=" + uid +
                ", projectType=" + projectType +
                ", gradeType=" + gradeType +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", part='" + part + '\'' +
                ", review='" + review + '\'' +
                ", link='" + link + '\'' +
                ", writerUid=" + writerUid +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                '}';
    }
}
