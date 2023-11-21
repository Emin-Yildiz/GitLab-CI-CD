package com.example.pdf.repositories;

import com.example.pdf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbRepository extends JpaRepository<User,Integer> {
}
