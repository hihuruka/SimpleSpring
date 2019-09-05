package com.hihruk.web;

import com.hihruk.domain.User;
import com.hihruk.service.UserService;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/index.html")
    public String loginPage(){
        return "login";
    }
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest httpRequest,LoginCommand loginCommand){
       boolean isVaildUser=userService.hasMatchUser(loginCommand.getUserName(),loginCommand.getPassword());
       if(!isVaildUser){
           return new ModelAndView("login","Error","当前用户用户名密码错误");
       }else{
           User user=userService.findUserByUserName(loginCommand.getUserName());
           user.setLastIp(httpRequest.getLocalAddr());
           user.setLastVisit(new Date());
           userService.loginSuccess(user);
           httpRequest.getSession().setAttribute("user",user);
           return  new ModelAndView("main");
       }
    }
}
