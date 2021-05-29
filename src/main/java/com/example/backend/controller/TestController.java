package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.entity.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/*Just for test
使用RestClient测试
见/test/java/com/restfulapi/demo/RestTemplateTest
*/

@RestController

public class TestController
{
    @Operation(description = "Just for Test")
    @RequestMapping("/test-DefaultException")
    public String testResponseStatusExceptionResolver()
    {
        throw new BusinessException(600,"自定义业务错误");
    }

    @Operation(description = "Just for Test")
    @GetMapping("/test-getparameter")
    public User getparameter(User user)
    {
        return user;
    }

    @Operation(description = "Just for Test")
    @GetMapping("/test-getuser")
    public User user1()
    {
        return new User(1,"myUser","123456");
    }

    @Operation(description = "Test of Valid User")
    @PostMapping("/test-postuser")
    public User postUser(@Valid User user, BindingResult result)
    {
        if(result.hasErrors())
            throw new BusinessException(601,"User Valid Error");
        System.out.println(user.toString());
        System.out.println("name:"+user.getName());
        System.out.println("id:"+user.getId());
        return user;
    }

    @Operation(description = "Just for Test")
    @PutMapping("/test-putuser")
    public void putUser(@RequestBody User user)
    {
        System.out.println(user.toString());
    }

    @Operation(description = "Just for Test")
    @DeleteMapping("/test-deleteuser")
    public void deleteUser(Long id)
    {
        System.out.println(id);
    }

    /*使用@schema中的allowableValues*/
    @Operation(description = "Just for Test")
    @GetMapping("/test-schema")
    public Object example(@Parameter(name ="json", schema = @Schema(description = "var 1",type = "string", allowableValues = {"1", "2"}))
                                  String json) {
        return null;
    }

    @Operation(description = "Test async")
    @GetMapping("/test-async")
    public void asyncTest()
    {
        System.out.println("thread start");
        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("thread end");
    }

}
