package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Gender;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class AdministratorResponse extends AdministratorData {

    private Long id;

    private List<EmailResponse> emails;

    private List<PhoneResponse> phones;

    private List<AddressResponse> addresses;

    public AdministratorResponse() {
        this.emails = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.phones = new ArrayList<>();
    }

    public AdministratorResponse(Long id, String identification, String name,
            String lastName, Gender gender, Calendar birthday, Boolean linked,
            Integer nDayReport, String observations) {
        super(identification, name, lastName, gender, birthday, linked,
                nDayReport, observations);
        this.id = id;
        this.emails = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.phones = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<EmailResponse> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailResponse> emails) {
        this.emails = emails;
    }

    public List<PhoneResponse> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneResponse> phones) {
        this.phones = phones;
    }

    public List<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponse> addresses) {
        this.addresses = addresses;
    }
}
