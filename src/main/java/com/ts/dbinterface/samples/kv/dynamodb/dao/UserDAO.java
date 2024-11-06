package com.ts.dbinterface.samples.kv.dynamodb.dao;

import com.ts.dbinterface.samples.kv.dynamodb.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User findById(String userID);

    boolean add(User user);

    boolean update(User user);

    boolean delete(String userID);
}
