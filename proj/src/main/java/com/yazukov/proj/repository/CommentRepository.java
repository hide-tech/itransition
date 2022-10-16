package com.yazukov.proj.repository;

import com.yazukov.proj.domain.Comment;
import com.yazukov.proj.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select distinct new com.yazukov.proj.dto.CommentDto(c.id, c.description) " +
            "from Comment c " +
            "where c.item.id = ?1")
    List<CommentDto> findCommentsByItemId(Long itemId);
}
