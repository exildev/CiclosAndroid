package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
@Entity
@Table(name = "MESSENGER_INVENTORY")
public class MessengerInventory implements Serializable {

    @EmbeddedId
    private MessengerInventoryPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("messengerId")
    @JoinColumn(name = "MESSENGER_ID", nullable = false)
    private Messenger messenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryId")
    @JoinColumn(name = "INVENTORY_ID", nullable = false)
    private Inventory inventory;

    @Column(name = "COUNT", nullable = false)
    private int count;

    public MessengerInventory() {
    }

    public MessengerInventory(Messenger messenger, Inventory inventory, int count) {
        this.id = new MessengerInventoryPK(messenger.getId(), inventory.getId());
        this.messenger = messenger;
        this.inventory = inventory;
        this.count = count;
    }

    public MessengerInventoryPK getId() {
        return id;
    }

    public void setId(MessengerInventoryPK id) {
        this.id = id;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
