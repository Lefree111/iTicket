package com.company.util;

import com.company.enums.profile.ProfileRole;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
/*

    public static String encode(Integer id) { // 3 -> dsasdasda.asdasdasd.asdasdsa
        return doEncode(id, null, 30);
    }


    public static String doEncode(Integer id, ProfileRole role, long minute) { // 3 -> dsasdasda.asdasdasd.asdasdsa
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(String.valueOf(id));
        jwtBuilder.setIssuedAt(new Date()); // 10:15
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (minute * 60 * 1000)));
        jwtBuilder.setIssuer("mazgi production");

        if (role != null) {
            jwtBuilder.claim("role", role);
        }

        String jwt = jwtBuilder.compact();
        return jwt;
    }



    public static ProfileJwtDto decode(String jwt) {
        try {
            JwtParser jwtParser = Jwts.parser();

            jwtParser.setSigningKey(secretKey);

            Jws jws = jwtParser.parseClaimsJws(jwt);

            Claims claims = (Claims) jws.getBody();
            String id = claims.getSubject();
            String role = String.valueOf(claims.get("role"));

            return new ProfileJwtDto(Integer.parseInt(id), ProfileRole.valueOf(role));
        } catch (SignatureException e) {
            throw new TokenNotValidException("Token not valid");
        }

    }

    public static Integer getIdFromHeader(HttpServletRequest request, ProfileRole... requiredRole) {


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

    public static Integer decodeAndGetId(String jwt) {
        JwtParser jwtParser = Jwts.parser();

        jwtParser.setSigningKey(secretKey);
        Jws<Claims> jws = jwtParser.parseClaimsJws(jwt);

        Claims claims = jws.getBody();
        String id = claims.getSubject();

        return Integer.parseInt(id);
    }

*/

}
