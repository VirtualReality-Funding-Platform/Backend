package com.buildweek.virtualrealityfunding.repository;

import com.buildweek.virtualrealityfunding.models.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//to do paging and sorting, changed CrudeRepository to PagingAndSortingRepository

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>
{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO created(projectid, entreprenureid) values (:projectid, :entrepreneurid)", nativeQuery = true)
    void insertProjects(Project projectid, long entrepreneurid);



}
