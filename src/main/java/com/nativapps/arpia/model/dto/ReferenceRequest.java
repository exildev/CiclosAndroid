package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Relationship;
import com.nativapps.arpia.database.entity.TypeReference;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class ReferenceRequest extends ReferenceData {

    private List<AddressRequest> addresses;

    private List<EmailRequest> emails;

    private List<PhoneRequest> phones;

    public ReferenceRequest() {
    }

    public ReferenceRequest(List<AddressRequest> addresses,
            List<EmailRequest> emails, List<PhoneRequest> phones,
            String name, String lastName, String identification,
            TypeReference typeReference,
            Relationship relationship) {
        super(name, lastName, typeReference, relationship);
        this.addresses = addresses;
        this.emails = emails;
        this.phones = phones;
    }

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
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

}
