package com.sm.prime.springboot.service.posts;

import com.sm.prime.springboot.domain.posts.Posts;
import com.sm.prime.springboot.domain.posts.PostsRepository;
import com.sm.prime.springboot.web.dto.PostsListResponseDto;
import com.sm.prime.springboot.web.dto.PostsResponseDto;
import com.sm.prime.springboot.web.dto.PostsSaveRequestDto;
import com.sm.prime.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto, String Author) {
        return postsRepository.save(requestDto.toEntity(Author)).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글 없셈. id=" + id));
        postsRepository.delete(posts);
    }
    @Transactional(readOnly = true)
    public Page<Posts> findAll(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

}
