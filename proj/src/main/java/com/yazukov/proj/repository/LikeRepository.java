package com.yazukov.proj.repository;

import com.yazukov.proj.domain.CommentLike;
import com.yazukov.proj.dto.LikeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<CommentLike, Long> {

    @Query("select distinct new com.yazukov.proj.dto.LikeDto(cl.id) " +
            "from CommentLike cl " +
            "where cl.comment.id = ?1")
    List<LikeDto> findByCommentId(Long id);
}
