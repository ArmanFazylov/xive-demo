package com.xive.demo.repository;

import com.xive.demo.entity.User;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends BaseRepository<User> {
    User findByUsername(String username);

    @Query(value = "SELECT setval('account_id_seq', (SELECT max(id) FROM account));", nativeQuery = true)
    void updateAccountIdSequence();

    @Query(value = "SELECT setval('authority_id_seq', (SELECT max(id) FROM authority));", nativeQuery = true)
    void updateAuthorityIdSequence();
}
