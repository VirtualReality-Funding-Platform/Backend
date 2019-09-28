package com.buildweek.virtualrealityfunding.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "investorprojects")
public class InvestorProjects extends Auditable implements Serializable
{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investorid")
    @JsonIgnoreProperties({"investorProjects", "hibernateLazyInitializer"})
    private Investor investor;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectid")
    @JsonIgnoreProperties({"investorProjects", "hibernateLazyInitializer"})
    private Project project;

    private double fund;

    public InvestorProjects()
    {
    }

    public InvestorProjects(Investor investor, Project project)
    {
        this.investor = investor;
//        this.project = project;

    }

    public InvestorProjects (Investor investor, Project project, double fund)
    {
        this(investor, project);
        this.fund = fund;
    }

    public Investor getInvestor()
    {
        return investor;
    }

    public void setInvestor(Investor investor)
    {
        this.investor = investor;
    }

    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public double getFund()
    {
        return fund;
    }

    public void setFund(double fund)
    {
        this.fund = fund;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof InvestorProjects))
            return false;
        InvestorProjects that = (InvestorProjects) o;
        return getInvestor().equals(that.getInvestor()) && getProject().equals(that.getProject());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getInvestor(), getProject());
    }
}
