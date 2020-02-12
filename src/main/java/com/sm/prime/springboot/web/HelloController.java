package com.sm.prime.springboot.web;

import com.sm.prime.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller를 JSON으로 반환하는 Controller로 Making
public class HelloController {
    @GetMapping("/hello") // HTTP method인 Get의 요청을 받을 수 있는 API 생성
    public String hello() {
        return "hello";
    }
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }

}

