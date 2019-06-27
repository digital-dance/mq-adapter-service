package org.xinyu.com.mq.boot.rest;

import com.digital.dance.framework.infrastructure.commons.GsonUtils;
import com.digital.dance.framework.infrastructure.commons.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xinyu.com.mq.Msg;
import org.xinyu.com.mq.boot.api.KafkaProducer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api")
@EnableDiscoveryClient
public class ApiFaceController  {
    static Log log = new Log(ApiFaceController.class);
    @Autowired
    private KafkaProducer kafkaProducer;
    @RequestMapping("/kafka/producer")
    @ResponseBody
    public ResponseVo sendMsg(HttpServletRequest request,
                              HttpServletResponse response, @ModelAttribute Msg pMsg) throws Exception {
//        String value = request.getHeader("cookie");
        String sessionId = request.getRequestedSessionId();
        ResponseVo responseVo = kafkaProducer.sendMsg(pMsg.getTopic(), pMsg.getValue(), pMsg.getPartition(), pMsg.getRole(), pMsg.getIfPartition());
        log.info("org.xinyu.com.mq.boot.api.KafkaProducer.sendMsg - " + GsonUtils.toJsonStr(responseVo));


        //if(value == null || "".equalsIgnoreCase(value)){
//            response.setHeader("Set-Cookie", "JSESSIONID=" + responseVo.getResult() + ";path=/;HttpOnly");
        //}
        String ret = (String) responseVo.getResult();
        ret = ret + "; sessionId form rest[" + request.getLocalAddr() + ":" + request.getLocalPort() + "] before sent:" + sessionId + ":after sent:" + request.getRequestedSessionId();
        responseVo.setResult(ret);
        return responseVo;
    }

    @RequestMapping("/kafka/producer/json")
    @ResponseBody
    public ResponseVo sendJsonMsg(HttpServletRequest request,
                                  HttpServletResponse response, @ModelAttribute Msg pMsg)
            throws Exception {
//        String value = request.getHeader("cookie");
        ResponseVo responseVo = kafkaProducer.sendJsonMsg(GsonUtils.toJsonStr(pMsg));
        log.info("org.xinyu.com.mq.boot.api.KafkaProducer.sendJsonMsg - "+ GsonUtils.toJsonStr(responseVo) );
        //if(value == null || "".equalsIgnoreCase(value)){
//            response.setHeader("Set-Cookie", "JSESSIONID=" + responseVo.getResult() + ";path=/;HttpOnly");
        //}
        return responseVo;
    }
}
