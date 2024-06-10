package com.hoangtuyen04work.instagram2.service.impl;

import com.hoangtuyen04work.instagram2.dto.request.CommentRequest;
import com.hoangtuyen04work.instagram2.dto.response.CommentResponse;
import com.hoangtuyen04work.instagram2.entity.CommentEntity;
import com.hoangtuyen04work.instagram2.entity.PostEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.exception.ErrorCode;
import com.hoangtuyen04work.instagram2.mapper.CommentMapper;
import com.hoangtuyen04work.instagram2.repository.CommentRepository;
import com.hoangtuyen04work.instagram2.service.interfaces.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CommentService  implements CommentServiceInterface {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public CommentResponse addComment(String postId, CommentRequest commentRequest) throws AppException {
        CommentEntity commentEntity = commentMapper.toCommentEntity(commentRequest);
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userService.findByUserId(userId);
        PostEntity postEntity = postService.findPostById(postId);
        commentEntity.setUser(userEntity);
        commentEntity.setPost(postEntity);
        commentRepository.save(commentEntity);
        return commentMapper.toCommentResponse(commentEntity);
    }

    @Override
    public Set<CommentResponse> getAllComment(String postId) throws AppException {
        if(!postService.existsPost(postId)) {
            throw new AppException(ErrorCode.POST_NOT_EXISTED);
        }
        Set<CommentEntity> comments = commentRepository.getAllByPostId(postId);
        Set<CommentResponse> commentResponses = new HashSet<>();
        for (CommentEntity commentEntity : comments) {
            commentResponses.add(commentMapper.toCommentResponse(commentEntity));
        }
        return commentResponses;
    }

    @Override
    public CommentResponse getComment(String commentId) throws AppException {
        if(!commentRepository.existsById(commentId)){
            throw new AppException(ErrorCode.COMMENT_NOT_EXISTED);
        }
        CommentEntity commentEntity = commentRepository.findById(commentId).get();
        return commentMapper.toCommentResponse(commentEntity);
    }

    @Override
    public CommentResponse updateComment(String commentId, CommentRequest commentRequest) throws AppException {
        UserEntity user = userService.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!commentRepository.existsById(commentId)){
            throw new AppException(ErrorCode.COMMENT_NOT_EXISTED);
        }
        CommentEntity commentEntity = commentRepository.findById(commentId).get();
        if(!Objects.equals(commentEntity.getUser().getId(), user.getId())){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        commentEntity.setContent(commentRequest.getContent());
        commentRepository.save(commentEntity);
        return commentMapper.toCommentResponse(commentEntity);
    }

    @Override
    public void deleteComment(String commentId) throws AppException {
        UserEntity user = userService.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        CommentEntity commentEntity = commentRepository.findById(commentId).get();
        if(!Objects.equals(commentEntity.getUser().getId(), user.getId())){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        commentRepository.delete(commentEntity);
    }
}
