package com.jojoldubook.practice.springboot.service;

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

        /*
        * stream -> map -> collect 사용 예시
        * 자바8부터 Stream 을 사용 할 수 있다.
        * 기존에 자바 컬렉션이나 배열의 원소를 가공할떄, for문, foreach 등으로 원소 하나씩 골라내여 가공을 하였다면,
        * Stream 을 이용하여 람다함수형식으로 간결하고 깔끔하게 요소들의 처리가 가능.
        *
        * ArrayList<string> list = new ArrayList<>(Arrays.asList("Apple","Banana","Melon","Grape","Strawberry"));
        * System.out.println(list); //[Apple, Banana, Melon, Grape, Strawberry]
        * list.stream().map(s->s.toUpperCase());
        * list.stream().map(String::toUpperCase);
        *
        * System.out.println(list.stream().map(s->s.toUpperCase()).collect(Collectors.joining(" "))); //APPLE BANANA MELON GRAPE STRAWBERRY]
        * System.out.println(list.stream().map(s->s.toUpperCase()).collect(Collectors.toList())); //[APPLE, BANANA, MELON, GRAPE, STRAWBERRY]
        * System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.toList())); //[APPLE, BANANA, MELON, GRAPE, STRAWBERRY]
        */
    }
}
