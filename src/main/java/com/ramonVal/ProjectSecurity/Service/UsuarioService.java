package com.ramonVal.ProjectSecurity.Service;

import com.ramonVal.ProjectSecurity.Domain.Usuario;
import com.ramonVal.ProjectSecurity.Repository.IUsuariosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {
  @Autowired
  private IUsuariosRepository usuarios;
  @Override
  public List findAll() {
    return usuarios.findAll();
  }

  @Override
  public Optional findById(Long id) {
    return usuarios.findById(id);
  }

  @Override
  public Usuario save(Usuario userSec) {
    return usuarios.save(userSec);
  }

  @Override
  public void deleteById(Long id) {
    usuarios.deleteById(id); //no tendria que eliminarse despues cambiar //todo
  }

  @Override
  public void update(Usuario userSec) {
    usuarios.save(userSec);
  }

  @Override
  public String encodePassword(String password) {
    return new BCryptPasswordEncoder().encode(password);
  }
}
