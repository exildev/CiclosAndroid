package com.nativapps.arpia.model.dto;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class AddressResponse extends AddressData {
    
    private Long id;
    
    private NeighborhoodResponse neighborhood;

    public AddressResponse() {
    }

    public AddressResponse(Long id, String tag, String residentialAddress, 
            NeighborhoodResponse neighborhood) {
        super(tag, residentialAddress);
        this.id = id;
        this.neighborhood = neighborhood;
    }

    public AddressResponse(String tag, String residentialAddress, 
            NeighborhoodResponse neighborhood) {
        super(tag, residentialAddress);
        this.neighborhood = neighborhood;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NeighborhoodResponse getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(NeighborhoodResponse neighborhood) {
        this.neighborhood = neighborhood;
    }
}
