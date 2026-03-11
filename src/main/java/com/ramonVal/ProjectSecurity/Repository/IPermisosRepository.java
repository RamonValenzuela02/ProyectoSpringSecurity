package com.ramonVal.ProjectSecurity.Repository;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisosRepository extends JpaRepository<Permission, Long> {
}
