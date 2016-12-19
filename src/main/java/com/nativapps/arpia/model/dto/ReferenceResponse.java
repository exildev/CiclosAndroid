package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Relationship;
import com.nativapps.arpia.database.entity.TypeReference;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class ReferenceResponse extends ReferenceData {

    private long id;

    private List<AddressResponse> addresses;

    private List<EmailResponse> emails;

    private List<PhoneResponse> phones;

    public ReferenceResponse() {
    }

    public ReferenceResponse(Long id, List<AddressResponse> addresses,
            List<EmailResponse> emails, List<PhoneResponse> phones, String name,
            String lastName, TypeReference typeReference,
            Relationship relationship) {
        super(name, lastName, typeReference, relationship);
        this.id = id;
        this.addresses = addresses;
        this.emails = emails;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponse> addresses) {
        this.addresses = addresses;
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
}
