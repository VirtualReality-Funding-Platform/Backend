package com.buildweek.virtualrealityfunding.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investors")
public class Investor // extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //primary key
    private long investorid;

    @OneToMany(cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JoinColumn(name = "investorid")
    @JsonIgnoreProperties("investors")
    //ties investor to investor projects
    private List<Project> projects = new ArrayList<>();

    @ManyToMany(mappedBy= "investors")
    @JsonIgnoreProperties("investors")
    private List<User> investuser = new ArrayList<>();

    private String name;
    private String investamt;
    private String location;
    private double funded;
    private String email;


    public long getInvestorid()
    {
        return investorid;
    }
//
//    public List<Project> getProjects()
//    {
//        return projects;
//    }
//
//    public void setProjects(List<Project> projects)
//    {
//        this.projects = projects;
//    }

    public Investor()
    {
    }

    public Investor(double funded)
    {
        this.funded = funded;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getInvestamt()
    {
        return investamt;
    }

    public void setInvestamt(String investamt)
    {
        this.investamt = investamt;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public double getFunded()
    {
        return funded;
    }

    public void setFunded(double funded)
    {
        this.funded = funded;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<User> getInvestuser()
    {
        return investuser;
    }

    public void setInvestuser(List<User> investuser)
    {
        this.investuser = investuser;
    }
}
