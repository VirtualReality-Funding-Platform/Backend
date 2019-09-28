package com.buildweek.virtualrealityfunding.controllers;


import com.buildweek.virtualrealityfunding.models.Entrepreneur;
import com.buildweek.virtualrealityfunding.models.Investor;
import com.buildweek.virtualrealityfunding.models.Project;
import com.buildweek.virtualrealityfunding.services.EntrepreneurService;
import com.buildweek.virtualrealityfunding.services.ProjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrepreneurs")
public class EntrepreneurController
{

@Autowired
private ProjectService projectService;

@Autowired
private EntrepreneurService entrepreneurService;

    @ApiOperation(value ="returns all Projects", responseContainer = "Project List")

    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})

    //create a list of projects
    @GetMapping(value = "/projects",
                produces ={"application/json"})

    public ResponseEntity<?> listRoles(
                @PageableDefault(page = 0,
                                 size = 5)
                        Pageable pageable)
    {
        List<Project> allProjects=projectService.findAll(pageable);
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @ApiOperation(value ="returns all Projects", responseContainer = "Project List")

    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})

//ENTREPRENEURS CRUD
    //create a list of projects
    @GetMapping(value = "/entrepreneurs",
                produces ={"application/json"})

    public ResponseEntity<?> listAuthors(
            @PageableDefault(page = 0,
                             size = 5)
                    Pageable pageable)
    {
        List<Entrepreneur> allAuthors= entrepreneurService.findAll(pageable);
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }


    //PROJECT CRUD
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable
                                                    long id)
    {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    //update projects
//    @PutMapping(value = "/project/{projectid}")
//    public ResponseEntity<?> updateProject(
//            @RequestBody
//                    Project updateProject,
//            @PathVariable
//                    long projectid)
//    {
//        projectService.update(updateProject, projectid);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    //new projects
    @PostMapping(value= "/project/{projectid}/entrepreneur/{entrepreneur}")
    public ResponseEntity<?> add(@PathVariable long projectid, long entrepreneurid)
    {
        entrepreneurService.saveEntrepreneurProject(projectid, entrepreneurid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



//    post, get, put, delete
//    project;
//    entrepreneurs;
//    investors;



//INVESTOR CRUD
    //
}
