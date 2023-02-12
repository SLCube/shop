package com.slcube.shop.business.item.controller;

import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Long saveItem(@RequestBody ItemSaveRequestDto requestDto) {
        return itemService.saveItem(requestDto);
    }

    @GetMapping("/{itemId}")
    public ItemResponseDto findItem(@PathVariable Long itemId) {
        return itemService.findItem(itemId);
    }

    @DeleteMapping("/{itemId}")
    public Long deleteItem(@PathVariable Long itemId) {
        return itemService.deleteItem(itemId);
    }
}
