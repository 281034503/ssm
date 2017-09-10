package com.cheer.ssm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cheer.ssm.dao.UserDao;

@Controller
@RequestMapping
public class LoginController {
	
	private transient Log log=LogFactory.getLog(getClass());
	
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		log.debug(userDao);
		return "login";
	}
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(){
		return "home";
	}
	
	@RequestMapping(value="/ping",method=RequestMethod.GET)
	public String ping(){
		return "ping";
	}
	
	
	
	
}
