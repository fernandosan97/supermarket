package com.fernando.sinch.supermarket.repository;

import com.fernando.sinch.supermarket.models.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {
    Optional<Detail> findById(Integer id);
}
