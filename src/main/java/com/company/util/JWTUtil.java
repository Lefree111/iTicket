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

    public static String encode(String email, ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(email);
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)));
        jwtBuilder.setIssuer("iTicet.uz");
        jwtBuilder.claim("role", role);
        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public static String getIdFromHeader(HttpServletRequest request, ProfileRole... requiredRoles) {
        try {
            ProfileJwtDto dto = (ProfileJwtDto) request.getAttribute("ProfileJwtDto");
            if (requiredRoles == null || requiredRoles.length == 0) {
                return dto.getEmail();
            }
            for (ProfileRole role : requiredRoles) {
                if (role.equals(dto.getRole())) {
                    return dto.getEmail();

                }
            }
        } catch (RuntimeException e) {
            throw new TokenNotValidException("Not Authorized");
        }
        throw new AppForbiddenException("Not Access");
    }

    public static ProfileJwtDto decode(String jwt) {
        JwtParser jwtParser = Jwts.parser();

        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(jwt);

        Claims claims = jws.getBody();
        String email = claims.getSubject();
        String role = String.valueOf(claims.get("role"));

        return new ProfileJwtDto(email, ProfileRole.valueOf(role));
    }



}
