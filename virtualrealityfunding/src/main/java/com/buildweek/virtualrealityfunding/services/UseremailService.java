package com.buildweek.virtualrealityfunding.services;


import com.buildweek.virtualrealityfunding.models.Useremail;

import java.util.List;

public interface UseremailService
{
    List<Useremail> findAll();

    Useremail findUseremailById(long id);

    List<Useremail> findByUserName(String username);

    void delete(long id, boolean isAdmin);

    Useremail save(Useremail useremail, boolean isAdmin);
}
