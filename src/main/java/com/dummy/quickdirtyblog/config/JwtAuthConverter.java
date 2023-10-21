package com.dummy.quickdirtyblog.config;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
      new JwtGrantedAuthoritiesConverter();

  @Value("${jwt.auth.converter.principal-attribute}")
  private String principalAttribute;

  @Value("${jwt.auth.converter.application-id}")
  private String resourceId;

  @Override
  public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
    var authorities =
        Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt))
            .collect(Collectors.toSet());
    return new JwtAuthenticationToken(jwt, authorities, getPrincipleAttribute(jwt));
  }

  private String getPrincipleAttribute(Jwt jwt) {
    return isEmpty(principalAttribute)
        ? jwt.getClaim(JwtClaimNames.SUB)
        : jwt.getClaim(principalAttribute);
  }

  private Stream<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
    Map<String, Object> resourceAccess;
    Map<String, Object> resource;
    Collection<String> resourceRoles;
    if (jwt.getClaim("resource_access") != null) {
      resourceAccess = jwt.getClaim("resource_access");
      if (resourceAccess.get(resourceId) != null) {
        resource = (Map<String, Object>) resourceAccess.get(resourceId);
        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role));
      }
    }
    return Stream.empty();
  }
}
