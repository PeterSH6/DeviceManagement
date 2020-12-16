package com.example.cache;

import com.example.dao.UserRepository;
import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserCache {

    @Autowired
    private UserRepository userRepository;

    /**
     * 第一次需要用到用户信息时从数据库中载入到cache中，
     * 注销时自动销毁
     *
     * @param userName cache的key，也能确保用户登录唯一性
     * @return 返回用户实体类
     */
    @Cacheable(key = "#userName")
    public User getUser(String userName) {
        log.info("Caching user: " + userName + "...");
        return userRepository.findByUserName(userName);
    }

    @Cacheable(key = "#userId")
    public User getUser(Integer userId) {
        log.info("Caching user with id: " + userId + "...");
        return userRepository.findByUserId(userId);
    }

    @Caching(put = {
            @CachePut(key = "#user.userId"),
            @CachePut(key = "#user.userName")
    })
    public User putUser(User user) {
        log.info("Writing through User cache with <userId> : <" + user.getUserId() + ">...");
        userRepository.save(user);
        return user;
    }

    /**
     * 作为一个trigger，注销时触发，清楚此前登录的用户的缓存
     *
     * @param user 即将注销的用户
     */
    @Caching(evict = {
            @CacheEvict(key = "#user.userName"),
            @CacheEvict(key = "#user.userId")
    })
    public void evictUser(User user) {
        log.info("Evicting user: " + user.getUserName() + "...");
    }
}
