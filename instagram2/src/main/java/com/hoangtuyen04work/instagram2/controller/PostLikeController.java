package com.hoangtuyen04work.instagram2.controller;


import com.hoangtuyen04work.instagram2.dto.request.PostLikeRequest;
import com.hoangtuyen04work.instagram2.dto.response.ApiResponse;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.service.impl.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class PostLikeController {

    @Autowired
    private PostLikeService postLikeService;

    @GetMapping("/count/{postId}")
    public Long countLikes(@PathVariable String postId) {
        return postLikeService.countLikes(postId);
    }

    @PutMapping
    public String likePost(@RequestBody PostLikeRequest postLikeRequest) throws AppException {
        postLikeService.addAndRemovePostLike(postLikeRequest);
        return "Sucessfully like post";
    }
}
