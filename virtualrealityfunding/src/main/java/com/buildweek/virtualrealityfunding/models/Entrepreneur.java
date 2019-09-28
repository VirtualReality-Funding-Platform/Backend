package com.buildweek.virtualrealityfunding.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@ApiModel(value = "Entrepreneur", description ="The Entrepreneur Entity")
@Entity
@Table(name = "entrepreneurs")
public class Entrepreneur extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long entrepreneurid;

//    @OneToMany(mappedBy = "entrepreneur", cascade= CascadeType.ALL,
//               orphanRemoval = true)
//    @JsonIgnoreProperties("entrepreneur")
//    private List<Project>projects= new ArrayList<>();


    private String name;
    //first name of the entrepreneur

    private double requestamt;

    private String location;

    private String email;

    private double phone;

    private String bio;

    public Entrepreneur()
    {
    }

    public Entrepreneur(List<Investor> investor, String name, double requestamt, String location, String email)
    {

        this.name = name;
        this.requestamt = requestamt;
        this.location = location;
        this.email = email;
        this.bio = bio;
    }

    public long getEntrepreneurid()
    {
        return entrepreneurid;
    }

    public void setEntrepreneurid(long entrepreneurid)
    {
        this.entrepreneurid = entrepreneurid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public double getRequestamt()
    {
        return requestamt;
    }

    public void setRequestamt(double requestamt)
    {
        this.requestamt = requestamt;
    }
//
//    public List<Project> getProjects()
//    {
//        return projects;
//    }
//
//    public void setProjects(List<Project>projects)
//    {
//        this.projects = projects;
//    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public double getPhone()
    {
        return phone;
    }

    public void setPhone(double phone)
    {
        this.phone = phone;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }
}
