package com.slcube.shop.business.order.dto;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.order.domain.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResponseDto {

    private Long orderItemId;
    private String itemName;
    private int itemPrice;
    private int quantity;

    public OrderItemResponseDto(OrderItem orderItem, Item item) {
        this.orderItemId = orderItem.getId();
        this.itemName = item.getItemName();
        this.itemPrice = item.getPrice();
        this.quantity = orderItem.getQuantity();
    }
}
