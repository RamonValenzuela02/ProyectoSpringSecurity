package com.ramonVal.ProjectSecurity.Controller;

import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Permission;
import com.ramonVal.ProjectSecurity.Domain.Autorizacion.Rol;
import com.ramonVal.ProjectSecurity.Domain.Usuario;
import com.ramonVal.ProjectSecurity.Service.IPermissionService;
import com.ramonVal.ProjectSecurity.Service.IRolService;
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
@RequestMapping("/api/roles")
public class RolController {
  @Autowired
  private IRolService roleService;
  @Autowired
  private IPermissionService permissionService;

  @GetMapping
  public ResponseEntity<List> findAll() {
    return ResponseEntity.ok(roleService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable Long id) {
    return ResponseEntity.ok(roleService.findById(id));
  }

  @PostMapping("/nuevo")
  public ResponseEntity createRole(@RequestBody Rol role) {
    Set<Permission> permissionList = new HashSet<>();
    Permission readPermission;

    for (Permission per : role.getPermisos()) {
      readPermission = (Permission) permissionService.findById(per.getId()).orElse(null);
      if (readPermission != null) {
        permissionList.add(readPermission);
      }
    }

    role.setPermisos(permissionList);
    Rol newRole = roleService.save(role);
    return ResponseEntity.ok(newRole);
  }
}
