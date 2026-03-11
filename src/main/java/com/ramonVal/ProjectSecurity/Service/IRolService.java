package com.ramonVal.ProjectSecurity.Service;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Rol;
import java.util.List;
import java.util.Optional;

public interface IRolService {
    List findAll();
    Optional findById(Long id);
    Rol save(Rol role);
    void deleteById(Long id);
    Rol update(Rol role);
}
