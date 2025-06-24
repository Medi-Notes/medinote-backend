package com.medinote.backend.domain.medinote.repository;

import com.medinote.backend.domain.medinote.entity.Medinote;
import com.medinote.backend.domain.medinote.entity.MedinoteState;
import com.medinote.backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedinoteRepository extends JpaRepository<Medinote, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Medinote m SET m.medinoteState = :state WHERE m.medinoteId = :id")
    int updateMedinoteStateById(Long id, MedinoteState state);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Medinote m SET m.sttText = :sttText WHERE m.medinoteId = :id")
    int updateSttTextById(Long id, String sttText);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Medinote m SET m.medinoteText = :medinoteText WHERE m.medinoteId = :id")
    int updateMedinoteTextById(Long id, String medinoteText);

    List<Medinote> findAllByMemberId(Member memberId);
}
