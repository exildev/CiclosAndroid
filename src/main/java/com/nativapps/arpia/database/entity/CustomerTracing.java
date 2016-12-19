package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "CUSTOMER_TRACING")
public class CustomerTracing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REPORT_PERIOD")
    @Enumerated(EnumType.STRING)
    private Period reportPeriod;

    @Column(name = "REPORT_ID", nullable = false)
    @Fetch(FetchMode.SUBSELECT)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "REPORT", joinColumns = @JoinColumn(name = "CLIENT_ID"))
    @ElementCollection(fetch = FetchType.EAGER, targetClass = ReportType.class)
    private List<ReportType> reports;

    public CustomerTracing() {
        this.reports = new ArrayList<>();
    }

    public CustomerTracing(Period reportPeriod, List<ReportType> reports) {
        this.reportPeriod = reportPeriod;
        this.reports = reports;
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Period getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(Period reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public List<ReportType> getReports() {
        return reports;
    }

    public void setReports(List<ReportType> reports) {
        this.reports = reports;
    }
}
