package com.jojoldubook.practice.springboot.config.auth.dto;

import com.jojoldubook.practice.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        if (user != null) {
            this.name = user.getName();
            this.email = user.getEmail();
            this.picture = user.getPicture();
        }
    }
}
