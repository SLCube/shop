package com.slcube.shop.business.member.controller;

import com.slcube.shop.business.member.dto.MemberChangePasswordRequestDto;
import com.slcube.shop.business.member.dto.MemberSignUpRequestDto;
import com.slcube.shop.business.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody MemberSignUpRequestDto requestDto) {
        memberService.signUp(requestDto);
        // TODO : redirection 필요해보임
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{signUpEmail}")
    public ResponseEntity<String> idDuplicatedCheck(@PathVariable String signUpEmail) {
        memberService.emailDuplicatedCheck(signUpEmail);
        return ResponseEntity.ok("사용가능한 이메일입니다.");
    }

    @PatchMapping
    public ResponseEntity<Void> changePassword(@RequestBody MemberChangePasswordRequestDto requestDto) {
        memberService.changePassword(requestDto);
        // TODO : redirection 필요해보임
        return ResponseEntity.ok().build();
    }
}
