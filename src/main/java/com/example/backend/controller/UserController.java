package com.example.backend.controller;
import com.example.backend.dao.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import com.example.backend.dao.UserRepository;
import com.example.backend.dao.Views;
import com.example.backend.entity.User;
import com.example.backend.entity.result.ExceptionMsg;
import com.example.backend.entity.result.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    //private UserRepository userRepository;
    private UserService userService;

    @Operation(description = "展示所有用户")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public ResponseData getUserList()
    {
        return new ResponseData(ExceptionMsg.SUCCESS,userService.findUser());
    }

    @Operation(description = "根据id查询用户")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public ResponseData getUserById(@PathVariable long id)
    {
        return new ResponseData(ExceptionMsg.SUCCESS,userService.findUserById(id));
    }

    @Operation(description = "注册用户")
    @RequestMapping(value = "/{name}/{password}",method = RequestMethod.POST)
    public ResponseData addUser(@PathVariable String name, @PathVariable String password)
    {
        User user=new User(name,password);
        return new ResponseData(ExceptionMsg.SUCCESS,userService.insertUser(user));
    }
    @Operation(description = "注册用户")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseData addUser(@RequestBody User user)
    {
        //User user=new User(name,password);
        return new ResponseData(ExceptionMsg.SUCCESS,userService.insertUser(user));
    }
}