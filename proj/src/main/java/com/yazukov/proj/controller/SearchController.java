package com.yazukov.proj.controller;

import com.yazukov.proj.dto.ItemDto;
import com.yazukov.proj.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items/search")
public class SearchController {

    private final ElasticSearchService elasticSearchService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/name")
    public ResponseEntity<List<ItemDto>> searchItemByName(@RequestParam("nm") String name){
        return ResponseEntity.ok(elasticSearchService.searchByName(name));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/tags")
    public ResponseEntity<List<ItemDto>> searchItemByTags(@RequestParam("tg") List<String> tags){
        return ResponseEntity.ok(elasticSearchService.searchByTags(tags));
    }
}
