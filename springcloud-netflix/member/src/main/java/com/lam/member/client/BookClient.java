package com.lam.member.client;

import com.lam.member.entity.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "book")
public interface BookClient {

    @GetMapping("/info")
    RestResult getInfo(@RequestParam("bid") Long bid);
}
