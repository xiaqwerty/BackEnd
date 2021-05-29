package com.example.backend.controller;

import com.example.backend.dao.ArticleRepository;
import com.example.backend.dao.CommitRepository;
import com.example.backend.dao.UserRepository;
import com.example.backend.entity.Article;
import com.example.backend.entity.Commit;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FrontPage
{
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommitRepository commitRepository;

    @RequestMapping("/")
    public String frontPage()
    {
        return "frontpage";
    }
    @PostMapping("/postblog")
    public String postblog(HttpServletRequest req,
                           Model model,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content)
    {
        if(content.equals("") || content.length()<20)
        {
            model.addAttribute("result", "文章内容太短");
            return "postblog";
        }
        if(title.equals(""))
        {
            title = content.substring(0, 10);
        }
        long userId = (long)req.getSession().getAttribute("userId");
        Article article=new Article(userId,title,content);
        articleRepository.save(article);
        User user=userRepository.findById(userId);
        user.incArticleNum(true);
        userRepository.save(user);
        return "redirect:frontpage?sort=time&start=0&size=2";
    }
    @PostMapping("/postCommit")
    public String postCommit(HttpServletRequest req,
                             Model model,
                             @RequestParam Long articleId,
                             @RequestParam String content)
    {
        if(content.equals(""))
        {
            model.addAttribute("result","评论不能为空！");
            return "postcommit";
        }
        Commit commit=new Commit();
        commit.setArticleId(articleId);
        commit.setUserId((Long) req.getSession().getAttribute("userId"));
        commit.setContent(content);
        Article article=new Article();
        if(articleRepository.findById(articleId).isPresent())
        {
            article = articleRepository.findById(articleId).get();
        }
        else System.out.println("article not exist");
        article.incCommitNum(true);
        articleRepository.saveAndFlush(article);
        commitRepository.save(commit);
        return "redirect:blog?blogId=";
    }
}
