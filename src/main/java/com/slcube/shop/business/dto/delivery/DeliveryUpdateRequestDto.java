package com.slcube.shop.business.dto.delivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryUpdateRequestDto {

    private Long deliveryId;
    // 배송여부는 enum으로 저장해야겠지...
}
