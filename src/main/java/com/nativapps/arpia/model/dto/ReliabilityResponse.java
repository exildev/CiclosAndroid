package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Management;
import com.nativapps.arpia.database.entity.Speed;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.0.1
 */
public class ReliabilityResponse extends ReliabilityData {

    private Long id;

    public ReliabilityResponse() {
    }

    public ReliabilityResponse(Long id, Integer confidence, Speed speed,
            Management management, Integer minNumberBase, Integer minProduction,
            Integer minService, Long dailySaving) {
        super(confidence, speed, management, minNumberBase, minProduction,
                minService, dailySaving);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
