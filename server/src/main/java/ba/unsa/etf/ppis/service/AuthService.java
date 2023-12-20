package ba.unsa.etf.ppis.service;

public interface AuthService {

    boolean isValidToken(String authorizationHeader);
    boolean isAdminRole(String authorizationHeader);
    boolean isUserRole(String authorizationHeader);
    boolean isCurrentUser(Integer userId, String authorizationHeader);
    void setTokenNotValid(String authorizationHeader);
}
