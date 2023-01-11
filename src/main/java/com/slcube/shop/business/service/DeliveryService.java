package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.delivery.DeliveryResponseDto;
import com.slcube.shop.business.dto.delivery.DeliverySaveRequestDto;
import com.slcube.shop.business.dto.delivery.DeliveryUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {

    public Long saveDelivery(DeliverySaveRequestDto requestDto);

    public DeliveryResponseDto findDelivery(Long orderId);

    public Long updateDeliveryStatus(DeliveryUpdateRequestDto requestDto);
}
