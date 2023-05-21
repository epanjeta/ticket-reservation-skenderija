package ba.unsa.etf.ppis.dto;

public class LocationDTO {

    private int id;

    private String name;

    private UserDTO admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDTO getAdmin() {
        return admin;
    }

    public void setAdmin(UserDTO admin) {
        this.admin = admin;
    }

    public LocationDTO() {
    }

    public LocationDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
