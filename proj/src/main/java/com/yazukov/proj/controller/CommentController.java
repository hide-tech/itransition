package com.yazukov.proj.controller;

import com.yazukov.proj.dto.CommentDto;
import com.yazukov.proj.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}/collections/{collectionId}/items/{itemId}/comments")
    public ResponseEntity<?> getCommentsByItemId(@PathVariable("itemId") Long itemId){
        return ResponseEntity.ok(commentService.getCommentsByItemId(itemId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{userId}/collections/{collectionId}/items/{itemId}/comments")
    public ResponseEntity<?> createNewComment(@PathVariable("itemId") Long itemId,
                                              @RequestParam("userId") Long userId,
                                              @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.createNewComment(itemId, userId, commentDto));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{userId}/collections/{collectionId}/items/{itemId}/comments/{commentId}")
    public ResponseEntity<?> putLikeOnComment(@PathVariable("commentId") Long commentId,
                                              @RequestParam("userId") Long userId){
        return ResponseEntity.ok(commentService.putLikeOnComment(commentId, userId));
    }
}
