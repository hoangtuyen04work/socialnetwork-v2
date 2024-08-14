package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.request.CommentRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.CommentResponse;
import com.hoangtuyen04work.socialnetwork.entity.CommentEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.mapper.CommentMapper;
import com.hoangtuyen04work.socialnetwork.repository.CommentRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.CommentServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class CommentService  implements CommentServiceInterface {
    CommentRepository commentRepository;
    CommentMapper commentMapper;
    UserService userService;
    PostService postService;

    @Override
    public CommentResponse add(CommentRequest commentRequest) throws AppException {

        CommentEntity commentEntity = CommentEntity.builder()
                .content(commentRequest.getContent())
                .post(postService.findById(commentRequest.getPostId()))
                .user(userService.getUserInHolder())
                .build();
        commentRepository.save(commentEntity);
        return commentMapper.toCommentResponse(commentEntity);
    }

    @Override
    public Set<CommentResponse> getAll(IdRequest idRequest) throws AppException {
        if(!postService.exists(idRequest.getId())) {
            throw new AppException(ErrorCode.POST_NOT_EXISTED);
        }
        Set<CommentEntity> comments = commentRepository.getAllByPostId(idRequest.getId());
        Set<CommentResponse> commentResponses = new HashSet<>();
        for (CommentEntity commentEntity : comments) {
            commentResponses.add(commentMapper.toCommentResponse(commentEntity));
        }
        return commentResponses;
    }

    @Override
    public CommentResponse get(String id) throws AppException {
        if(!commentRepository.existsById(id)){
            throw new AppException(ErrorCode.COMMENT_NOT_EXISTED);
        }
        CommentEntity commentEntity = commentRepository.findById(id).get();
        return commentMapper.toCommentResponse(commentEntity);
    }

    @Override
    public CommentResponse update(CommentRequest commentRequest) throws AppException {
        UserEntity user = userService.getUserInHolder();
        if(!commentRepository.existsById(commentRequest.getCommentId())){
            throw new AppException(ErrorCode.COMMENT_NOT_EXISTED);
        }
        CommentEntity commentEntity = commentRepository.findById(commentRequest.getCommentId()).get();
        if(!Objects.equals(commentEntity.getUser().getId(), user.getId())){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        commentEntity.setContent(commentRequest.getContent());
        commentRepository.save(commentEntity);
        return commentMapper.toCommentResponse(commentEntity);
    }

    @Override
    public void delete(CommentRequest commentRequest) throws AppException {
        UserEntity user = userService.getUserInHolder();
        CommentEntity commentEntity = commentRepository.findById(commentRequest.getCommentId()).get();
        if(!Objects.equals(commentEntity.getUser().getId(), user.getId())){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        commentRepository.delete(commentEntity);
    }
}
