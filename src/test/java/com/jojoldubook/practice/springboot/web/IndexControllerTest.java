//package com.jojoldubook.practice.springboot.web;
//
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
>>>>>>> ffcd4623d9ea90fc6c5a1da5572c03f4cb30e4b7
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = RANDOM_PORT)
//public class IndexControllerTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void 메인페이지_로딩() {
//        //when
//        String body = this.restTemplate.getForObject("/", String.class);
//
//        //then
//        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
//    }
//}
