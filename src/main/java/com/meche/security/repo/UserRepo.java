package com.meche.security.repo;

import com.meche.security.model.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author sidof
 * @Since 10/2/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Repository
public interface UserRepo extends JpaRepository<Appuser, Long> {
    Appuser findByName(String name);

    Optional<Appuser> findByEmail(String email);
}
