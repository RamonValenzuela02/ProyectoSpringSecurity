package com.ramonVal.ProjectSecurity.Service;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Rol;
import com.ramonVal.ProjectSecurity.Repository.IRolesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService {
  @Autowired
  private IRolesRepository roles;

  @Override
  public List findAll() {
    return roles.findAll();
  }

  @Override
  public Optional findById(Long id) {
    return roles.findById(id);
  }

  @Override
  public Rol save(Rol role) {
    return roles.save(role);
  }

  @Override
  public void deleteById(Long id) {
    roles.deleteById(id);
  }

  @Override
  public Rol update(Rol role) {
    return roles.save(role);
  }
}
