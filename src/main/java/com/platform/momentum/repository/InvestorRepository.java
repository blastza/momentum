package com.platform.momentum.repository;

import com.platform.momentum.entity.UserEntity;
import com.platform.momentum.model.domain.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {
    @Query(value = "SELECT * FROM investor WHERE email = :email", nativeQuery = true)
    Investor findByEmail(String email);
}
