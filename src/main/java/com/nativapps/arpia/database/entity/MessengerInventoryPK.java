package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
@Embeddable
public class MessengerInventoryPK implements Serializable {

    @Column(name = "MESSENGER_ID")
    private Long messengerId;

    @Column(name = "INVENTORY_ID")
    private Long inventoryId;

    public MessengerInventoryPK() {
    }

    public MessengerInventoryPK(Long messengerId, Long inventoryId) {
        this.messengerId = messengerId;
        this.inventoryId = inventoryId;
    }

    public Long getMessengerId() {
        return messengerId;
    }

    public void setMessengerId(Long messengerId) {
        this.messengerId = messengerId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

}
