package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "NEIGHBORHOOD")
@NamedQueries({
    @NamedQuery(name = "neighborhood.findAll", 
            query = "SELECT n FROM Neighborhood n"),
    @NamedQuery(name = "neighborhood.findByName",
            query = "SELECT n FROM Neighborhood n WHERE n.name = :name")
})
public class Neighborhood implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    public Neighborhood() {
    }

    public Neighborhood(String name) {
        this.name = name;
    }

    public Neighborhood(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Neighborhood other = (Neighborhood) obj;
        if (this.id != other.id)
            return false;
        return Objects.equals(this.name, other.name);
    }
}
