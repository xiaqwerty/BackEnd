package com.example.backend;

import com.example.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用com.restfuldemo.demo.controller.TestController里的api
 */

/**
 * 原来的login()使用springsecurity自带login表单
 * 加入springsecurity功能后，带login()的方法能登陆并成功完成业务，其他的看看得了
 *
 */

public class RestTemplateTest
{
   /* private HttpHeaders login()//模拟登陆来获取cookie
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        MultiValueMap<String,Object> paramMap=new LinkedMultiValueMap<>();
        paramMap.add("username","admin");
        paramMap.add("password","123456");
        ResponseEntity<String> response=client.postForEntity("http://localhost:8080/login",paramMap,String.class);
        List<String> cookies=new ArrayList<>();
        cookies.add(response.getHeaders().get("Set-Cookie").get(0));
        HttpHeaders headers=new HttpHeaders();
        headers.put(HttpHeaders.COOKIE,cookies);
        return headers;
    }*/

    @Test
    public void testException()//错误
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client= restTemplateBuilder.build();
        Exception e=client.getForObject("http://localhost:8080/DefaultException",Exception.class);
        System.out.println(e);
    }


    @Test
    public void nparameters()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client= restTemplateBuilder.build();
        //登陆Cookie
        ResponseEntity<String> responseEntity=client.getForEntity("http://localhost:8080/getuser",String.class);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getBody().getClass());
    }

    @Test
    public void withparameters1()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        //ResponseEntity<String> responseEntity=client.getForEntity("http://localhost:8080/getparameter?name={9}&id={9}",
        //        String.class,"hua",2);//这里的数字“9”是占位符，数值是多少无所谓
        ResponseEntity<String> responseEntity=client.getForEntity("http://localhost:8080/getparameter?name={9}&id={9}",
                String.class,"hua",2);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getBody().getClass());
    }

    @Test
    public void withparameters2()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        Map<String,String> map=new HashMap<>();
        map.put("name","zhonghuaLong");
        ResponseEntity<String> responseEntity=client.getForEntity("http://localhost:8080/getparameter?name={name}&id=3",
                String.class,map);//最后一个参数可以是map，前面的占位符是map的key值，实际传参为对应的value值
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getBody().getClass());
    }

    @Test
    public void returnUser1()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        ResponseEntity<User> responseEntity=client.getForEntity("http://localhost:8080/getuser1",User.class);//注意需要提供User的默认构造方法，供容器装配
        System.out.println(responseEntity.getBody().getId());//此时的getBody获得的直接是api传回的user对象
        System.out.println(responseEntity.getBody().getName());
        System.out.println(responseEntity.getBody().getClass());
    }

    @Test
    public void getForObject()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        User user=client.getForObject("http://localhost:8080/getuser",User.class);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getClass());
    }

    @Test
    public void postForEntity()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        MultiValueMap<String,Object> paramMap=new LinkedMultiValueMap<>();//Map得以multivaluemap的形式传递
        paramMap.add("name","longzhiran");
        ResponseEntity<User> responseEntity=client.postForEntity("http://localhost:8080/test-postuser",paramMap,User.class);//paramap为post传递的参数
        //尽管post的controller要求的参数是user对象，仍然能正确地使用map传参
        User user=new User();
        ResponseEntity<User> responseEntity1=client.postForEntity("http://localhost:8080/test-postuser",user,User.class);
        //也可直接传user对象
        System.out.println(responseEntity.getBody().getId());
        System.out.println(responseEntity.getBody().getName());
        System.out.println(responseEntity.getBody().getClass());
        System.out.println(responseEntity1.getBody().toString());
    }

    @Test
    public void postForObject()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        MultiValueMap<String,Object> paramMap=new LinkedMultiValueMap<>();
        paramMap.add("name","longzhonghua");
        paramMap.add("id",4);
        String response=client.postForObject("http://localhost:8080/postuser",paramMap,String.class);
        System.out.println(response);
    }

    @Test
    public void postForexchange()//exchange可以指定http方法
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        MultiValueMap<String,Object> paramMap=new LinkedMultiValueMap<>();
        paramMap.add("name","longzhonghua");
        paramMap.add("id",4);
        HttpHeaders headers=new HttpHeaders();
        HttpEntity<MultiValueMap<String,Object>> httpEntity=new HttpEntity<>(paramMap,headers);
        ResponseEntity<String> responseEntity=client.exchange("http://localhost:8080/postuser", HttpMethod.POST,httpEntity,String.class,paramMap);
        //RestTemplate.exchange(url,HttpMethod,request,Object.class,*(param))
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void put()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        User user=new User(1,"name1","123456");
        System.out.println(user.toString());
        /*Map<String,User> map=new HashMap();
        map.put("name",user);*/
        client.put("http://localhost:8080/putuser",user);
    }

    @Test
    public void delete()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        RestTemplate client=restTemplateBuilder.build();
        User user=new User(2,"name2","123456");
        client.delete("http://localhost:8080/deleteuser?id={1}",user.getId());
    }
}
