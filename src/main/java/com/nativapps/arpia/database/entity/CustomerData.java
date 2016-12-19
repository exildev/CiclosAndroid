package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "CUSTOMER_DATA")
@NamedQueries({
    @NamedQuery(name = "customerData.findAll",
            query = "SELECT c FROM CustomerData c")
})
public class CustomerData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private long id;

    @Column(name = "OBSERVATIONS", columnDefinition = "TEXT")
    private String observations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARAMETER_ID")
    private CustomerParameter param;

    @OneToMany(mappedBy = "customerData")
    private List<Bonus> bonusList;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREDIT_INFO_ID")
    private CreditInfo creditInfo;
    
    @Column(name = "BANNED")
    private boolean banned;
    
    @OneToMany(mappedBy = "customer")
    private List<CustomerBanHistory> history;

    public CustomerData() {
        this.creditInfo = new CreditInfo();
    }

    public CustomerData(String observations) {
        this.creditInfo = new CreditInfo();
        this.observations = observations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public CustomerParameter getParam() {
        return param;
    }

    public void setParam(CustomerParameter param) {
        this.param = param;
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Bonus> bonusList) {
        this.bonusList = bonusList;
    }

    public void addBonus(Bonus bonus) {
        if (bonusList == null)
            bonusList = new ArrayList<>();

        if (bonus != null) {
            bonusList.add(bonus);
            if (bonus.getCustomerData() != this)
                bonus.setCustomerData(this);
        }
    }

    public CreditInfo getCreditInfo() {
        return creditInfo;
    }

    public void setCreditInfo(CreditInfo creditInfo) {
        this.creditInfo = creditInfo;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public List<CustomerBanHistory> getHistory() {
        return history;
    }

    public void setHistory(List<CustomerBanHistory> history) {
        this.history = history;
    }
    
    public void addHistory(CustomerBanHistory customerHistory) {
        if (history == null)
            history = new ArrayList<>();

        if (customerHistory != null) {
            history.add(customerHistory);
            if (customerHistory.getCustomer() != this)
                customerHistory.setCustomer(this);
        }
    }
}
