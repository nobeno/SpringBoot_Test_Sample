package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.UserDao;
import com.app.entity.User;

@Controller
@RequestMapping("/db")
public class DBController {

	//ユーザDAO
	@Autowired
	private UserDao userDao;

	/**
	 * 初期処理
	 * DBから取得したユーザエンティティのリストをモデルへセットし、画面へ渡す。
	 * @param model リクエストスコープへ載せるモデルパラメータ
	 * @return ホーム画面のビュー
	 */
	@RequestMapping("/init")
	private String init(Model model) {

		// DBからユーザテーブルの全てのレコードを取得
		List<User> userList = userDao.findAllUser();

		model.addAttribute("userList", userList);

		return "home";
	}

}