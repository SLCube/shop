package com.slcube.shop.business.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.member.dto.MemberSignUpRequestDto;
import com.slcube.shop.business.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static java.nio.charset.StandardCharsets.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@WithMockUser
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입")
    void signUpTest() throws Exception {
        MemberSignUpRequestDto requestDto = new MemberSignUpRequestDto();
        requestDto.setUsername("test username");
        requestDto.setEmail("test@naver.com");
        requestDto.setPassword("test password");

        given(memberService.signUp(eq(requestDto)))
                .willReturn(1L);

        mockMvc.perform(post("/api/members")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestDto))
                        .characterEncoding(UTF_8))
                .andExpect(status().isOk());
    }
}