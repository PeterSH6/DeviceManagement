package com.example.dao;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * find user by name
     * @param userName
     * return a User
     */
    User findByUserName(String userName);

    /**
     * find user by id
     * @param userId
     * return a User
     */
    User findByUserId(Integer userId);

    /**
     * save a user in the db
     * not read-only
     * user must have the primary key or it will become add not update
     * @return a user
     */
    @Transactional
    User save(User user);

    /**
     * save a series of users in the db
     * not read-only
     * user must have the primary key or it will become add not update
     * @return a user
     */
    @Transactional
    List<User> save(Iterable<User> User);

    /**
     * delete a user
     * @param userId
     */
    @Transactional
    @Modifying
    void deleteByUserId(Integer userId);




}
