package com.socialnetwork.networkplatform.dtos;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDetails {
    Long id;
    String content; 
    Date createdDate;

    public PostDetails(Long id, String content, Date date) {
        this.id = id;
        this.content = content;
        this.createdDate = date;
    }
}
