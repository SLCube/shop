package com.slcube.shop.business.item.controller;

import com.slcube.shop.business.item.dto.ItemListResponseDto;
import com.slcube.shop.business.item.dto.ItemResponseDto;
import com.slcube.shop.business.item.dto.ItemSaveRequestDto;
import com.slcube.shop.business.item.dto.ItemUpdateRequestDto;
import com.slcube.shop.business.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Long saveItem(@RequestBody @Valid ItemSaveRequestDto requestDto) {
        return itemService.saveItem(requestDto);
    }

    @GetMapping("/{itemId}")
    public ItemResponseDto findItem(@PathVariable Long itemId) {
        return itemService.findItem(itemId);
    }

    @GetMapping
    public Page<ItemListResponseDto> findAllItems(Long categoryId, Pageable pageable) {
        return itemService.findItems(categoryId, pageable);
    }

    @PatchMapping("/{itemId}")
    public Long updateItem(@PathVariable Long itemId, @RequestBody @Valid ItemUpdateRequestDto requestDto) {
        return itemService.updateItem(itemId, requestDto);
    }

    @DeleteMapping("/{itemId}")
    public Long deleteItem(@PathVariable Long itemId) {
        return itemService.deleteItem(itemId);
    }
}
