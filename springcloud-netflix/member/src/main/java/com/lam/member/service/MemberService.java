package com.lam.member.service;

import com.lam.member.entity.Member;
import com.lam.member.repository.MemberRepository;
import com.lam.member.service.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberRepository memberRepository;

    public Member checkByMobile(String mobile) {
        List<Member> memberList = memberRepository.findByMobile(mobile);
        if (memberList.size() == 0) {
            throw new MemberNotFoundException("会员不存在");
        }
        return memberList.get(0);
    }
}
