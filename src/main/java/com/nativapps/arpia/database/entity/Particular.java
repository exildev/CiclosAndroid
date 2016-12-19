package com.nativapps.arpia.database.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "PARTICULAR")
@PrimaryKeyJoinColumn(referencedColumnName = "PERSON_ID")
@NamedQueries({
    @NamedQuery(name = "particular.findByCustomerId", 
            query = "SELECT p FROM Particular p "
                    + "WHERE p.customerData.id = :customerId")
})
public class Particular extends Person {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerData customerData;

    public Particular() {
        this.customerData = new CustomerData();
        this.customerData.setParam(new ParticularParam());
    }

    public Particular(String observations, String identification, String name,
            String lastName, Gender gender) {
        super(identification, name, lastName, gender);
        this.customerData = new CustomerData(observations);
        this.customerData.setParam(new ParticularParam());
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }
}
