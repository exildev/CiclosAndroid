package com.nativapps.arpia.model.dto;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class AddressRequest extends AddressData{
    
    private Long neighborhood;

    public AddressRequest() {
    }

    public AddressRequest(String tag, 
            String residentialAddress, Long neighborhood) {
        super(tag, residentialAddress);
        this.neighborhood = neighborhood;
    }

    public Long getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Long neighborhood) {
        this.neighborhood = neighborhood;
    }
}
