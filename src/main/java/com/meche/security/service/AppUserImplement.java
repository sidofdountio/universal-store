package com.meche.security.service;


import com.meche.security.model.Appuser;

import java.util.List;

/**
 * @Author sidof
 * @Since 10/2/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface AppUserImplement {
    Appuser save(Appuser user);

    List<Appuser> getUsers();

    Appuser getUser(Long userId);

    Appuser edit(Appuser user);

    void deleteUser(Long userId);

}
