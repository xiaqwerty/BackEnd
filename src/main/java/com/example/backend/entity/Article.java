package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.backend.dao.Views;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Article
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long userId;
    @Size(min = 2,max = 20,message = "The length of title should be in 2~20")
    private String title;
    private String content;
    private int commitNum;

    public Article(long userId,String title, String content)
    {
        this.userId=userId;
        this.title = title;
        this.content = content;
    }
    public Article(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
    public Article(){}
    public void putArticle(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setCommitNum(int commitNum)
    {
        this.commitNum = commitNum;
    }

    public void incCommitNum(boolean ifInc) {
        if(ifInc)
            this.commitNum++;
    }

    @JsonView(Views.Internal.class)
    public int getCommitNum()
    {
        return commitNum;
    }

    @JsonView(Views.Internal.class)
    public long getId()
    {
        return id;
    }

    @JsonView(Views.Public.class)
    public String getTitle()
    {
        return title;
    }

    @JsonView(Views.Public.class)
    public String getContent()
    {
        return content;
    }

    @JsonView(Views.Internal.class)
    public long getUserId()
    {
        return userId;
    }
}
