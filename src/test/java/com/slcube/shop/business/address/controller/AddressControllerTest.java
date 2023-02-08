package com.slcube.shop.business.address.controller;

import com.slcube.shop.business.address.dto.AddressResponseDto;
import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
import com.slcube.shop.business.address.dto.AddressUpdateRequestDto;
import com.slcube.shop.business.address.service.AddressService;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
@WithMockMember
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    @DisplayName("주소 저장")
    void saveAddressTest() throws Exception {
        AddressSaveRequestDto requestDto = new AddressSaveRequestDto();
        requestDto.setCity("test city");
        requestDto.setZipcode("test zipcode");
        requestDto.setDefaultAddress(true);
        requestDto.setStreet("test street");
        requestDto.setComment("test comment");

        given(addressService.saveAddress(eq(requestDto), any()))
                .willReturn(1L);

        mockMvc.perform(post("/api/address")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("주소 단건 조회")
    void findAddressTest() throws Exception {
        AddressResponseDto responseDto = new AddressResponseDto();
        responseDto.setAddressId(1L);
        responseDto.setDefaultAddress(true);
        responseDto.setCity("test city");
        responseDto.setZipcode("test zipcode");
        responseDto.setStreet("test street");

        given(addressService.findAddress(any(), any()))
                .willReturn(responseDto);

        mockMvc.perform(get("/api/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1L))
                .andExpect(jsonPath("$.city").value("test city"))
                .andExpect(jsonPath("$.zipcode").value("test zipcode"))
                .andExpect(jsonPath("$.street").value("test street"))
                .andExpect(jsonPath("$.defaultAddress").value(true));
    }

    @Test
    @DisplayName("주소 정보 수정")
    void updateAddressTest() throws Exception {
        Long addressId = 1L;
        AddressUpdateRequestDto requestDto = new AddressUpdateRequestDto();
        requestDto.setCity("update test city");
        requestDto.setZipcode("update test zipcode");
        requestDto.setStreet("update test street");
        requestDto.setDefaultAddress(false);
        requestDto.setComment("update test comment");

        given(addressService.updateAddress(eq(addressId), eq(requestDto), any()))
                .willReturn(addressId);

        mockMvc.perform(patch("/api/address/" + addressId)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}