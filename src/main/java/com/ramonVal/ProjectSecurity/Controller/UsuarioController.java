package com.ramonVal.ProjectSecurity.Controller;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Permission;
import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Rol;
import com.ramonVal.ProjectSecurity.Domain.Usuario;
import com.ramonVal.ProjectSecurity.Service.IRolService;
import com.ramonVal.ProjectSecurity.Service.IUsuarioService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  @Autowired
  private IUsuarioService usuarioService;
  @Autowired
  private IRolService rolService;

  @GetMapping()
  public ResponseEntity<List> findAll() {
    return ResponseEntity.ok(usuarioService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable Long id) {
    return ResponseEntity.ok(usuarioService.findById(id));
  }

  @PostMapping("/nuevo")
  public ResponseEntity createUsuario(@RequestBody Usuario usuario) {
    Set<Rol> roleList = new HashSet<>();
    Rol readRole;

    usuario.setPassword(usuarioService.encodePassword(usuario.getPassword()));

    for (Rol rol : usuario.getRoles()) {
      readRole = (Rol) rolService.findById(rol.getId()).orElse(null);
      if (readRole != null) {
        roleList.add(readRole);
      }
    }

    if (!roleList.isEmpty()) {
      usuario.setRoles(roleList);
      usuarioService.save(usuario);
      return ResponseEntity.ok(usuario);
    }

    return null;
  }
}
