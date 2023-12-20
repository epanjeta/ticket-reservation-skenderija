package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.entity.JwtEntity;
import ba.unsa.etf.ppis.exception.NotValidJwtException;
import ba.unsa.etf.ppis.repository.JwtRepository;
import ba.unsa.etf.ppis.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired private JwtUtils jwtUtils;
    @Autowired private JwtRepository jwtRepository;

    public boolean emptyHeader(String authorizationHeader){
        if(authorizationHeader == null) return true;
        if(authorizationHeader.isEmpty() || authorizationHeader.isBlank()) return true;
        else return false;
    }
    @Override
    public boolean isValidToken(String authorizationHeader) {
        if(emptyHeader(authorizationHeader)) throw new NotValidJwtException("This JWT token is not valid!");
        String token = authorizationHeader.replace("Bearer ", "");
        JwtEntity jwtToken = jwtRepository.findJwtEntitiesByToken(token);
        if (jwtToken == null) return false;
        if (!jwtToken.isValid()) return false;
        return jwtUtils.isTokenValid(token);
    }

    @Override
    public boolean isAdminRole(String authorizationHeader) {
        if(emptyHeader(authorizationHeader)) throw new NotValidJwtException("This JWT token is not valid!");
        String token = authorizationHeader.replace("Bearer ", "");
        if(!jwtUtils.isTokenValid(token)) throw new NotValidJwtException("This JWT token is not valid!");
        Claims claims = jwtUtils.extractClaims(token);
        String userType = (String) claims.get("userType");
        if(userType.equals("ADMIN")) return true;
        else return false;
    }

    @Override
    public boolean isUserRole(String authorizationHeader) {
        if(emptyHeader(authorizationHeader)) throw new NotValidJwtException("This JWT token is not valid!");
        String token = authorizationHeader.replace("Bearer ", "");
        if(!jwtUtils.isTokenValid(token)) throw new NotValidJwtException("This JWT token is not valid!");
        Claims claims = jwtUtils.extractClaims(token);
        String userType = (String) claims.get("userType");
        if(userType.equals("USER")) return true;
        else return false;
    }

    @Override
    public boolean isCurrentUser(Integer userId, String authorizationHeader) {
        if(emptyHeader(authorizationHeader)) throw new NotValidJwtException("This JWT token is not valid!");
        String token = authorizationHeader.replace("Bearer ", "");
        if(!jwtUtils.isTokenValid(token)) throw new NotValidJwtException("This JWT token is not valid!");
        Claims claims = jwtUtils.extractClaims(token);
        Integer userIdToken = (Integer) claims.get("userId");
        return Objects.equals(userId, userIdToken);
    }


    @Override
    public void setTokenNotValid(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        JwtEntity jwtToken = jwtRepository.findJwtEntitiesByToken(token);
        if(jwtToken == null){
            throw new EntityNotFoundException("JWT token not found in database");
        }
        jwtToken.setValid(false);
        jwtRepository.save(jwtToken);
    }
}
