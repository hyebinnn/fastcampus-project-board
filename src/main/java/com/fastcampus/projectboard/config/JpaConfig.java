package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    // Auditing : 데이터베이스의 특정 엔터티에 대한 생성 및 수정과 같은 변경 이벤트를 기록하는 메커니즘
    // Auditing 할 때마다 사람 이름은 설정한 Optional 값으로 넣게 됨
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("hyebin");          // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정 필요
    }

}
