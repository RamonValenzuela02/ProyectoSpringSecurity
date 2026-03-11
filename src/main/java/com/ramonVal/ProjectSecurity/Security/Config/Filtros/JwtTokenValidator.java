package com.ramonVal.ProjectSecurity.Security.Config.Filtros;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ramonVal.ProjectSecurity.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenValidator extends OncePerRequestFilter {
  private JwtUtils jwtUtils;

  public JwtTokenValidator(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  //valida el token y lo carga en el securityContextHolder
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    String token = request.getHeader("Authorization");

    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      DecodedJWT decodedJWT = jwtUtils.verifyToken(token);
      String username =jwtUtils.extractUsername(decodedJWT);
      String authorities = decodedJWT.getClaim("authorities").asString();

      //Collections authorityList = (Collections) AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
      List<GrantedAuthority> authorityList =
        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

      SecurityContext contex = SecurityContextHolder.getContext();
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorityList);
      contex.setAuthentication(authentication);
      SecurityContextHolder.setContext(contex);
    }

    filterChain.doFilter(request, response);

  }
}
