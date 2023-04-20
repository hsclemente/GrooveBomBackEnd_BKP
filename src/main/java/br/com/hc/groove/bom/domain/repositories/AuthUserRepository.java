package br.com.hc.groove.bom.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hc.groove.bom.domain.models.entities.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{

    UserDetails findByUsername(String username);
    
}
