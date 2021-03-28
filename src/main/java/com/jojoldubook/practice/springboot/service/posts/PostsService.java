package com.jojoldubook.practice.springboot.service.posts;

import com.jojoldubook.practice.springboot.domain.posts.Posts;
import com.jojoldubook.practice.springboot.domain.posts.PostsRepository;
import com.jojoldubook.practice.springboot.web.dto.PostsListResponseDto;
import com.jojoldubook.practice.springboot.web.dto.PostsResponseDto;
import com.jojoldubook.practice.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldubook.practice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository; // 생성자로 Bean 주입

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    // Transactional에 readOnly를 추가하면 트랜젝션 범위는 유지하되, 조회 기능만 남겨두어
    // 조회 속도가 개선되기 때문에 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하면 좋다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // map(posts -> new PostsListResponseDto(posts)
                .collect(Collectors.toList());
    }
}
