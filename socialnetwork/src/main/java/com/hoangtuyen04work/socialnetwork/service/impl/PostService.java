package com.hoangtuyen04work.socialnetwork.service.impl;


import com.hoangtuyen04work.socialnetwork.dto.request.NewPostRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.mapper.PostMapper;
import com.hoangtuyen04work.socialnetwork.repository.PostRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.PostServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class PostService implements PostServiceInterface {
    PostRepository postRepository;
    PostMapper postMapper;
    UserService userService;
    @Override
    public List<PostEntity> find(String finded, Long page) throws AppException {
        List<PostEntity> postList = postRepository.findByTitleContainingOrContentContaining(finded, page);
        if(postList.isEmpty()){
            throw new AppException(ErrorCode.POST_NOT_EXISTED);
        }
        return postList;
    }

    @Override
    public Set<PostResponse> getAll() throws AppException {
        UserEntity userEntity = userService.getUserInHolder();
        Set<PostEntity> postEntities = postRepository.findAllByUser(userEntity);
        return postEntities.stream()
                .map(postMapper::toPostResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public PostEntity findById(String postId) throws AppException {
        return postRepository.findById(postId).orElseThrow(() ->  new AppException(ErrorCode.POST_NOT_EXISTED));
    }

    @Override
    public PostResponse getById(String id) throws AppException {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() ->  new AppException(ErrorCode.POST_NOT_EXISTED));
        return postMapper.toPostResponse(postEntity);
    }

    @Override
    public PostResponse create(NewPostRequest newPostRequest) throws AppException {
        PostEntity postEntity = postMapper.toPostEntity(newPostRequest);
        postEntity.setUser(userService.getUserInHolder());
        return postMapper.toPostResponse(save(postEntity));
    }
    @Override
    public PostEntity save(PostEntity postEntity){
        return postRepository.save(postEntity);
    }


    @Override
    public PostResponse update(String postId, NewPostRequest newPostRequest) throws AppException {
        PostEntity postEntity = findPost(postId);
        if(!userService.isYourSelf(postEntity.getUser().getId())){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        postEntity.setContent(newPostRequest.getContent());
        postEntity.setTitle(newPostRequest.getTitle());
        return postMapper.toPostResponse(postRepository.save(postEntity));
    }
    @Override
    public PostEntity findPost(String postId) throws AppException {
        return  postRepository.findById(postId)
                    .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
    }

    @Override
    public void delete(String postId) throws AppException {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND));
        if( ! userService.isYourSelf(postEntity.getUser().getId())) {
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        postRepository.delete(postEntity);
    }

    @Override
    public boolean exists(String postId) throws AppException {
        return postRepository.existsById(postId);
    }
}
