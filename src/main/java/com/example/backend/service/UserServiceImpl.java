package com.example.backend.service;

import com.example.backend.dao.UserRepository;
import com.example.backend.dao.UserService;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "user")
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUser()
    {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(key = "#id")
    public User findUserById(long id)
    {
        return userRepository.findById(id);
    }

    @Override
    @CachePut(key = "#user.id")
    public User insertUser(User user)
    {
        user=userRepository.save(user);
        return user;
    }

    @Override
    @CachePut(key = "#user.id")
    public User updateUserById(User user)
    {
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteUserById(long id)
    {
        userRepository.deleteById(id);
    }
}
