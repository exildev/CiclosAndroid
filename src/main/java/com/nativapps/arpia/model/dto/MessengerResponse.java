package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Gender;
import java.util.Calendar;
import java.util.List;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.2.1
 */
public class MessengerResponse extends MessengerData {

    private Long id;

    private List<AddressResponse> addresses;

    private List<EmailResponse> emails;

    private List<PhoneResponse> phones;

    private CurriculumVitaeResponse curriculumVitae;

    private Long userId;

    public MessengerResponse() {
    }

    public MessengerResponse(Long id, List<AddressResponse> addresses,
            List<EmailResponse> emails, List<PhoneResponse> phones,
            CurriculumVitaeResponse curriculumVitae, Long userId,
            String identification, String name, String lastName, Gender gender,
            Calendar birthday, Boolean dismissal, String observations,
            String photo, Integer category) {
        super(identification, name, lastName, gender, birthday, dismissal,
                observations, photo, category);
        this.id = id;
        this.addresses = addresses;
        this.emails = emails;
        this.phones = phones;
        this.curriculumVitae = curriculumVitae;
        this.userId = userId;
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

    public CurriculumVitaeResponse getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitaeResponse curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
