package com.yazukov.proj.mapper;

import com.yazukov.proj.domain.Comment;
import com.yazukov.proj.dto.CommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentDtoToComment(CommentDto commentDto);

    CommentDto commentToCommentDto(Comment comment);
}
