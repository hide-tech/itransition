package com.yazukov.proj.controller;

import com.yazukov.proj.dto.ItemDto;
import com.yazukov.proj.exception.CollectionNotFoundException;
import com.yazukov.proj.exception.ItemNotFoundException;
import com.yazukov.proj.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}/collections/{collectionId}/items")
    public ResponseEntity<?> getItemsByCollectionId(@PathVariable("collectionId") Long collectionId){
        return ResponseEntity.ok(itemService.getItemsByCollectionId(collectionId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}/collections/{collectionId}/items/{itemId}")
    public ResponseEntity<?> getItemByItemId(@PathVariable("itemId") Long itemId) throws ItemNotFoundException {
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{userId}/collections/{collectionId}/items")
    public ResponseEntity<?> createNewItem(@PathVariable("collectionId") Long collectionId,
                                           @PathVariable("userId") Long userId,
                                           @RequestBody ItemDto itemDto) throws CollectionNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createNewItem(collectionId, userId, itemDto));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{userId}/collections/{collectionId}/items")
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto) throws ItemNotFoundException {
        return ResponseEntity.ok(itemService.updateItem(itemDto));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}/collections/{collectionId}/items/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable("itemId") Long itemId) throws ItemNotFoundException {
        itemService.deleteItem(itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
