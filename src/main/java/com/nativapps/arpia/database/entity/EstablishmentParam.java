package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "ESTABLISHMENT_PARAMETER")
@PrimaryKeyJoinColumn(referencedColumnName = "PARAMETER_ID")
public class EstablishmentParam extends CustomerParameter 
        implements Serializable{
    
    @Column(name = "COMMISSION_PER_SERVICE")
    private Double commissionPerService;
    
    @Column(name = "NUMBER_OF_SERVICES")
    private int numberOfServices;
    
    @Column(name = "DATE_INITIAL_REPORT")
    @Temporal(TemporalType.DATE)
    private Calendar dateInitialReport;
    
    @Column(name = "`PERIOD`")
    @Enumerated(EnumType.STRING)
    private Period period;

    public EstablishmentParam() {
        super();
    }
    
    public Double getCommissionPerService() {
        return commissionPerService;
    }

    public void setCommissionPerService(Double commissionPerService) {
        this.commissionPerService = commissionPerService;
    }

    public int getNumberOfServices() {
        return numberOfServices;
    }

    public void setNumberOfServices(int numberOfServices) {
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
