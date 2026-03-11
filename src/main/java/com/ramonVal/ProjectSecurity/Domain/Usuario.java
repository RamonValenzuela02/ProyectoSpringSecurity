package com.ramonVal.ProjectSecurity.Domain;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Rol;
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
public class Usuario {
  @Id
  private Long id;
  private String nombre;
  private String apellido;
  private String email;
  private String password;
  private boolean enabled;
  private boolean accountNonExpired;
  private boolean credentialsNonExpired;

  @ManyToMany
  private Set<Rol> roles;
}
