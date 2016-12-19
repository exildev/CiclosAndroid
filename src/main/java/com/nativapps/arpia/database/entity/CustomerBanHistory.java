package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "CUSTOMER_BAN_HISTORY")
@NamedQueries({
    @NamedQuery(name = "customerHistory.findAllByCustomerId",
            query = "SELECT h FROM CustomerBanHistory h "
                    + "WHERE h.customer.id = :customerId ORDER BY h.id DESC")
})
public class CustomerBanHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerData customer;

    @Column(name = "REASON", columnDefinition = "TEXT", nullable = false)
    private String reason;

    @Column(name = "`START`", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar start;

    @Column(name = "`END`")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar end;

    public CustomerBanHistory() {
    }

    public CustomerBanHistory(CustomerData customer, String reason, Calendar start) {
        this.customer = customer;
        this.reason = reason;
        this.start = start;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
}
