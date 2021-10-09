package com.lam.book.client;

import com.lam.book.entity.MemberRestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member", fallback = MemberClient.MemberClientFallback.class)
public interface MemberClient {

    @GetMapping("/check")
    MemberRestResult checkMobile(@RequestParam("mobile") String mobile);


    @Component
    static class MemberClientFallback implements MemberClient{

        @Override
        public MemberRestResult checkMobile(String mobile) {
            MemberRestResult result = new MemberRestResult();
            result.setCode("1");
            result.setMessage("服务器繁忙，请稍后再试.");
            result.setData(null);
            return result;
        }
    }

}
