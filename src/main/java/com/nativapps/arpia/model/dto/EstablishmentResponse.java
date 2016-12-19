package com.nativapps.arpia.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class EstablishmentResponse extends EstablishmentData{

    private Long id;

    private List<AddressResponse> addresses;

    public EstablishmentResponse() {
        this.addresses = new ArrayList<>();
    }

    public EstablishmentResponse(Long id, String nit, String name, 
            String observations) {
        super(nit, name, observations);
        this.id = id;
        this.addresses = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponse> addresses) {
        this.addresses = addresses;
    }
}
