package com.example.backoffice.security.user;

import com.example.backoffice.model.ValidEnum.Auth;
import com.example.backoffice.model.entity.Member;
import jakarta.persistence.CollectionTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MemberDetailCustom implements UserDetails {
    private final Member member;

    public MemberDetailCustom(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String memberAuth = member.getAuth();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(memberAuth);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public Long getId() {
        return member.getId();
    }
    public String getEmail() {
        return member.getEmail();
    }
    public String getPart() {
        return member.getPart();
    }
    public String getAuth() {
        return member.getAuth();
    }
}
