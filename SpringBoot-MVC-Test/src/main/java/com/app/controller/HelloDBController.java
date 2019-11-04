package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.UserDao;
import com.app.entity.User;
import com.app.form.DbForm;

@Controller
@RequestMapping("/helloDB")
public class HelloDBController {

	@Autowired
	private UserDao userDao;

	@RequestMapping("/init")
	private String init(Model model) {

		List<User> userList = userDao.findAllUser();

		DbForm form = new DbForm();
		form.setUserList(userList);

		model.addAttribute("dbForm", form);

		return "helloDB";
	}

}