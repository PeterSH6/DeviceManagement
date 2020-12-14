package com.example.dao;

import com.example.entity.Broken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokenRepository extends JpaRepository<Broken,Integer> {
}
