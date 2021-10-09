package com.lam.clientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MsgController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/msg")
    @ResponseBody
    public String getMsg() {
        return "Hello" + port;
    }

}
