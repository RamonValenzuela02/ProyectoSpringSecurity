package com.ramonVal.ProjectSecurity.Controller;

import com.ramonVal.ProjectSecurity.Dto.AuthLoginRequestDto;
import com.ramonVal.ProjectSecurity.Service.UserDetailServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private UserDetailServiceImp userDetailServiceImp;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthLoginRequestDto userRequest) {
    return new ResponseEntity<>(this.userDetailServiceImp.loginUser(userRequest), HttpStatus.OK);
  }
}
