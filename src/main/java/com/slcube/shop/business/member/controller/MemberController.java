package com.slcube.shop.business.member.controller;

import com.slcube.shop.business.member.dto.MemberChangePasswordRequestDto;
import com.slcube.shop.business.member.dto.MemberSignUpRequestDto;
import com.slcube.shop.business.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Long signUp(@RequestBody MemberSignUpRequestDto requestDto) {
        return memberService.signUp(requestDto);
    }

    @PatchMapping
    public Long changePassword(@RequestBody MemberChangePasswordRequestDto requestDto) {
        return memberService.changePassword(requestDto);
    }
}
