package com.slcube.shop.business.address.controller;

import com.slcube.shop.business.address.dto.AddressSaveRequestDto;
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
}