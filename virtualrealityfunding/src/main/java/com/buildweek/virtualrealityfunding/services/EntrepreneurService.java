package com.buildweek.virtualrealityfunding.services;

import com.buildweek.virtualrealityfunding.models.Entrepreneur;
import com.buildweek.virtualrealityfunding.models.Investor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntrepreneurService
{
    //search all projects
    List<Entrepreneur> findAll(Pageable pageable);

    //save project
    void  save(long projectid, long entrepreneurid);

    //delete project
    void delete(long id);

    //update project
    Entrepreneur update(Entrepreneur project, long id);

    //find by id
    Entrepreneur findById(long id);

    void saveEntrepreneurProject(long entrepreneurid, long projectid);

    Entrepreneur save(Entrepreneur entrepreneur);
}
