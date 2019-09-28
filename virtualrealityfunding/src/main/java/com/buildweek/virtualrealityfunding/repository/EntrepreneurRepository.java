package com.buildweek.virtualrealityfunding.repository;

import com.buildweek.virtualrealityfunding.models.Entrepreneur;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EntrepreneurRepository extends PagingAndSortingRepository<Entrepreneur, Long>
{

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO created(projectid, entreprenureid) values (:projectid, :entrepreneurid)", nativeQuery = true)
    void insertEntrepreneurs(long projectid, long entrepreneurid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO created(projectid, entreprenureid) values (:projectid, :entrepreneurid)", nativeQuery = true)
    void deleteInvestorProjectsbyEntrepreneurId(long entrepreneurid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO created(projectid, entreprenureid) values (:projectid, :entrepreneurid)", nativeQuery = true)
    void saveEntrepreneurProject(long entrepreneurid);


//    @Query(value = "INSERT INTO created(projectid, entreprenureid) values (:projectid, :entrepreneurid)", nativeQuery = true)
//    void insertIntoInvestorProjects(long entrepreneurid, long fundid);
}