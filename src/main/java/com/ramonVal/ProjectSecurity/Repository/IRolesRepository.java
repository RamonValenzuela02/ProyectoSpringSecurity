package com.ramonVal.ProjectSecurity.Repository;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<Rol, Long> {
}
