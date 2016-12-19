package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Period;
import java.util.Calendar;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class EstablishmentParamData extends CustomerParamData {

    protected Double commissionPerService;

    protected Integer numberOfServices;

    protected Calendar dateInitialReport;

    protected Period period;

    public EstablishmentParamData(Double commissionPerService, 
            Integer numberOfServices, Calendar dateInitialReport, 
            Period period, Boolean urgentOrders, Boolean alwaysMoneyReturn, 
            String specialRateInfo, Long favoriteMessengerId, Boolean denied, 
            String condition, CustomerTracingData tracing) {
        super(urgentOrders, alwaysMoneyReturn, specialRateInfo, 
                favoriteMessengerId, denied, condition, tracing);
        this.commissionPerService = commissionPerService;
        this.numberOfServices = numberOfServices;
        this.dateInitialReport = dateInitialReport;
        this.period = period;
    }
    
    public EstablishmentParamData() {
        this.tracing = new CustomerTracingData();
    }

    public Double getCommissionPerService() {
        return commissionPerService;
    }

    public void setCommissionPerService(Double commissionPerService) {
        this.commissionPerService = commissionPerService;
    }

    public Integer getNumberOfServices() {
        return numberOfServices;
    }

    public void setNumberOfServices(Integer numberOfServices) {
        this.numberOfServices = numberOfServices;
    }

    public Calendar getDateInitialReport() {
        return dateInitialReport;
    }

    public void setDateInitialReport(Calendar dateInitialReport) {
        this.dateInitialReport = dateInitialReport;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
