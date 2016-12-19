package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.CustomerTracing;
import com.nativapps.arpia.database.entity.Period;
import com.nativapps.arpia.database.entity.ReportType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class CustomerTracingData {
    
    private Period reportPeriod;
    
    private List<ReportType> reports;

    public CustomerTracingData() {
        this.reports = new ArrayList<>();
    }
    
    public CustomerTracingData(CustomerTracing entity) {
        this.reports = entity.getReports();
        this.reportPeriod = entity.getReportPeriod();
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
