package com.ramonVal.ProjectSecurity.Domain.Autorizacion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
  @Id
  private Long id;
  private String nombre;

  @ManyToMany
  private Set<Permission> permisos;

}
