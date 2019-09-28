package com.buildweek.virtualrealityfunding.services;

import com.buildweek.virtualrealityfunding.models.Investor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvestorService
{
    //search all investors
    List<Investor> findAll(Pageable pageable);

    //save investor
    //void  save(long investorid, long entrepreneurid);
    Investor save(Investor investor);

    //delete investor
    void delete(long id);

    //update investor
    Investor update(Investor investor, long id);

    //find by id
    Investor findById(long id);
}
