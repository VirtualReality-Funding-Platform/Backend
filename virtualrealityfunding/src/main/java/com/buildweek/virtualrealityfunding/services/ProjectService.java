package com.buildweek.virtualrealityfunding.services;

import com.buildweek.virtualrealityfunding.models.Project;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService
{
    //search all projects
    List<Project> findAll(Pageable pageale);

    //save project
    void  save(Project project, long entrepreneurid);

    //delete project
    void delete(long id);

    //update project
    Project update(Project project, boolean id);

    //find by id
    Project findById(Project project);

    Project findByProjectName(Project project, String projectname);
}

