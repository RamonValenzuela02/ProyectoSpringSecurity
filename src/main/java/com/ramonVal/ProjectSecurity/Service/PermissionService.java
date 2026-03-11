package com.ramonVal.ProjectSecurity.Service;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Permission;
import com.ramonVal.ProjectSecurity.Repository.IPermisosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {
  @Autowired
  private IPermisosRepository permisos;

  @Override
  public List findAll() {
    return permisos.findAll();
  }

  @Override
  public Optional<Permission> findById(Long id) {
    return permisos.findById(id);
  }

  @Override
  public Permission save(Permission permission) {
    return permisos.save(permission);
  }

  @Override
  public void deleteById(Long id) {
    permisos.deleteById(id);
  }

  @Override
  public Permission update(Permission permission) {
    return permisos.save(permission);
  }
}
