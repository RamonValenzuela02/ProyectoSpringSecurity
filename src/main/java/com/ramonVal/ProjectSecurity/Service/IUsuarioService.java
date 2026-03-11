package com.ramonVal.ProjectSecurity.Service;

import com.ramonVal.ProjectSecurity.Domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
  List findAll();
  Optional findById(Long id);
  Usuario save(Usuario userSec);
  void deleteById(Long id);
  void update(Usuario userSec);
  String encodePassword(String password);
}
