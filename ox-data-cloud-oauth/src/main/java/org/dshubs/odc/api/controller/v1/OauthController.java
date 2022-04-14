package org.dshubs.odc.api.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author create by wangxian 2021/12/29
 */
@RequestMapping
@Slf4j
@Controller
public class OauthController {

    @Value("${butterfly.oauth.redirect-url:login}")
    private String defaultUrl;

    @GetMapping({"/"})
    public String index() {
        return this.defaultUrl;
    }

    @GetMapping({"/login"})
    public String login() {
        return this.defaultUrl;
    }

    @GetMapping({"/home","/index"})
    public ModelAndView home() {
        ModelAndView modelAndVie    = new ModelAndView("index");
        modelAndVie.addObject("loginUser", "wangxian");
        return modelAndVie;
    }

}
