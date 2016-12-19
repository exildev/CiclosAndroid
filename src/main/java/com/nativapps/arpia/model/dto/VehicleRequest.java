package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.VehicleOwnership;
import com.nativapps.arpia.database.entity.VehicleCondition;
import java.util.Calendar;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public class VehicleRequest extends VehicleData {

    public VehicleRequest() {
    }

    public VehicleRequest(VehicleOwnership ownership,
            VehicleCondition vehicleCondition, String trademark, String model,
            String color, String licensePlate, Calendar tecnomecanicaExpiration,
            Calendar soatExpiration) {
        super(ownership, vehicleCondition, trademark, model, color,
                licensePlate, tecnomecanicaExpiration, soatExpiration);
    }

}
