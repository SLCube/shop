package com.slcube.shop.business.delivery.service;

import com.slcube.shop.business.delivery.dto.DeliveryResponseDto;
import com.slcube.shop.business.delivery.dto.DeliverySaveRequestDto;
import com.slcube.shop.business.delivery.dto.DeliveryUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {

    Long saveDelivery(DeliverySaveRequestDto requestDto);

    DeliveryResponseDto findDelivery(Long orderId);

    Long updateDeliveryStatus(DeliveryUpdateRequestDto requestDto);
}
