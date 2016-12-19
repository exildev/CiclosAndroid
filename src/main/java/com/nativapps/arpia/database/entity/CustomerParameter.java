package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "CUSTOMER_PARAMETER")
@Inheritance(strategy = InheritanceType.JOINED)
public class CustomerParameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAMETER_ID")
    protected long id;

    @Column(name = "URGENT_ORDERS")
    protected boolean urgentOrders;

    @Column(name = "ALWAYS_MONEY_BACK")
    protected boolean alwaysMoneyReturn;

    @Column(name = "SPECIAL_RATE_INFO", columnDefinition = "TEXT")
    protected String specialRateInfo;
    
    @Column(name = "DENIED")
    protected boolean denied;
    
    @Column(name = "`CONDITION`", columnDefinition = "TEXT")
    protected String condition;

    @OneToOne
    @JoinColumn(name = "FAVORITE_MESSENGER")
    protected Messenger favoriteMessenger;

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "MESSENGER_BLACK_LIST",
            joinColumns = @JoinColumn(name = "CUSTOMER_PARAMETER_ID",
                    referencedColumnName = "PARAMETER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MESSENGER_ID",
                    referencedColumnName = "PERSON_ID"))
    protected List<Messenger> messengerBlackList;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRACING")
    protected CustomerTracing tracing;

    public CustomerParameter() {
        this.tracing = new CustomerTracing();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isUrgentOrders() {
        return urgentOrders;
    }

    public void setUrgentOrders(boolean urgentOrders) {
        this.urgentOrders = urgentOrders;
    }

    public boolean isAlwaysMoneyReturn() {
        return alwaysMoneyReturn;
    }

    public void setAlwaysMoneyReturn(boolean alwaysMoneyReturn) {
        this.alwaysMoneyReturn = alwaysMoneyReturn;
    }

    public String getSpecialRateInfo() {
        return specialRateInfo;
    }

    public void setSpecialRateInfo(String specialRateInfo) {
        this.specialRateInfo = specialRateInfo;
    }

    public Messenger getFavoriteMessenger() {
        return favoriteMessenger;
    }

    public void setFavoriteMessenger(Messenger favoriteMessenger) {
        this.favoriteMessenger = favoriteMessenger;
    }

    public List<Messenger> getMessengerBlackList() {
        return messengerBlackList;
    }

    public void setMessengerBlackList(List<Messenger> messengerBlackList) {
        this.messengerBlackList = messengerBlackList;
    }
    
    public void addMessenger(Messenger messenger){
        if (messengerBlackList == null)
            messengerBlackList = new ArrayList<>();
        
        if (messenger != null)
            messengerBlackList.add(messenger);
    }

    public boolean isDenied() {
        return denied;
    }

    public void setDenied(boolean denied) {
        this.denied = denied;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public CustomerTracing getTracing() {
        return tracing;
    }

    public void setTracing(CustomerTracing tracing) {
        this.tracing = tracing;
    }
}
