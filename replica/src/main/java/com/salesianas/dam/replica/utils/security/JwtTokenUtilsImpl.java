package com.salesianas.dam.replica.utils.security;


import com.salesianas.dam.replica.exception.ReplicaPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of TokenRoleValidation
 *
 * @see  JwtTokenUtils
 */
@Component
public class JwtTokenUtilsImpl implements JwtTokenUtils {

    @Override
    @Transactional
    public boolean hasUserRoleToken(String userRole) throws ReplicaPermissionException {
        try {
            return this.getUserRoleFromContext().contains(userRole);

        } catch (Exception e) {
            throw new ReplicaPermissionException(HttpStatus.UNAUTHORIZED, "Bad Rol", "401");
        }
    }

    @Override
    @Transactional
    public List<String> getUserRoleFromContext() {
        List<String> roles = new ArrayList<>();

        AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities())
                .iterator().forEachRemaining(roles::add);

        return roles;

    }

    @Override
    @Transactional
    public String extractTokenFromContext() {
        String autho = null;

        if (RequestContextHolder.getRequestAttributes() != null) {
            autho = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                    .getHeader("Authorization");

            autho = autho.replace("Bearer ", "");
        }
        return autho;
    }
}
