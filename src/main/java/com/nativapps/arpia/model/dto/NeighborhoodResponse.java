package com.nativapps.arpia.model.dto;

import java.util.Objects;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class NeighborhoodResponse extends NeighborhoodData {

    private Long id;

    public NeighborhoodResponse() {
    }

    public NeighborhoodResponse(Long id, String name) {
        super(name);
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final NeighborhoodResponse other = (NeighborhoodResponse) obj;
        if (!Objects.equals(this.id, other.id))
            return false;
        return Objects.equals(this.name, other.name);
    }
    
    
}
