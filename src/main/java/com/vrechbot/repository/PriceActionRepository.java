package com.vrechbot.repository;

import com.vrechbot.domain.PriceAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceActionRepository extends JpaRepository<PriceAction, LocalDateTime> {
    @Query(value = "from PriceAction pa where pa.id.data > :data")
    List<PriceAction> findAllByIdDataGreaterThan(LocalDateTime data);
}
