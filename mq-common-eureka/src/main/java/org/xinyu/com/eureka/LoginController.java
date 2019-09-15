package org.xinyu.com.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Controller
public class LoginController {

    @Value("${spring.redis.host}")
    public String redisHost;

    @RequestMapping("/eureka-server/login")
    public String loginPage() {
        //这边我们,默认是返到templates下的login.html
        return "login";
    }
}
