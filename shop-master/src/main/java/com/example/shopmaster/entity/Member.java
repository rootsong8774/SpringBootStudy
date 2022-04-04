package com.example.shopmaster.entity;

import com.example.shopmaster.MemberFormDTO;
import com.example.shopmaster.constant.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDTO memberFormDTO,
        PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode(memberFormDTO.getPassword());
        return Member.builder()
            .name(memberFormDTO.getName())
            .email(memberFormDTO.getEmail())
            .address(memberFormDTO.getAddress())
            .password(password)
            .role(Role.ADMIN)
            .build();
    }


}
