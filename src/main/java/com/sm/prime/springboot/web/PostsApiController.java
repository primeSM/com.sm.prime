package com.sm.prime.springboot.web;


import com.sm.prime.springboot.config.auth.LoginUser;
import com.sm.prime.springboot.config.auth.dto.SessionUser;
import com.sm.prime.springboot.domain.posts.PostsRepository;
import com.sm.prime.springboot.service.posts.PostsService;
import com.sm.prime.springboot.web.dto.PostsResponseDto;
import com.sm.prime.springboot.web.dto.PostsSaveRequestDto;
import com.sm.prime.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsRepository postsRepository;
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user) {
        String Author = user.getName();
        return postsService.save(requestDto, Author);
    }

    @PutMapping("/api/v1/posts/update")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/delete")
    public Long delete(@PathVariable Long id, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        if(!dto.getAuthor().equals(user.getName())){
            return id;
        }
        postsService.delete(id);
        return id;
    }
}
