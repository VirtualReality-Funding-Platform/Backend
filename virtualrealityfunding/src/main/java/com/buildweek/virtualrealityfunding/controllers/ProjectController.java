package com.buildweek.virtualrealityfunding.controllers;

import com.buildweek.virtualrealityfunding.models.Project;
import com.buildweek.virtualrealityfunding.models.User;
import com.buildweek.virtualrealityfunding.repository.UserRepository;
import com.buildweek.virtualrealityfunding.services.ProjectService;

import com.buildweek.virtualrealityfunding.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/projects")
public class ProjectController
{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


////    //login
////    @GetMapping(value = "/login")
////    @ResponseEntity<?>
//
//
//
//    @GetMapping(value = "/project/{id}",
//                produces = {"application/json"})
//    @ResponseBody
//    public ResponseEntity<?> CurrentProjectName(HttpServletRequest request, Authentication authentication,
//                                    @PathVariable long id)
//    {
//        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + "access");
//
//        projectService.findById(id);
//        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
//    }


//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/project",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewProject(HttpServletRequest request, Authentication authentication, @Valid
    @RequestBody(required = false)
            Project newproject) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");


        User user =  userRepository.findByUsername(authentication.getPrincipal().toString());

        user.getUserid();
        projectService.save(newproject, user.getUserid());

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newProjectURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{projectid}").buildAndExpand(newproject.getProjectid()).toUri();
        responseHeaders.setLocation(newProjectURI);

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    //update
    @PutMapping(value = "/post/{id}")
    public ResponseEntity<?> updateProject(HttpServletRequest request,
                                        @RequestBody
                                                Project updateProject,
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        projectService.update(updateProject, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity<?> deleteProject(HttpServletRequest request,
                                                    long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + "accessed");
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
