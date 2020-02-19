package com.sm.prime.springboot.web;


import com.sm.prime.springboot.config.auth.LoginUser;
import com.sm.prime.springboot.config.auth.dto.SessionUser;
import com.sm.prime.springboot.service.posts.PostsService;
import com.sm.prime.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 가능
        model.addAttribute("posts", postsService.findAllDesc());
        if( user != null ) {
            model.addAttribute("Name", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/read/{id}")
    public String postsRead(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if( user == null ) {
            return "index";
        }
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-read";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if( user != null ) {
            model.addAttribute("Author", user.getName());
        } else {
            return "index";
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if( user != null ) {
            model.addAttribute("Author", user.getName());
        } else {
            return "index";
        }
        PostsResponseDto dto = postsService.findById(id);
        if(!dto.getAuthor().equals(user.getName())){
            return "posts-read";
        } else {
            model.addAttribute("post", dto);
            return "posts-update";
        }
    }

}
