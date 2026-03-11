package com.ramonVal.ProjectSecurity.Controller;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Permission;
import com.ramonVal.ProjectSecurity.Domain.Usuario;
import com.ramonVal.ProjectSecurity.Service.IPermissionService;
import com.ramonVal.ProjectSecurity.Service.IUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permisos")
public class PermissionController {
  @Autowired
  private IPermissionService permissionService;

  @GetMapping()
  public ResponseEntity<List> findAll() {
    return ResponseEntity.ok(permissionService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable Long id) {
    return ResponseEntity.ok(permissionService.findById(id));
  }

  @PostMapping("/nuevo")
  public ResponseEntity crearUsuario(@RequestBody Permission permission) {
    Permission permissionNuevo = permissionService.save(permission);
    return ResponseEntity.ok(permissionNuevo);
  }

}
