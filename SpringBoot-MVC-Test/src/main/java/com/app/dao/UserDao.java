package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.entity.User;
import com.app.util.QueryBuilder;

@Component
public class UserDao extends BaseDao<User> {

	public List<User> findAllUser(){
		QueryBuilder query = new QueryBuilder();
		query.append("select user_id, user_name from users");
		return findResultList(query.createQuery(User.class, getEm()));
	}
}
