package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.VehicleOwnership;
import com.nativapps.arpia.database.entity.VehicleCondition;
import java.util.Calendar;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.2
 */
public class VehicleResponse extends VehicleData {

    private Long id;

    public VehicleResponse() {
    }

    public VehicleResponse(Long id, VehicleOwnership ownership,
            VehicleCondition vehicleCondition, String trademark, String model,
            String color, String licensePlate, Calendar tecnomecanicaExpiration,
            Calendar soatExpiration) {
        super(ownership, vehicleCondition, trademark, model, color,
                licensePlate, tecnomecanicaExpiration, soatExpiration);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
