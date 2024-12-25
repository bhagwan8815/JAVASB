package com.bhagwan.FirstSbProject.entity;

import org.springframework.stereotype.Component;


public class JournalEntity {
    private Long id;
    private String name;
    private String content;
    public Long getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public String getContent() {

        return content;
    }




    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
