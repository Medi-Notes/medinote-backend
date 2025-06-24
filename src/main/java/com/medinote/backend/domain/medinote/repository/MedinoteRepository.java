package com.medinote.backend.domain.medinote.repository;

import com.medinote.backend.domain.medinote.entity.Medinote;
import com.medinote.backend.domain.medinote.entity.MedinoteState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedinoteRepository extends JpaRepository<Medinote, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Medinote m SET m.medinoteState = :state WHERE m.medinoteId = :id")
    int updateMedinoteStateById(Long id, MedinoteState state);
}
