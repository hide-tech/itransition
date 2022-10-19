package com.yazukov.proj.controller;

import com.yazukov.proj.dto.CollectionDto;
import com.yazukov.proj.exception.CollectionNotFoundException;
import com.yazukov.proj.exception.UnsupportOperationException;
import com.yazukov.proj.exception.UserNotFoundException;
import com.yazukov.proj.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}/collections")
    public ResponseEntity<List<CollectionDto>> getCollectionsByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(collectionService.getCollectionByUserId(userId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}/collections/{collectionId}")
    public ResponseEntity<CollectionDto> getCollectionById(@PathVariable("collectionId") Long collectionId)
            throws CollectionNotFoundException {
        return ResponseEntity.ok(collectionService.getCollectionById(collectionId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{userId}/collections")
    public ResponseEntity<?> createNewCollection(@PathVariable("userId") Long userId,
                                                 @RequestBody CollectionDto collectionDto)
            throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(collectionService.createNewCollection(userId, collectionDto));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{userId}/collections")
    public ResponseEntity<?> updateCollection(@RequestBody CollectionDto collectionDto)
            throws CollectionNotFoundException {
        return ResponseEntity.ok(collectionService.updateCollection(collectionDto));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}/collections/{collectionId}")
    public ResponseEntity<?> deleteCollection(@PathVariable("collectionId") Long collectionId)
            throws UnsupportOperationException, CollectionNotFoundException {
        collectionService.deleteCollection(collectionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
