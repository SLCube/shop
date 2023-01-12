package com.slcube.shop.business.member;

import com.slcube.shop.business.member.MemberChangePasswordRequestDto;
import com.slcube.shop.business.member.MemberSignUpRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    public Long signUp(MemberSignUpRequestDto requestDto);

    public Long changePassword(MemberChangePasswordRequestDto requestDto);
}
