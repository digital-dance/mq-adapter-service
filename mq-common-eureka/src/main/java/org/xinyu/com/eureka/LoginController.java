package org.xinyu.com.eureka;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/eureka-server/login")
    public String loginPage() {
        //这边我们,默认是返到templates下的login.html
        return "login";
    }
}
