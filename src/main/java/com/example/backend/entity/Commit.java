package com.example.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long articleId;
    private String content;

    public Commit(long userId,long articleId,String content)
    {
        this.userId=userId;
        this.articleId=articleId;
        this.content=content;
    }
    public Commit(long id)
    {
        this.id=id;
    }
    public Commit()
    {

    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public long getArticleId()
    {
        return articleId;
    }

    public void setArticleId(long articleId)
    {
        this.articleId = articleId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
