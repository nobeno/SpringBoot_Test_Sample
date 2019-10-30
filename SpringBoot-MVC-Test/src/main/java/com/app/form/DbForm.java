package com.app.form;

import java.util.List;

import com.app.entity.User;

/**
 * ユーザエンティティのリストを格納するためのフォーム
 * @author aoi
 *
 */
public class DbForm {

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}