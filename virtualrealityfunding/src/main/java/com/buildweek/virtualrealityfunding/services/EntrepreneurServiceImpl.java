package com.buildweek.virtualrealityfunding.services;

import com.buildweek.virtualrealityfunding.exceptions.ResourceNotFoundException;
import com.buildweek.virtualrealityfunding.models.Entrepreneur;
import com.buildweek.virtualrealityfunding.models.InvestorProjects;
import com.buildweek.virtualrealityfunding.models.Project;
import com.buildweek.virtualrealityfunding.repository.EntrepreneurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value ="EntrepreneurService")
public class EntrepreneurServiceImpl implements EntrepreneurService
{
    @Autowired
    private EntrepreneurRepository entrepo;

    @Override
    public List<Entrepreneur> findAll(Pageable pageable)
    {
        List<Entrepreneur> theEntrepreneurs = new ArrayList();
        entrepo.findAll().iterator().forEachRemaining(theEntrepreneurs::add);
        return theEntrepreneurs;
    }

    @Override
    public void save(long projectid, long entrepreneurid)
    {

    }

    @Override
    public Entrepreneur findById(long id)
    {
//        Entrepreneur e = new Entrepreneur();

        return entrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur id " + id + " not found!"));

    }

    @Override
    public void saveEntrepreneurProject(long entrepreneurid, long projectid)
    {

    }

    @Override
    public Entrepreneur save(Entrepreneur entrepreneur)
    {
        return entrepreneur;
    }


    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (entrepo.findById(id).isPresent())
        {
            entrepo.deleteById(id);
        }else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }
    @Transactional
    public Entrepreneur save (Entrepreneur entrepreneurid, long projectid)
    {
        Entrepreneur newEntrepreneur = new Entrepreneur();

        newEntrepreneur.setName(entrepreneurid.getName());
        newEntrepreneur.setLocation(entrepreneurid.getLocation());
        newEntrepreneur.setRequestamt(entrepreneurid.getRequestamt());
        newEntrepreneur.setEmail(entrepreneurid.getEmail());
        newEntrepreneur.setPhone(entrepreneurid.getPhone());
        newEntrepreneur.setBio(entrepreneurid.getBio());


//        ArrayList<InvestorProjects> newFunds = new ArrayList<>();
//        Entrepreneur entrepreneur = null;
//        for (InvestorProjects ip : entrepreneur.getInvestorProjects())
//        {
//            newFunds.add(new InvestorProjects(newEntrepreneur, ip.getFund()));
//        }
//        newEntrepreneur.setInvestorProjects(newFunds);
//
//        for (Project p : entrepreneur.getProjects())
//        {
//            newEntrepreneur.getProjects().add(new Project(p.getProjectname(), p.getFundedamt(), p.getProjecttype(), newEntrepreneur));
//        }


        return entrepo.save(newEntrepreneur);
//      entrepo.insertEntrepreneurs(entrepreneurid, projectid);
    }


    @Transactional
    @Override
    public Entrepreneur update(Entrepreneur entrepreneur, long id)
    {
        Entrepreneur currentEntrepreneur = entrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException((Long.toString(id))));

        if(entrepreneur.getName() !=null)
        {
            currentEntrepreneur.setName(entrepreneur.getName());

        }
        if(entrepreneur.getLocation() !=null)
        {
            currentEntrepreneur.setLocation(entrepreneur.getLocation());
        }
        if (entrepreneur.getEmail() !=null)
        {
            currentEntrepreneur.setEmail(entrepreneur.getEmail());
        }
        if(entrepreneur.getPhone() !=0)
        {
            currentEntrepreneur.setPhone(entrepreneur.getPhone());
        }
        if(entrepreneur.getBio() !=null)
        {
            currentEntrepreneur.setBio((entrepreneur.getBio()));
        }

//        if (entrepreneur.getProjects().size() > 0)
//        {
//            for (Project p : entrepreneur.getProjects())
//            {
//                currentEntrepreneur.getProjects().add(new Project(p.getProjectname(), p.getFundedamt()));
//            }
//        }

        return entrepo.save(currentEntrepreneur);
    }
}
