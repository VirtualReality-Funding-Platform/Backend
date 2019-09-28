//package com.buildweek.virtualrealityfunding.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@Table(name = "entrepreneurprojects")
//public class EntrepreneurProjects extends Auditable implements Serializable
//{
//
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "entrepreneurid")
//    @JsonIgnoreProperties({"entrepreneurProjects", "hibernateLazyInitializer"})
//    private Entrepreneur entrepreneur;
//
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "projectid")
//    @JsonIgnoreProperties({"entrepreneurProjects", "hibernateLazyInitializer"})
//    private Project project;
//
//
//    public EntrepreneurProjects(Entrepreneur entrepreneur, Project project)
//    {
//        this.entrepreneur = entrepreneur;
//        this.project = project;
//    }
//
//    public EntrepreneurProjects()
//    {
//    }
//
//    public Entrepreneur getEntrepreneur()
//    {
//        return entrepreneur;
//    }
//
//    public void setEntrepreneur(Entrepreneur entrepreneur)
//    {
//        this.entrepreneur = entrepreneur;
//    }
//
//
//    public Project getProject()
//    {
//        return project;
//    }
//
//    public void setProject(Project project)
//    {
//        this.project = project;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o)
//            return true;
//        if (!(o instanceof EntrepreneurProjects))
//            return false;
//        EntrepreneurProjects that = (EntrepreneurProjects) o;
//        return getEntrepreneur().equals(that.getEntrepreneur()) && getProject().equals(that.getProject());
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(getEntrepreneur(), getProject());
//    }
//}
//
