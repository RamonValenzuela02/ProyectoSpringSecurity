package com.ramonVal.ProjectSecurity.Service;

import com.ramonVal.ProjectSecurity.Domain.Usuario;
import com.ramonVal.ProjectSecurity.Dto.AuthLoginRequestDto;
import com.ramonVal.ProjectSecurity.Dto.AuthResponseDto;
import com.ramonVal.ProjectSecurity.Repository.IUsuariosRepository;
import com.ramonVal.ProjectSecurity.Utils.JwtUtils;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {
  @Autowired
  private IUsuariosRepository usuarioRepository;
  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException(username));

    List<GrantedAuthority> authorities = new ArrayList<>();
    usuario.getRoles()
      .forEach(role ->
        authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getNombre()))));

    usuario.getRoles()
      .stream().flatMap(role -> role.getPermisos().stream())
      .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getNombre())));

    return new User(
      usuario.getUsername(),
      usuario.getPassword(),
      authorities);
  }

  public AuthResponseDto loginUser(@Valid AuthLoginRequestDto userRequest) {
    String username = userRequest.username();
    String password = userRequest.password();

    Authentication auth = this.authenticate(username,password);

    SecurityContextHolder.getContext().setAuthentication(auth);
    String accessToken = jwtUtils.generateToken(auth);
    AuthResponseDto responseDto = new AuthResponseDto(username, "login ok", accessToken, true);
    return responseDto;
  }

  private Authentication authenticate(String username, String password) {
    UserDetails userDetails = this.loadUserByUsername(username);

    if (userDetails == null) {
      throw new BadCredentialsException("Invalid username or password");
    }
    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid password");
    }
    return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
  }
}
