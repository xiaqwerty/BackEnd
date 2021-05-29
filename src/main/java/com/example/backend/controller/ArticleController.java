package com.example.backend.controller;

import com.example.backend.dao.ArticleRepository;
import com.example.backend.entity.Article;
import com.example.backend.entity.result.ExceptionMsg;
import com.example.backend.entity.result.Response;
import com.example.backend.entity.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController
{
    protected Response result(ExceptionMsg msg)
    {
        return new Response(msg);
    }
    protected Response result()
    {
        return new Response();
    }
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseData getArticleList()
    {
        List<Article> list=new ArrayList<Article>(articleRepository.findAll());
        return new ResponseData(ExceptionMsg.SUCCESS,list);
    }

    @RequestMapping(value="/{title}/{content}",method = RequestMethod.POST)
    public ResponseData add(@PathVariable String title, @PathVariable String content)
    {
        System.out.println("成功进入post");
        Article article=new Article(title,content);
        articleRepository.save(article);
        return new ResponseData(ExceptionMsg.SUCCESS,article);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public Response delete(@PathVariable long id)
    {
        System.out.println("成功进入delete");
        articleRepository.deleteById(id);
        return result(ExceptionMsg.SUCCESS);
    }

    @RequestMapping(value="/{id}/{title}/{content}",method = RequestMethod.PUT)
    public ResponseData update(@PathVariable long id, @PathVariable String title, @PathVariable String content)
    {
        System.out.println("成功进入put");
        Article article=articleRepository.findById(id);
        article.putArticle(title,content);
        articleRepository.saveAndFlush(article);
        return new ResponseData(ExceptionMsg.SUCCESS,article);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseData findArticle(@PathVariable Integer id)throws IOException
    {
        System.out.println("成功进入查询");
        //Article article=articleRepository.findById(id);
        Article article=articleRepository.findById(id);
        return new ResponseData(ExceptionMsg.SUCCESS,article);
    }
}
