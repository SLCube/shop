package com.slcube.shop.business.member.service;

import com.slcube.shop.business.member.dto.MemberChangePasswordRequestDto;
import com.slcube.shop.business.member.dto.MemberLoginDto;
import com.slcube.shop.business.member.dto.MemberResponseDto;
import com.slcube.shop.business.member.dto.MemberSignUpRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    public Long signUp(MemberSignUpRequestDto requestDto);
    public MemberResponseDto login(MemberLoginDto requestDto);

    public Long changePassword(MemberChangePasswordRequestDto requestDto);
}
