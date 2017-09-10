package com.cheer.ssm.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cheer.ssm.dao.UserDao;
import com.cheer.ssm.dao.UserLogDao;
import com.cheer.ssm.domain.User;
import com.cheer.ssm.domain.UserLog;

import com.cheer.ssm.listener.ClientSession;

@Service
public class LoginService {
	
	private transient Log log=LogFactory.getLog(getClass());
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserLogDao userLogDao;
	
	@Transactional
	public void initTestData() {

		User user=new User();
		user.setUserName("admin");
		user.setPassword("12345678");
		user.setCredite(0);
		userDao.insert(user);
		log.info("prepared data success!");
	}
	
	public ClientSession login(String userName,String password,String ip) {
		Date current=new Date();
		ClientSession cs=new ClientSession();
		User exisUser=userDao.findForUserName(userName);
		if(exisUser==null) {
			cs.setLoginStatus(ClientSession.USER_NOT_EXISTS);
			
		}else {
			if(password.equals(exisUser.getPassword())) {
				cs.setLoginStatus(ClientSession.LOGINED);
				cs.setUser(exisUser);
				
				exisUser.setLastIp(ip);
				exisUser.setLastLogin(current);
				exisUser.setCredite(exisUser.getCredite()+1);
				userDao.update(exisUser);
				
				UserLog userLog=new UserLog();
				userLog.setId(exisUser.getId());
				userLog.setIp(ip);
				userLog.setLastLogin(current);
				userLogDao.insert(userLog);
				
				
				
			}else {
				cs.setLoginStatus(ClientSession.INVLID_PWD);
			}
		}
		return cs;
	}

}
