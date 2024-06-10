package com.hoangtuyen04work.instagram2.service.impl;

import com.hoangtuyen04work.instagram2.dto.request.PostLikeRequest;
import com.hoangtuyen04work.instagram2.entity.PostEntity;
import com.hoangtuyen04work.instagram2.entity.PostLikeEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.mapper.PostLikeMapper;
import com.hoangtuyen04work.instagram2.repository.PostLikeRepository;
import com.hoangtuyen04work.instagram2.repository.PostRepository;
import com.hoangtuyen04work.instagram2.service.interfaces.PostLikeSeviceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostLikeService implements PostLikeSeviceInterface {

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostLikeMapper postLikeMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public void addAndRemovePostLike(PostLikeRequest postLikeRequest) throws AppException{
        PostLikeEntity postLikeEntity = new PostLikeEntity();
        PostEntity postEntity = postService.findPostById(postLikeRequest.getPostId());
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userService.findByUserId(userId);
        postLikeEntity.setPost(postEntity);
        postLikeEntity.setUser(userEntity);
        if(isLiked(postLikeEntity)){
            System.out.println("this is liked");
            postLikeRepository.deleteByPostIdAndUserId(postEntity.getId(), userEntity.getId());
        }
        else{
            postLikeRepository.save(postLikeEntity);
        }
    }

    @Override
    public boolean isLiked(PostLikeEntity postLikeEntity) throws AppException {
        return postLikeRepository.existsByPostIdAndUserId(postLikeEntity.getPost().getId()
                , postLikeEntity.getUser().getId());
    }

    @Override
    public long countLikes(String postId) {
        return postLikeRepository.countLikedByPostId(postId);
    }



}
