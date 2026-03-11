package com.ramonVal.ProjectSecurity.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api")
public class HelloWordController {

  @GetMapping("/holaNoSeg")
  public String holaNoSeg() {
    return "Hola no seg";
  }

  @GetMapping("/holaSeg")
  @PreAuthorize("hasAuthority('READ')")
  public String holaSeg() {
    return "Hola seg";
  }
}
