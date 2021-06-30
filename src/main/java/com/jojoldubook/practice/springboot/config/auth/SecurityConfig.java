package com.jojoldubook.practice.springboot.config.auth;

import com.jojoldubook.practice.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                .authorizeRequests()// url별 권한 관리를 설정하는 옵션 시작점. authorizeRequests가 선언되어야만 antMachers 옵션 사용 가능
                .antMatchers("/", "/css/**",
                        "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // antMachers: 권한 곤리 대상을 지정하는 옵션
                .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL들을 나타냄(나머지 URL들은 로그인한 사용자들에게 모두 허용)
                .and()
                .logout().logoutSuccessUrl("/")// 로그아웃 기능에 대한 여러 설정 진입점. 로그아웃 성공 시 /주소로 이동
                .and()
                .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정 집입점
                .userInfoEndpoint() // OAuth2 로그인 성공 이후, 사용자 정보를 가져올 때 설정 담당
                .userService(customOAuth2UserService); // 소셜로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록
    }
}


