package com.xpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpo.demo.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
