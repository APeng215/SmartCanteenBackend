package com.apeng.smartcanteenbackend.entity.sub;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`authorities`")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;
    private String authority;
    private String username;

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

}
