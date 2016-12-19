package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Period;
import java.util.Calendar;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class EstablishmentParamResponse extends EstablishmentParamData {

    public EstablishmentParamResponse() {
    }

    public EstablishmentParamResponse(Double commissionPerService,
            Integer numberOfServices, Calendar dateInitialReport, Period period,
            Boolean urgentOrders, Boolean alwaysMoneyReturn,
            String specialRateInfo, Long favoriteMessengerId, Boolean denied,
            String condition, CustomerTracingData tracing) {
        super(commissionPerService, numberOfServices, dateInitialReport, period,
                urgentOrders, alwaysMoneyReturn, specialRateInfo,
                favoriteMessengerId, denied, condition, tracing);
    }
}
