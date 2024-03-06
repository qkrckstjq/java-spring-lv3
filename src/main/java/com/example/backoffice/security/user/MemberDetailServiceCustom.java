package com.example.backoffice.security.user;

import com.example.backoffice.model.entity.Member;
import com.example.backoffice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "구체화된 UserDetailService")
public class MemberDetailServiceCustom implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberDetailServiceCustom(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("이메일에 해당하는 데이터 뽑아옴");
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("해당 이메일이 존재하지 않습니다.")
        );
        log.info("요청한 정의 이메일, 비밀번호");
        log.info(member.getEmail());
        log.info(member.getPassword());
        return new MemberDetailCustom(member);
    }
}
