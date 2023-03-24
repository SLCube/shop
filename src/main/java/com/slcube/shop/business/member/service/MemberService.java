package com.slcube.shop.business.member.service;

import com.slcube.shop.business.member.dto.MemberChangePasswordRequestDto;
import com.slcube.shop.business.member.dto.MemberSignUpRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    Long signUp(MemberSignUpRequestDto requestDto);

    Long changePassword(MemberChangePasswordRequestDto requestDto);

    void emailDuplicatedCheck(String signUpId);
}
