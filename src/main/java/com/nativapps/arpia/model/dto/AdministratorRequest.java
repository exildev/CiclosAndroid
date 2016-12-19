package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Gender;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class AdministratorRequest extends AdministratorData{

    private List<EmailRequest> emails;

    private List<PhoneRequest> phones;

    private List<AddressRequest> addresses;

    public AdministratorRequest() {
    }

    public AdministratorRequest(String identification, String name, 
            String lastName, Gender gender, Calendar birthday, Boolean linked, 
            Integer nDayReport, String observations) {
        super(identification, name, lastName, gender, birthday, linked, 
                nDayReport, observations);
    }

    public List<EmailRequest> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailRequest> emails) {
        this.emails = emails;
    }

    public List<PhoneRequest> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneRequest> phones) {
        this.phones = phones;
    }

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }
}
