package com.salesianas.dam.replica.utils.security;


import com.salesianas.dam.replica.exception.ReplicaException;

import java.text.ParseException;
import java.util.List;

/**
 * Component for operating with
 */
public interface JwtTokenUtils {

    /**
     * Checks if the jwt user has the specified role
     *
     * @param userRole Role of the user
     * @return True if user has the role
     * @throws ReplicaException - In case of error
     */
    boolean hasUserRoleToken(String userRole) throws ReplicaException;

    /**
     * Extracts the list of user roles from the token
     *
     * @return List of user roles
     */
    List<String> getUserRoleFromContext();

    /**
     * Extracts the token from the context
     *
     * @return Jwt token extracted from the context
     */
    String extractTokenFromContext();
}

