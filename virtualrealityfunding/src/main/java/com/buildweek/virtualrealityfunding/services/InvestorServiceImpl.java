package com.buildweek.virtualrealityfunding.services;

import com.buildweek.virtualrealityfunding.models.Investor;
import com.buildweek.virtualrealityfunding.models.User;

import com.buildweek.virtualrealityfunding.repository.InvestorRepository;
import com.buildweek.virtualrealityfunding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value ="investorService")
public class InvestorServiceImpl implements InvestorService
{
    @Autowired
    private InvestorRepository investrepo;

    @Autowired
    private UserRepository userrepos;

    @Override
    public List<Investor> findAll(Pageable pageable)
    {
        List<Investor> myInvestors = new ArrayList();
        investrepo.findAll().iterator().forEachRemaining(myInvestors::add);
        return myInvestors;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (investrepo.findById(id).isPresent())
        {
            investrepo.deleteById(id);
        }else
        {
            throw new EntityNotFoundException((Long.toString(id)));

        }
    }


    @Transactional
    @Override
//    public void save(long investorid, long entrepreneurid)
    public Investor save(Investor investor)
    {
//        investrepo.insertInvestors(investorid, entrepreneurid);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        Investor newInvestor = new Investor();
        newInvestor.setEmail(investor.getEmail());
        newInvestor.setName(investor.getName());
        newInvestor.setLocation(investor.getLocation());
//        List<Investor> investors = currentUser.getInvestors();
//
//        investors.add(newInvestor);
//        currentUser.setInvestors(investors);

        Investor savedInvestor = investrepo.save(newInvestor);
        System.out.println(savedInvestor.getEmail());
        System.out.println(savedInvestor.getLocation());
        System.out.println(savedInvestor.getInvestorid());
//        return investrepo.save(newInvestor);
        return savedInvestor;
    }

    @Transactional
    @Override
    public Investor update(Investor investor, long id)
    {
        Investor newInvestor = investrepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (investor.getName() !=null)
        {
            newInvestor.setName(investor.getName());
        }

        if (investor.getFunded() != 0)
        {
            newInvestor.setFunded(investor.getFunded());
        }


        if (investor.getInvestamt() != null)
        {
            newInvestor.setInvestamt(investor.getInvestamt());
        }

        if (investor.getEmail() != null)
        {
            newInvestor.setEmail(investor.getEmail());
        }

        return investrepo.save(newInvestor);
    }

    @Override
    public Investor findById(long id) throws EntityNotFoundException
    {
        return investrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
}
