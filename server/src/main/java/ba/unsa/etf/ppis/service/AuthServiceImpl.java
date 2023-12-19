package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.exception.NotValidJwtException;
import ba.unsa.etf.ppis.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired private JwtUtils jwtUtils;

    @Override
    public boolean isValidToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        return jwtUtils.isTokenValid(token);
    }

    @Override
    public boolean isAdminRole(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        if(!jwtUtils.isTokenValid(token)) throw new NotValidJwtException("This JWT token is not valid!");
        Claims claims = jwtUtils.extractClaims(token);
        String userType = (String) claims.get("userType");
        if(userType.equals("ADMIN")) return true;
        else return false;
    }

    @Override
    public boolean isUserRole(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        if(!jwtUtils.isTokenValid(token)) throw new NotValidJwtException("This JWT token is not valid!");
        Claims claims = jwtUtils.extractClaims(token);
        String userType = (String) claims.get("userType");
        if(userType.equals("USER")) return true;
        else return false;
    }
}
