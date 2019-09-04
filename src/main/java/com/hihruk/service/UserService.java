package com.hihruk.service;

import com.hihruk.dao.LoginLogDao;
import com.hihruk.dao.UserDao;
import com.hihruk.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hihruk.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    public LoginLogDao loginLogDao;
    public UserDao userDao;
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public boolean hasMatchUser(String userName,String password){
        return userDao.getMatchCount(userName,password)>0;
    }
    public User findUserByUserName(String userName){
        return  userDao.findUserByUserName(userName);
    }
    @Transactional(rollbackFor = Exception.class)
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog=new LoginLog();
        loginLog.setLoginDate(user.getLastVisit());
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        userDao.updateLoginInfo(user);
        loginLogDao.insetLoginLog(loginLog);
    }
}
