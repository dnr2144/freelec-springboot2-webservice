package com.jojoldubook.practice.springboot.config.auth;

import com.jojoldubook.practice.springboot.config.auth.dto.OAuthAttributes;
import com.jojoldubook.practice.springboot.config.auth.dto.SessionUser;
import com.jojoldubook.practice.springboot.domain.user.User;
import com.jojoldubook.practice.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
// 소셜 로그인 이휴 가져온 사용자 정보(email, name, picture 등)들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능 지원
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /*
        * registrationId
        * 현재 로그인 진행 중인 서비스를 구분하는 코드
        * 네이버 로그인인지, 구글 로그인인지, 카카오 로그인인지 구분하기 위해 사용
        *
         */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /*
        * userNameAttributeName
        * OAuth2 로그인 진행 키가 되는 필드값을 이야기한다. Primary Key와 같은 의미임
        * 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않는다. 구글 기본 코드는 "sub"
        * 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용
         */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        /*
        * OAuthAttributes
        * OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        * 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용
        */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);

        System.out.println("===========================================================");
        System.out.println("로그2: " + user.getEmail());

        /*
        * SessionUser
        * 세션에 사용자 정보를 저장하기 위한 DTO Class
         */
        httpSession.setAttribute("user", new SessionUser(user));


        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),
                        attributes.getPicture()))
                .orElse(attributes.toEntity());

        System.out.println("==========================================================");
        System.out.println("로그1: attributes.getEmail() = " + attributes.getEmail());
        System.out.println("로그1: attributes.getName() = " + attributes.getName());
        System.out.println("로그1: attributes.getPicture() = " + attributes.getPicture());
        System.out.println("로그1: attributes.toEntity().toString() = " + attributes.toEntity().toString());
        System.out.println("로그1: user.getEmail() = " + user.getEmail());
        System.out.println("로그1: user.getName() = " + user.getName());
        System.out.println("로그1: user.getPicture() = " + user.getPicture());
        System.out.println("로그1: user.getRoleKey() = " + user.getRoleKey());

        return userRepository.save(user);
    }
}