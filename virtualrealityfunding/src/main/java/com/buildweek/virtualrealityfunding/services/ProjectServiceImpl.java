package com.buildweek.virtualrealityfunding.services;

import com.buildweek.virtualrealityfunding.models.Project;
import com.buildweek.virtualrealityfunding.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value ="projectService")
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectRepository projrepo;



    @Override
    public List<Project> findAll(Pageable pageable)
    {
        List<Project> myProjects = new ArrayList();
        projrepo.findAll().iterator().forEachRemaining(myProjects::add);
        return myProjects;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (projrepo.findById(id).isPresent())
        {
            projrepo.deleteById(id);
        }else
        {
            throw new EntityNotFoundException((Long.toString(id)));

        }
    }

    @Transactional
    @Override
    public void save(Project project, long entrepreneurid)

       {
          projrepo.insertProjects(project, entrepreneurid);
       }




    @Transactional
    @Override
    public Project update(Project project, boolean id)
    {


        Project newProject = projrepo.orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

            if (project.getProjectname() !=null)
            {
                newProject.setProjectname(project.getProjectname());
            }

            if (project.getDescription() != null)
            {
                newProject.setDescription(project.getDescription());
            }

            if (project.getProjectname() != null)
            {
                newProject.setProjecttype(project.getProjecttype());
            }

            if (project.getProjectimage() != null)
            {
                newProject.setProjectimage(project.getProjectimage());
            }

        return projrepo.save(newProject);
    }

    @Override
    public Project findById(long id) throws EntityNotFoundException
    {
        return projrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
}
