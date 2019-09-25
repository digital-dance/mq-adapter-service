package org.xinyu.com.mq.boot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/heath")
    @ResponseBody
    public String heath()
    {
        return "ok";
    }
}
