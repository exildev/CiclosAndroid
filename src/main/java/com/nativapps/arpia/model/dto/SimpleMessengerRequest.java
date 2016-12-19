package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Gender;
import java.util.Calendar;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class SimpleMessengerRequest extends SimpleMessengerData {

    public SimpleMessengerRequest() {
    }

    public SimpleMessengerRequest(String identification, String name, 
            String lastName, Gender gender, Calendar birthday) {
        super(identification, name, lastName, gender, birthday);
    }
}
