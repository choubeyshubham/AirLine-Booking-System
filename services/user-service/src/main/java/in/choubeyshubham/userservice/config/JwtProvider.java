package in.choubeyshubham.userservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.*;

public class JwtProvider {

    private final SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth, Long userId){
        Collection<? extends GrantedAuthority> authorities= auth.getAuthorities();
        String roles=populateAuthorities(authorities);
        String jwtToken= Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+86400000))
                .claim("email",auth.getName())
                .claim("authorities",roles)
                .claim("userId",userId)
                .signWith(key)
                .compact();
        return jwtToken;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths= new HashSet<>();
        for(GrantedAuthority auth: authorities){
            auths.add(auth.getAuthority());
        }
        return String.join(",", auths);
    }


}

