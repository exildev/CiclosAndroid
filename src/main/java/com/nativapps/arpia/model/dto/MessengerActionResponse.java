package com.nativapps.arpia.model.dto;

import java.util.Calendar;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1.0
 */
public class MessengerActionResponse extends MessengerActionData {

    private Long id;

    private Calendar registerDate;

    public MessengerActionResponse() {
    }

    public MessengerActionResponse(Long id, Calendar registerDate, String title,
            String description) {
        super(title, description);
        this.id = id;
        this.registerDate = registerDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

}
