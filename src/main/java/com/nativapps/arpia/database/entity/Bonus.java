package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "BONUS")
@NamedQueries({
    @NamedQuery(name = "bonus.findAllByCustomerId",
            query = "SELECT b FROM Bonus b WHERE b.customerData.id = :customerId"),
    @NamedQuery(name = "bonus.findByCustomerId",
            query = "SELECT b FROM Bonus b "
                    + "WHERE b.customerData.id = :customerId AND b.id = :id")
})
public class Bonus implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "COUNT_FREE_SERVICES")
    private int countFreeService;

    @Column(name = "COUNT_FREE_SERVICES_USED")
    private int countFreeServiceUsed;

    @Column(name = "AVAILABLE")
    private boolean available;

    @Column(name = "REASON", columnDefinition = "TEXT", nullable = false)
    private String reason;

    @Column(name = "REGISTER_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar registerDate;

    @OneToOne
    @JoinColumn(name = "AUTHORIZED_BY", nullable = false)
    private User authorizedBy; //set(new User(userInfo.getId()))

    @Column(name = "EXPIRY_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar expiryDate;
    
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerData customerData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCountFreeService() {
        return countFreeService;
    }

    public void setCountFreeService(int countFreeService) {
        this.countFreeService = countFreeService;
    }

    public int getCountFreeServiceUsed() {
        return countFreeServiceUsed;
    }

    public void setCountFreeServiceUsed(int countFreeServiceUsed) {
        this.countFreeServiceUsed = countFreeServiceUsed;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

    public User getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(User authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }
}
