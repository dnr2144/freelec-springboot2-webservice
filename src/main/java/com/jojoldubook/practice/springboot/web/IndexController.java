package com.jojoldubook.practice.springboot.web;

import com.jojoldubook.practice.springboot.service.PostsService;
import com.jojoldubook.practice.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    // 스프링에서는 Bean을 주입받는 방식이 크게 Autowired, setter, 생성자 3개가 있는데
    // 이 중 가장 권장받는 방법은 생성자를 통해서 Bean을 주입받는 방식이다.
    // IndexController는 RequiredArgsConstructor로 final 필드의 생성자를 생성하고 Bean을 주입받는다.
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        // Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        // 여기서는 posts.Service.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
