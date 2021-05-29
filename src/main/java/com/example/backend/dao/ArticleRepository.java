package com.example.backend.dao;

import com.example.backend.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long>
{
    Article findById(long id);
    //@Query(value = "select * from Article where id=:id",nativeQuery = true)
    //List<Article> findBySql(@Param("id") long id);
}
