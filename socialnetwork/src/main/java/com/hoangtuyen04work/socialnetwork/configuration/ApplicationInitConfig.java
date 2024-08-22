package com.hoangtuyen04work.socialnetwork.configuration;

import com.hoangtuyen04work.socialnetwork.constant.Role;
import com.hoangtuyen04work.socialnetwork.entity.RoleEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.repository.RoleRepository;
import com.hoangtuyen04work.socialnetwork.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByUserId(ADMIN_USER_NAME) == null) {
                roleRepository.save(RoleEntity.builder()
                        .role(Role.ADMIN)
                        .build());
                RoleEntity adminRole = roleRepository.save(RoleEntity.builder()
                        .role(Role.ADMIN)
                        .build());
                var roles = new HashSet<RoleEntity>();
                roles.add(adminRole);
                UserEntity user = UserEntity.builder()
                        .userName(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();
                userRepository.save(user);
            }
        };
    }
}