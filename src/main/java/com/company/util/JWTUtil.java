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
