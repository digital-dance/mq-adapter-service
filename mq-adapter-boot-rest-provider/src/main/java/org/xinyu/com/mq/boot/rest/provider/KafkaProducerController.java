package org.xinyu.com.mq.boot.rest.provider;

import java.util.Map;

import com.digital.dance.framework.infrastructure.commons.Constants;
import com.digital.dance.framework.infrastructure.commons.GsonUtils;
import com.digital.dance.framework.infrastructure.commons.Log;
import com.digital.dance.framework.infrastructure.commons.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.xinyu.com.mq.Msg;
import org.xinyu.com.mq.ProducerHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/kafka")
@EnableDiscoveryClient
@RefreshScope
public class KafkaProducerController {

    protected static final Log LOG = new Log(KafkaProducerController.class);
//    @Autowired
//    private ProducerHandler producerHandler;

    @RequestMapping("/producer")
    @ResponseBody
    public ResponseVo sendMsg(HttpServletRequest request,
                              HttpServletResponse response, @ModelAttribute Msg pMsg)
            throws Exception {
        LOG.info( GsonUtils.toJsonStr(pMsg) );
        ResponseVo reVo = new ResponseVo();
        reVo.setResult(request.getSession().getId());

        Msg msg = pMsg;

        try {
//            Map<String, Object> res = producerHandler.sendMsg(msg);
//            LOG.info( GsonUtils.toJsonStr(res) );
//            System.out.println("测试结果如下：===============");
//            String message = (String) res.get("message");
//            reVo.setMsg(message);
//
//            String code = (String) res.get("code");
//            reVo.setCode(code);
//
//            System.out.println("code:" + code);
//            System.out.println("message:" + message);
            reVo.setCode(Constants.RETURN_CODE_SUCCESS);
            reVo.setMsg(Constants.SUCCESS_MSG);

        } catch (Exception ex){

            reVo.setResult(ex.getMessage());
            reVo.setMsg(Constants.FAILED_MSG);
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            String exJson = GsonUtils.toJsonStr(ex);
            LOG.error(exJson);
        }
        return reVo;
    }

    @RequestMapping("/producer/json")
    @ResponseBody
    public ResponseVo sendJsonMsg(HttpServletRequest request,
                              HttpServletResponse response, String pJsonMsg)
            throws Exception {
        LOG.info( (pJsonMsg) );
        ResponseVo reVo = new ResponseVo();
        reVo.setResult( request.getSession().getId() );

        Msg msg = GsonUtils.toObject(pJsonMsg, Msg.class);

        try {
//            Map<String, Object> res = producerHandler.sendMsg(msg);
//            LOG.info( GsonUtils.toJsonStr(res) );
//            System.out.println("测试结果如下：===============");
//            String message = (String) res.get("message");
//            reVo.setMsg(message);
//
//            String code = (String) res.get("code");
//            reVo.setCode(code);
//
//            System.out.println("code:" + code);
//            System.out.println("message:" + message);
            reVo.setCode(Constants.RETURN_CODE_SUCCESS);
            reVo.setMsg(Constants.SUCCESS_MSG);

        } catch (Exception ex){

            reVo.setResult(ex.getMessage());
            reVo.setMsg(Constants.FAILED_MSG);
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            String exJson = GsonUtils.toJsonStr(ex);
            LOG.error(exJson);
        }
        return reVo;
    }

//    @Test
//    public void main() {
//        //public static void main(String... args) {
//        //context = getInstance("classpath*:/ioc_conf/com-mq-boot-context.xml");
//
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
}
