package com.company.util;

import com.company.dto.profile.ProfileJwtDto;
import com.company.enums.profile.ProfileRole;
import com.company.exc.AppForbiddenException;
import com.company.exc.TokenNotValidException;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JWTUtil {

    private final static String secretKey = "kalit";

    public static String encode(String id, ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(id);
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)));
        jwtBuilder.setIssuer("kun.uz");
        jwtBuilder.claim("role", role);
        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public static String getIdFromHeader(HttpServletRequest request, ProfileRole... requiredRole) {


        try {
            ProfileJwtDto jwtDTO = (ProfileJwtDto) request.getAttribute("profileJwtDTO");
            if (requiredRole == null || requiredRole.length == 0) {
                return jwtDTO.getId();
            }
            for (ProfileRole role : requiredRole) {
                if (role.equals(jwtDTO.getRole())) {
                    return jwtDTO.getId();
                }
            }
            throw new AppForbiddenException("Not Access");

        } catch (RuntimeException e) {
            throw new TokenNotValidException("Not Authorized");
        }
    }
}
