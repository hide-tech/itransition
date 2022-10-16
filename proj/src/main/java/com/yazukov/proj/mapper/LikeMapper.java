package com.yazukov.proj.mapper;

import com.yazukov.proj.domain.CommentLike;
import com.yazukov.proj.dto.LikeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    CommentLike likeDtoToCommentLike(LikeDto likeDto);

    LikeDto commentLikeToLikeDto(CommentLike commentLike);
}
