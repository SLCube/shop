package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.member.MemberChangePasswordRequestDto;
import com.slcube.shop.business.dto.member.MemberSignUpRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    public Long signUp(MemberSignUpRequestDto requestDto);

    public Long changePassword(MemberChangePasswordRequestDto requestDto);
}
