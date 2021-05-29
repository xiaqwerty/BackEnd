package com.example.backend.entity.result;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.backend.dao.Views;


public class Response
{
    private String rspCode="200";
    private String rspMsg="操作成功";

    public Response(ExceptionMsg msg)
    {
        this.rspCode=msg.getCode();
        this.rspMsg=msg.getMsg();
    }
    public Response() {}

    @JsonView(Views.Public.class)
    public String getRspCode()
    {
        return rspCode;
    }

    @JsonView(Views.Public.class)
    public String getRspMsg()
    {
        return rspMsg;
    }
}
