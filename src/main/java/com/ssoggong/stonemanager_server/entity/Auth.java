package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {
    @Id @GeneratedValue
    @Column(name = "auth_idx")
    private Long idx;

    private String email;
    private String code;
    @Builder
    public Auth(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
