package com.lam.member.Controller;

import com.alibaba.fastjson.JSONObject;
import com.lam.member.client.BookClient;
import com.lam.member.entity.Member;
import com.lam.member.entity.RestResult;
import com.lam.member.service.MemberService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@DefaultProperties(defaultFallback = "defaultFallback")
public class MemberController {

    @Resource
    private MemberService memberService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookClient bookClient;

    @GetMapping("/check")
    @ResponseBody
    public Map checkMobile(String mobile) {
        Map result = new HashMap();

        try {
            Member member = memberService.checkByMobile(mobile);
            result.put("code", 0);
            result.put("message", "success");
            result.put("data", member);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", e.getClass().getSimpleName());
            result.put("message", e.getMessage());
        }
        return result;
    }

    // @HystrixCommand(fallbackMethod = "testFallback")
    @HystrixCommand
    @GetMapping("/test")
    @ResponseBody
    public RestResult test(Long bid) {
        RestResult json = restTemplate.getForObject("http://book/info?bid=" + bid, RestResult.class);
        return json;
    }

    private JSONObject testFallback(Long bid) {
        String s = "{\"msg\":\"服务器正忙，请稍后再试\"}";
        return JSONObject.parseObject(s);
    }


    @GetMapping("/test1")
    @ResponseBody
    public RestResult test1(Long bid) {
        RestResult result = bookClient.getInfo(bid);
        return result;
    }

    private RestResult defaultFallback() {
        RestResult result = new RestResult();
        result.setCode("xx");
        result.setMessage("[全局]服务器正忙，请稍后再试");
        return result;
    }
}
