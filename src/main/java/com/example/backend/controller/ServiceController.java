package com.example.backend.controller;

import com.example.backend.entity.Subscribe;
import com.example.backend.entity.result.ExceptionMsg;
import com.example.backend.entity.result.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController
{
    @GetMapping("/subscribe")
    public ResponseData subscribe()
    {
        Subscribe subscribe=new Subscribe();
        return new ResponseData(ExceptionMsg.SUCCESS,subscribe);
    }
}
