package com.medinote.backend.domain.medinote.repository;

import com.medinote.backend.domain.medinote.entity.Medinote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedinoteRepository extends JpaRepository<Medinote, Long> {
}
