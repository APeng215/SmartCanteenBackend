package com.apeng.smartcanteenbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
public class Authorities implements GrantedAuthority {

    @Id
    private String authority;
    private String username;

    public Authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

}
