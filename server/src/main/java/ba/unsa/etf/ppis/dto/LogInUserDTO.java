package ba.unsa.etf.ppis.dto;

import java.io.Serializable;

public class LogInUserDTO implements Serializable {
    private LocationDTO location;
    private String jwt;

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
