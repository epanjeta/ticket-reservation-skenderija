package ba.unsa.etf.ppis.entity;

import ba.unsa.etf.ppis.constants.DatabaseConstants;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(schema = DatabaseConstants.DATABASE_SCHEMA, name = "location" )
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
