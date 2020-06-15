package com.vrechbot.repository;

import com.vrechbot.domain.PriceAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceActionRepository extends JpaRepository<PriceAction, LocalDateTime> {
    @Query(value = "SELECT pa from PriceAction pa where pa.id.data > :data AND pa.ordem = 'SELL' AND pa.par = coalesce(:par, pa.par) AND pa.percentage >= 90")
    List<PriceAction> findSellActionsByDataAndPair(LocalDateTime data, String par);

    @Query(value = "SELECT pa from PriceAction pa where pa.id.data > :data AND pa.ordem = 'BUY' AND pa.par = coalesce(:par, pa.par) AND pa.percentage >= 90")
    List<PriceAction> findBuyActionsByDataAndPair(LocalDateTime data, String par);
}
