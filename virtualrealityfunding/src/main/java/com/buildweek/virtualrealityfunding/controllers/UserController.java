package com.buildweek.virtualrealityfunding.controllers;


import com.buildweek.virtualrealityfunding.models.Entrepreneur;
import com.buildweek.virtualrealityfunding.models.Investor;
import com.buildweek.virtualrealityfunding.models.User;
import com.buildweek.virtualrealityfunding.services.EntrepreneurService;
import com.buildweek.virtualrealityfunding.services.InvestorService;
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
import java.util.List;


///register
/// login in
///complete information( choosing investor or entrepreneur)
///dashboard


//ask what they have built out
//
@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private InvestorService investorService;

    @Autowired
    private EntrepreneurService entrepreneurService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserById(HttpServletRequest request,
                                         @PathVariable
                                                 Long userId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserByName(HttpServletRequest request,
                                           @PathVariable
                                                   String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findByName(userName);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @GetMapping(value = "/getusername",
                produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request, Authentication authentication)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.update(updateUser, id, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(HttpServletRequest request,
                                                 @PathVariable
                                                         long userid,
                                                 @PathVariable
                                                         long roleid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.deleteUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(HttpServletRequest request,
                                               @PathVariable
                                                       long userid,
                                               @PathVariable
                                                       long roleid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.addUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//INVESTOR CREATE ENDPOINTS
    ////trying to create new endpoints associated with the new investor and
    @PostMapping(value = "/createInvestor",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> createInvestor(
            @RequestBody
                    Investor investor) throws URISyntaxException
    {
        Investor newInvestor = investorService.save(investor);
        newInvestor.setInvestamt(investor.getInvestamt());
        newInvestor.setName(investor.getName());
        newInvestor.setEmail(investor.getEmail());
        newInvestor.setInvestamt(investor.getInvestamt());
        newInvestor.setLocation(investor.getLocation());

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newInvURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{investorid}").buildAndExpand(newInvestor.getInvestorid()).toUri();
        responseHeaders.setLocation(newInvURI);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

//ENTREPRENEUR CREATE ENDPOOINT
    @PostMapping(value = "/createEntrepreneur",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> createEntrepreneur(
            @RequestBody
                    Entrepreneur entrepreneur) throws URISyntaxException
    {
        Entrepreneur newEntrepreneur = new Entrepreneur();
        newEntrepreneur.setBio(entrepreneur.getBio());
        newEntrepreneur.setRequestamt(entrepreneur.getRequestamt());
        newEntrepreneur.setPhone(entrepreneur.getPhone());
        newEntrepreneur.setName(entrepreneur.getName());
        newEntrepreneur.setEmail(entrepreneur.getEmail());
        newEntrepreneur.setLocation(entrepreneur.getLocation());
        newEntrepreneur.setEntrepreneurid(entrepreneur.getEntrepreneurid());

        entrepreneurService.save(newEntrepreneur);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newInvURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{investorid}").buildAndExpand(entrepreneur.getEntrepreneurid()).toUri();
        responseHeaders.setLocation(newInvURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


}