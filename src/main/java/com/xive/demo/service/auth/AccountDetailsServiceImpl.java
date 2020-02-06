package com.xive.demo.service.auth;

import com.xive.demo.dto.AccountPostRequestDTO;
import com.xive.demo.entity.Authority;
import com.xive.demo.entity.AuthorityType;
import com.xive.demo.entity.User;
import com.xive.demo.model.UserDetailsImpl;
import com.xive.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Collections;



@Service
@RequiredArgsConstructor
@Slf4j
public class AccountDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Lazy
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void updateTableSequences() {
        userRepository.updateAccountIdSequence();
        userRepository.updateAuthorityIdSequence();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Username doesn't exist");
        return  new UserDetailsImpl(user);
    }

    @Transactional(rollbackOn=Exception.class)
    public User saveAccount(AccountPostRequestDTO dto) {
        log.info("-------------------------------------------------------");
        log.info(dto.toString());
        log.info("-------------------------------------------------------");
        if(userRepository.findByUsername(dto.getLogin()) != null)
            throw new ValidationException("User with such login already exists!");

        User user = new User();
        user.setUsername(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Authority authority = new Authority();
        authority.setName(AuthorityType.ROLE_USER);
        user.setAuthorities(Collections.singleton(authority));
        user = userRepository.save(user);
        return user;
    }
}
