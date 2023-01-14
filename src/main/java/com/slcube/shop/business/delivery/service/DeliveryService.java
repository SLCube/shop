package com.slcube.shop.business.delivery.service;

import com.slcube.shop.business.delivery.dto.DeliveryResponseDto;
import com.slcube.shop.business.delivery.dto.DeliverySaveRequestDto;
import com.slcube.shop.business.delivery.dto.DeliveryUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {

    public Long saveDelivery(DeliverySaveRequestDto requestDto);

    public DeliveryResponseDto findDelivery(Long orderId);

    public Long updateDeliveryStatus(DeliveryUpdateRequestDto requestDto);
}
