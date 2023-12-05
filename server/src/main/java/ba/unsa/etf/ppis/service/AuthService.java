package ba.unsa.etf.ppis.service;

public interface AuthService {
    boolean isAdminRole(String authorizationHeader);
    boolean isUserRole(String authorizationHeader);
}
