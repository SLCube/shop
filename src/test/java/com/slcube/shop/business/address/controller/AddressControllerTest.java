package com.slcube.shop.business.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.address.service.AddressService;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
@WithMockMember
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("주소 저장")
    void saveAddressTest() throws Exception {
        Long addressId = 1L;

        AddressSaveRequestDto requestDto = new AddressSaveRequestDto();
        requestDto.setCity("test city");
        requestDto.setZipcode("test zipcode");
        requestDto.setStreet("test street");
        requestDto.setIsDefaultAddress(true);
        requestDto.setComment("test comment");

        given(addressService.saveAddress(any(AddressSaveRequestDto.class), any(MemberSessionDto.class)))
                .willReturn(addressId);


        mockMvc.perform(post("/api/address")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(addressId.toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("주소 단건 조회")
    void findAddressTest() throws Exception {
        AddressResponseDto responseDto = new AddressResponseDto();
        responseDto.setAddressId(1L);
        responseDto.setIsDefaultAddress(true);
        responseDto.setCity("test city");
        responseDto.setZipcode("test zipcode");
        responseDto.setStreet("test street");

        given(addressService.findAddress(anyLong(), any(MemberSessionDto.class)))
                .willReturn(responseDto);

        mockMvc.perform(get("/api/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1L))
                .andExpect(jsonPath("$.city").value("test city"))
                .andExpect(jsonPath("$.zipcode").value("test zipcode"))
                .andExpect(jsonPath("$.street").value("test street"))
                .andExpect(jsonPath("$.isDefaultAddress").value(true))
                .andDo(print());
    }

    @Test
    @DisplayName("주소 정보 수정")
    void updateAddressTest() throws Exception {
        Long addressId = 1L;

        AddressUpdateRequestDto requestDto = new AddressUpdateRequestDto();
        requestDto.setIsDefaultAddress(true);
        requestDto.setCity("test update city");
        requestDto.setStreet("test update street");
        requestDto.setZipcode("test update zipcode");
        requestDto.setComment("test update comment");

        given(addressService.updateAddress(anyLong(), any(AddressUpdateRequestDto.class), any(MemberSessionDto.class)))
                .willReturn(addressId);

        mockMvc.perform(patch("/api/address/" + addressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(addressId.toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("주소 정보 삭제")
    void deleteAddressTest() throws Exception {
        Long addressId = 1L;

        given(addressService.deleteAddress(anyLong(), any(MemberSessionDto.class)))
                .willReturn(addressId);

        mockMvc.perform(delete("/api/address/" + addressId)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(addressId.toString()))
                .andDo(print());
    }
}