package com.buildweek.virtualrealityfunding.repository;

import com.buildweek.virtualrealityfunding.models.Investor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface InvestorRepository extends PagingAndSortingRepository<Investor, Long>
{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO created(projectid, investorid) values (:projectid, :investorid)", nativeQuery = true)
    void insertInvestors(long projectid, long investorid);

    /////trying to add a way to do find by user connecting it to the investor
    //User findByUsername(String username);
}
