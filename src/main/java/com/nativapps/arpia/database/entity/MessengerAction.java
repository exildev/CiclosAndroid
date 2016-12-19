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
import javax.persistence.TemporalType;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1.1
 */
@Entity
@Table(name = "MESSENGER_ACTION")
@NamedQueries({
    @NamedQuery(name = "messengerAction.getAllByMessengerId",
            query = "SELECT a FROM MessengerAction a "
            + "WHERE a.messenger.id = :messengerId")
    ,
    @NamedQuery(name = "messengerAction.findAll",
            query = "SELECT a FROM MessengerAction a "
            + "WHERE a.messenger.id = :messengerId")
})
public class MessengerAction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSENGER_ID", nullable = false)
    private Messenger messenger;

    @Column(name = "REGISTER_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar registerDate;

    public MessengerAction() {
        registerDate = Calendar.getInstance();
    }

    public MessengerAction(String title, String description) {
        this.title = title;
        this.description = description;
        registerDate = Calendar.getInstance();
    }

    public MessengerAction(long id, String title, String description,
            Calendar registerDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.registerDate = registerDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
