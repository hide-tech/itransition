package com.yazukov.proj.service;

import com.yazukov.proj.domain.Comment;
import com.yazukov.proj.domain.CommentLike;
import com.yazukov.proj.domain.Item;
import com.yazukov.proj.domain.User;
import com.yazukov.proj.dto.CommentDto;
import com.yazukov.proj.dto.CommentTransfer;
import com.yazukov.proj.dto.LikeDto;
import com.yazukov.proj.mapper.CommentMapper;
import com.yazukov.proj.mapper.LikeMapper;
import com.yazukov.proj.repository.CommentRepository;
import com.yazukov.proj.repository.ItemRepository;
import com.yazukov.proj.repository.LikeRepository;
import com.yazukov.proj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public List<CommentTransfer> getCommentsByItemId(Long itemId){
        List<CommentDto> comments = commentRepository.findCommentsByItemId(itemId);
        return comments.stream().map(cm -> {
            List<LikeDto> likes = likeRepository.findByCommentId(cm.getId());
            CommentTransfer ct = new CommentTransfer();
            ct.setDescription(cm.getDescription());
            ct.setLikesAmount(likes.size());
            ct.setId(cm.getId());
            return ct;
        }).collect(Collectors.toList());
    }

    @Transactional
    public CommentDto createNewComment(Long itemId, Long userId, CommentDto commentDto){
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        User user = userRepository.findById(userId).get();
        Item item = itemRepository.findById(itemId).get();
        comment.setItem(item);
        comment.setUser(user);
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    @Transactional
    public CommentTransfer putLikeOnComment(Long commentId, Long userId){
        User user = userRepository.findById(userId).get();
        Comment comment = commentRepository.findById(commentId).get();
        CommentLike like = new CommentLike();
        like.setComment(comment);
        like.setUser(user);
        like = likeRepository.save(like);
        CommentTransfer ct = new CommentTransfer();
        ct.setId(comment.getId());
        ct.setDescription(comment.getDescription());
        ct.setLikesAmount(comment.getLikies().size()+1);
        return ct;
    }
}
